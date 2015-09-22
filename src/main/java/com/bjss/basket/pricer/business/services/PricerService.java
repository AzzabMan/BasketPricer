package com.bjss.basket.pricer.business.services;

import java.math.BigDecimal;
import java.util.Map;

import com.bjss.basket.pricer.model.Product;

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
	 * @param basket
	 * @return
	 */
	public BigDecimal priceDiscountForProducts(Map<Product, Integer> basket);

}
