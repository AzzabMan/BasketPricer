package com.bjss.basket.pricer.runner;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.bjss.basket.pricer.model.Basket;

/**
 * Main class for application starting
 * 
 * @author AZZABI
 * 
 */
@Component
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

		AppRunner appRunner = applicationContext.getBean(AppRunner.class);
		appRunner.start(args);
	}
	
	// getting data from dataSource
	@Autowired
	private Basket basket;
	private void start(String[] args) {
		
		basket.buildBasket(args);
		
		// Compute the subTotal for all the basket product 
		BigDecimal subTotal = basket.computeSubtotal();
		
		// printout subtotal
		StringBuilder subTotalOutput = new StringBuilder();
		subTotalOutput.append("Subtotal: ");
		subTotal = subTotal.setScale(2, RoundingMode.HALF_EVEN);
		subTotalOutput.append(subTotal.doubleValue());
		
		System.out.println(subTotalOutput.toString());
		
		// Compute the discounts
		BigDecimal totalDiscounts = basket.computeDiscounts();
		
		StringBuilder totalOutput = new StringBuilder();
		// printout total price
		totalOutput.append("Total price: ");
		BigDecimal totalPrice = subTotal.add(totalDiscounts); 
		totalOutput.append(totalPrice.doubleValue());
		
		System.out.println(totalOutput.toString());
	}

}
