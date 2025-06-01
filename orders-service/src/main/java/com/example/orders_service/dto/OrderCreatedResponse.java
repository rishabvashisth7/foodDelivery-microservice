  package com.example.orders_service.dto;

public class OrderCreatedResponse {

	String orderId;

	public OrderCreatedResponse(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
