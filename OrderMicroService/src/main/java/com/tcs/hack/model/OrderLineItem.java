package com.tcs.hack.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_LINE_ITEM")
public class OrderLineItem {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="order_line_seq")
	@SequenceGenerator(name = "order_line_seq", sequenceName = "order_line_seq", allocationSize = 1)
	private Long orderLineItemId;
	private Long skuId;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="orderId",insertable=false,updatable=false)
	private Order order;
	
	
	
	private int itemQty;
	
	public Long getOrderLineItemId() {
		return orderLineItemId;
	}
	public void setOrderLineItemId(Long orderLineItemId) {
		this.orderLineItemId = orderLineItemId;
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
