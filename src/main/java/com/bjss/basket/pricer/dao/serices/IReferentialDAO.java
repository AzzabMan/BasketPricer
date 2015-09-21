package com.bjss.basket.pricer.dao.serices;

import java.util.List;

import com.bjss.basket.pricer.model.Product;
import com.bjss.basket.pricer.runner.ProductNotFoundException;

public interface IReferentialDAO {
		
	public List<Product> getAllMarketData();
	
	public Product getProductByName(String name) throws ProductNotFoundException;

}
