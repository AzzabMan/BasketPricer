package com.bjss.basket.pricer.business.services;

import java.math.BigDecimal;
import java.util.Map;

import com.bjss.basket.pricer.model.Product;
import com.bjss.basket.pricer.runner.ProductNotFoundException;

public interface PricerService {

	/**
	 * Execute calculations based on an product price 
	 * @param product
	 * @param count
	 * @return
	 */
	public BigDecimal price(Product product, Integer count);

	/**
	 * Compute discount on product price
	 * @param product
	 * @return
	 */
	public BigDecimal pricePeriodicDiscountOnProduct(Product product);

	/**
	 * Compute compound offers
	 * @param basket
	 * @throws ProductNotFoundException 
	 */
	public BigDecimal priceCompoundDiscountOnBasket(int breadCount, int soupCount) throws ProductNotFoundException;

}
