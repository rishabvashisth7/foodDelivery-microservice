package com.example.payment.payment_service.dto;
public record OrderDto(
        String orderId,
        int amount,
        String status
) {
}