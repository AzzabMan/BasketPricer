package com.bjss.basket.pricer.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bjss.basket.pricer.business.services.PricerService;
import com.bjss.basket.pricer.dao.serices.ReferentialDAO;
import com.bjss.basket.pricer.runner.ProductNotFoundException;

@Service
public class Basket {
	
	final static Logger logger = Logger.getLogger(Basket.class);
	
	@Autowired
	private ReferentialDAO referentialDAO;
	
	@Autowired
	private PricerService pricerService;
	
	private Map<Product, Integer> basketMap;

	
	public Basket() {}
	
	/**
	 * @param basketMap
	 */
	public void buildBasket(String[] args) {
		
		logger.info("Creating Basket using " + args.length + " fields:");
		this.basketMap = new HashMap<>();
		
		// building basket from args
		for (String productName : args) {
			try {
				Product p = referentialDAO.getProductByName(productName);
				if (!basketMap.containsKey(p)) {
					basketMap.put(p, 1);
				} else {
					basketMap.put(p, new Integer(basketMap.get(p) + 1));
				}
			} catch (ProductNotFoundException e) {
				logger.error("Error to get product name from referential");
				System.exit(1);
			}
		}
	}
	
	public Product getProductByName(String name) throws ProductNotFoundException{
		for (Map.Entry<Product, Integer> entry : basketMap.entrySet()) {
			if(name.equals(entry.getKey().getName())) {
				return entry.getKey();
			}
		}
		throw new ProductNotFoundException();
	}

	public BigDecimal computeSubtotal() {
		BigDecimal subTotal = new BigDecimal(0);

		for (Map.Entry<Product, Integer> entry : basketMap.entrySet()) {
			// compute total price
			subTotal = subTotal.add(pricerService.price(entry.getKey(), entry.getValue()));
		}
		return subTotal;
	}

	public BigDecimal computeDiscounts() {
		BigDecimal discounts = pricerService.priceDiscountForProducts(basketMap);
		return discounts;
	}
	
}
