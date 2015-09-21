package com.bjss.basket.pricer.dao.serices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bjss.basket.pricer.model.Product;
import com.bjss.basket.pricer.runner.ProductNotFoundException;
import com.bjss.basket.pricer.utils.DateTimeUtils;


public class ReferentialDAO implements IReferentialDAO {

	private static List<Product> products = new ArrayList<>();
	
	
	static {
		products.add(new Product("Soup", 0.65d));
		products.add(new Product("Bread", 0.80d));
		products.add(new Product("Milk", 1.30d));
		products.add(new Product("Apples", 1.00d,
				10, DateTimeUtils.getFirstDayOfWeek(Calendar.getInstance().getTime()),
				DateTimeUtils.getFirstDayOfNextWeek(Calendar.getInstance().getTime())));
	}
	
	@Override
	public List<Product> getAllMarketData() {
		return products;
	}

	public Product getProductByName(String name) throws ProductNotFoundException {
		for (Product product : products) {
			if (product.getName().equalsIgnoreCase(name)) {
				return product;
			}
		}
		throw new ProductNotFoundException();
	}
}
