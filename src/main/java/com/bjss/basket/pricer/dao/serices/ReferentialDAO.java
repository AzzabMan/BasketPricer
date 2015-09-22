package com.bjss.basket.pricer.dao.serices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bjss.basket.pricer.model.Discount;
import com.bjss.basket.pricer.model.Product;
import com.bjss.basket.pricer.runner.ProductNotFoundException;
import com.bjss.basket.pricer.utils.DateTimeUtils;

@Service
public class ReferentialDAO implements IReferentialDAO {

	private static List<Product> products = new ArrayList<>();
	private static List<Discount> discounts = new ArrayList<>();
	
	static {
		/***	create products	***/ 
		products.add(new Product("Soup",   0.65d));
		products.add(new Product("Bread",  0.80d));
		products.add(new Product("Milk",   1.30d));
		products.add(new Product("Apples", 1.00d));
		
		/***	create discount rules	***/
		// Apples have a 10% discount off their normal price this week
		Discount appleDiscounts = new Discount(10l, "Apples","Apples", 1l,
				DateTimeUtils.getFirstDayOfWeek(Calendar.getInstance().getTime()),
				DateTimeUtils.getFirstDayOfNextWeek(Calendar.getInstance().getTime()));
		
		// Buy 2 tins of soup and get a loaf of bread for half price
		Discount soupDiscounts = new Discount(50l, "Soup", "Bread", 2l, null, null);
		
		discounts.add(appleDiscounts);
		discounts.add(soupDiscounts);
		
	}
	
	@Override
	public List<Product> getAllMarketData() {
		return products;
	}

	@Override
	public Product getProductByName(String name) throws ProductNotFoundException {
		for (Product product : products) {
			if (product.getName().equalsIgnoreCase(name)) {
				return product;
			}
		}
		throw new ProductNotFoundException();
	}
	
	@Override
	public List<Discount> getDiscountsforProduct(Product product){
		List<Discount> productDiscounts = new ArrayList<>();
		for (Discount discount : discounts) {
			if (discount.getOriginalProduct().equalsIgnoreCase(product.getName())) {
				productDiscounts.add(discount);
			}
		}
		return productDiscounts;
	}
	
}
