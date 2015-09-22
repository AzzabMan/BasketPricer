package com.bjss.basket.pricer.business.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bjss.basket.pricer.dao.serices.ReferentialDAO;
import com.bjss.basket.pricer.model.Discount;
import com.bjss.basket.pricer.model.Product;

/**
 * Dummy service class of calculation
 * 
 * @author AZZABI
 * 
 */
@Service
public class PricerServiceImpl implements PricerService {

	final static Logger logger = Logger.getLogger(PricerServiceImpl.class);
	
	@Autowired
	private ReferentialDAO referentialDAO;

	@Override
	  public BigDecimal price(Product product, Integer count) {
		// product valuation = price * quantity
		BigDecimal productTotalPrice = new BigDecimal(product.getPrice() * count);
		return productTotalPrice.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	@Override
	public BigDecimal priceDiscountForProducts(Map<Product, Integer> basket) {
		BigDecimal totalDiscount = new BigDecimal(0);
		List<StringBuilder> productsDiscountMessage = new ArrayList<>();

		//check if there is any discount for products
		for (Map.Entry<Product, Integer> entry : basket.entrySet()) {
			BigDecimal productDiscount = new BigDecimal(0);
			Integer productCount = entry.getValue();
			Product product = entry.getKey();

			// get discount rules for product
			List<Discount> productDiscounts = referentialDAO.getDiscountsforProduct(product);
			
			if (!CollectionUtils.isEmpty(productDiscounts)) {
				for (Discount discount : productDiscounts) {
					
					productDiscount = productDiscount.add(new BigDecimal((
							productCount.doubleValue() * discount.getPercentage() * -1 / discount.getProductCount() ) / 100));
					
					productsDiscountMessage.add(new StringBuilder(product.getName() + " " +
							discount.getPercentage() + "% off: " + productDiscount.doubleValue() ));
					
				}
			}
			totalDiscount = totalDiscount.add(productDiscount);
		}
		
		// printout products offers
		if(CollectionUtils.isEmpty(productsDiscountMessage)) {
			System.out.println("(No offers available)");
		} else {
			for (StringBuilder stringBuilder : productsDiscountMessage) {
				System.out.println(stringBuilder.toString());
			}
		}
		
		return totalDiscount.setScale(2, RoundingMode.HALF_EVEN);
	}
}
