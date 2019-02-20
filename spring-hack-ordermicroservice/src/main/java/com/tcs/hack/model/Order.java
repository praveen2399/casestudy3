package com.tcs.hack.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name ="Order")
@Table(name = "orderData")
public class Order {
	

	@Id
	private Long orderId;
	private Long customerID;
	private String paymentChannel;
	private Boolean isCod;
	private String orderStatus;
	private Long orderCreatedOn;
	private Double totalAmount;
	private String shippingAddress;
	
	
	
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
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
	
	

}
