package com.bjss.basket.pricer.business.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bjss.basket.pricer.dao.serices.ReferentialDAO;
import com.bjss.basket.pricer.model.Product;
import com.bjss.basket.pricer.runner.ProductNotFoundException;

/**
 * Dummy service class of calculation
 * 
 * @author AZZABI
 * 
 */
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
	public BigDecimal pricePeriodicDiscountOnProduct(Product product) {
		BigDecimal productDiscount = new BigDecimal(product.getPrice());
		productDiscount = productDiscount.multiply(new BigDecimal(product.getDiscount().doubleValue() * -1 / 100));
		return productDiscount.setScale(2, RoundingMode.HALF_EVEN);
	}
	
	@Override
	public BigDecimal priceCompoundDiscountOnBasket(int breadCount, int soupCount) throws ProductNotFoundException {
		BigDecimal compoundDiscount = new BigDecimal(0);
		// get the number of 2 soups in the basket
		int soupCountBy2 = soupCount / 2;
		for (int i = 0; i < Math.min(breadCount, soupCountBy2); i++) {
			Product bread = referentialDAO.getProductByName("Bread");
			compoundDiscount = compoundDiscount.add(new BigDecimal(bread.getPrice() * 0.5));
		}
		return compoundDiscount.setScale(2, RoundingMode.HALF_EVEN);
	}
	
}
