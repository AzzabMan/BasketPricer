package com.bjss.basket.pricer.model;

import java.util.Date;

/**
 * Product model Object
 * 
 * @author AZZABI
 * 
 */
public class Product {
	
	private String name;
	private Double price;
	private Integer discount;
	private Date discountStartDate;
	private Date discountEndDate;
	
	/**
	 * @param name
	 * @param price
	 */
	public Product(String name, Double price) {
		super();
		this.name = name;
		this.price = price;
		this.discount = 0;
	}

	/**
	 * 
	 * @param name
	 * @param price
	 * @param discount
	 * @param discountStartDate
	 * @param discountEndDate
	 */
	public Product(String name, Double price, Integer discount,
			Date discountStartDate, Date discountEndDate) {
		super();
		this.name = name;
		this.price = price;
		this.discount = discount;
		this.discountStartDate = discountStartDate;
		this.discountEndDate = discountEndDate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the discount
	 */
	public Integer getDiscount() {
		return discount;
	}

	/**
	 * @return the discountStartDate
	 */
	public Date getDiscountStartDate() {
		return discountStartDate;
	}

	/**
	 * @return the discountEndDate
	 */
	public Date getDiscountEndDate() {
		return discountEndDate;
	}

	/**
	 * @return
	 * @see java.lang.Integer#hashCode()
	 */
	public int hashCode() {
		return discount.hashCode();
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Integer#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return discount.equals(obj);
	}

	
	
}
