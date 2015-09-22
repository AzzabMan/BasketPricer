package com.bjss.basket.pricer.model;

import java.util.Date;

public class Discount {
	
	// percentage of the discount
	private Long percentage;
	
	// id of the product allowing to apply a discount (FK)
	private String originalProduct;

	// id of the product that will be discounted (FK)
	private String productToDiscount;

	// quantity of products allowing to have the discount
	private Long productCount;

	// start date of discount, can be null
	private Date discountStartDate;
	
	// start date of discount, can be null
	private Date discountEndDate;

	/**
	 * Constructor using all fields
	 * @param percentage
	 * @param originalProduct
	 * @param productToDiscount
	 * @param productCount
	 * @param discountStartDate
	 * @param discountEndDate
	 */
	public Discount(Long percentage, String originalProduct,
			String productToDiscount, Long productCount,
			Date discountStartDate, Date discountEndDate) {
		super();
		this.percentage = percentage;
		this.originalProduct = originalProduct;
		this.productToDiscount = productToDiscount;
		this.productCount = productCount;
		this.discountStartDate = discountStartDate;
		this.discountEndDate = discountEndDate;
	}

	/**
	 * @return the percentage
	 */
	public Long getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(Long percentage) {
		this.percentage = percentage;
	}

	/**
	 * @return the originalProduct
	 */
	public String getOriginalProduct() {
		return originalProduct;
	}

	/**
	 * @param originalProduct the originalProduct to set
	 */
	public void setOriginalProduct(String originalProduct) {
		this.originalProduct = originalProduct;
	}

	/**
	 * @return the productToDiscount
	 */
	public String getProductToDiscount() {
		return productToDiscount;
	}

	/**
	 * @param productToDiscount the productToDiscount to set
	 */
	public void setProductToDiscount(String productToDiscount) {
		this.productToDiscount = productToDiscount;
	}

	/**
	 * @return the productCount
	 */
	public Long getProductCount() {
		return productCount;
	}

	/**
	 * @param productCount the productCount to set
	 */
	public void setProductCount(Long productCount) {
		this.productCount = productCount;
	}

	/**
	 * @return the discountStartDate
	 */
	public Date getDiscountStartDate() {
		return discountStartDate;
	}

	/**
	 * @param discountStartDate the discountStartDate to set
	 */
	public void setDiscountStartDate(Date discountStartDate) {
		this.discountStartDate = discountStartDate;
	}

	/**
	 * @return the discountEndDate
	 */
	public Date getDiscountEndDate() {
		return discountEndDate;
	}

	/**
	 * @param discountEndDate the discountEndDate to set
	 */
	public void setDiscountEndDate(Date discountEndDate) {
		this.discountEndDate = discountEndDate;
	}

}
