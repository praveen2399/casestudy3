package com.tcs.hack.model;



public class OrderDTO {

	private Long orderId;
	private Long customerID;
	private String paymentChannel;
	private Boolean isCod;
	private Long orderCreatedOn;
	private Double totalAmount;
	private String shippingAddress;
	private Long skuId;
	private int itemQty;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	public String getPaymentChannel() {
		return paymentChannel;
	}
	public void setPaymentChannel(String paymentChannel) {
		this.paymentChannel = paymentChannel;
	}
	public Boolean getIsCod() {
		return isCod;
	}
	public void setIsCod(Boolean isCod) {
		this.isCod = isCod;
	}
	public Long getOrderCreatedOn() {
		return orderCreatedOn;
	}
	public void setOrderCreatedOn(Long orderCreatedOn) {
		this.orderCreatedOn = orderCreatedOn;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	
	

}
