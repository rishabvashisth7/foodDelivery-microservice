package com.example.orders_service.dto;
public record OrderDto(
        String orderId,
        int amount,
        String status
) {
}