package com.bjss.basket.pricer.runner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.CollectionUtils;

import com.bjss.basket.pricer.business.services.PricerService;
import com.bjss.basket.pricer.dao.serices.ReferentialDAO;
import com.bjss.basket.pricer.model.Product;

/**
 * Main class for application starting
 * 
 * @author AZZABI
 * 
 */
public class AppRunner {

	final static Logger logger = Logger.getLogger(AppRunner.class);

	/**
	 * Example of Main method.
	 */
	public static void main(String[] args) {

		// spring context initialization
		logger.info("Initializing Spring context.");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		logger.info("Spring context initialized.");

		// getting data from dataSource
		ReferentialDAO referentialDAO = (ReferentialDAO) applicationContext.getBean("referentialDAO");
		
		Map<Product, Integer> basket = new HashMap<>();
		// building basket from args
		for (String productName : args) {
			try {
				Product p = referentialDAO.getProductByName(productName);
				if (!basket.containsKey(p)) {
					basket.put(p, 1);
				} else {
					basket.put(p, new Integer(basket.get(p) + 1));
				}
			} catch (ProductNotFoundException e) {
				logger.error("Error to get product name from referential");
				System.exit(1);
			}
		}

		// autowire the pricerService
		PricerService pricerService = (PricerService) applicationContext.getBean("pricerService");
		
		BigDecimal subTotal = new BigDecimal(0);
		BigDecimal totalProductDiscounts = new BigDecimal(0);
		
		List<StringBuilder> productsDiscountMessage = new ArrayList<>();
		
		int breadCount = 0;
		int soupCount = 0;
		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
			// compute total price
			subTotal = subTotal.add(pricerService.price(entry.getKey(), entry.getValue()));
			
			// compute products offers
			BigDecimal productDiscount = pricerService.pricePeriodicDiscountOnProduct(entry.getKey());
			if(productDiscount.doubleValue() != 0) {
				totalProductDiscounts = totalProductDiscounts.add(productDiscount);
				productsDiscountMessage.add(new StringBuilder(entry.getKey().getName() + " " + entry.getKey().getDiscount() + "% off: " + productDiscount.doubleValue() + "\n"));
			}
			
			if("Bread".equals(entry.getKey().getName())) {
				breadCount = entry.getValue();
			}
			if("Soup".equals(entry.getKey().getName())) {
				soupCount = entry.getValue();
			}			
			
		}
		
		
		// compute compound offers
		BigDecimal compoundDiscount = new BigDecimal(0);
		try {
			compoundDiscount = pricerService.priceCompoundDiscountOnBasket(breadCount, soupCount);
			if(compoundDiscount.doubleValue() != 0) {
				totalProductDiscounts = totalProductDiscounts.add(compoundDiscount);
				productsDiscountMessage.add(new StringBuilder("Coumpound 50% off: " + compoundDiscount.doubleValue() + "\n"));
			}			
		} catch (ProductNotFoundException e) {
			logger.error("Error to get product name from referential");
			System.exit(1);
		}
		
		// printout subtotal
		StringBuilder output = new StringBuilder();
		output.append("Subtotal: ");
		subTotal = subTotal.setScale(2, RoundingMode.HALF_EVEN);
		output.append(subTotal.doubleValue());
		output.append("\n");
		
		// printout products offers
		if(CollectionUtils.isEmpty(productsDiscountMessage)) {
			output.append("(No offers available)\n");
		} else {
			for (StringBuilder stringBuilder : productsDiscountMessage) {
				output.append(stringBuilder.toString());
			}
		}
		
		// printout total price
		output.append("Total price: ");
		BigDecimal totalPrice = subTotal.add(totalProductDiscounts); 
		output.append(totalPrice.doubleValue());
		
		System.out.println(output.toString());
	}

}
