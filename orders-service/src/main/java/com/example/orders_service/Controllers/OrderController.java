package com.example.orders_service.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orders_service.dto.OrderCreateRequestDto;
import com.example.orders_service.entity.Order;
import com.example.orders_service.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody OrderCreateRequestDto createRequestDto){
		Order order= orderService.createOrder(createRequestDto);
		return new ResponseEntity<>(order,HttpStatus.CREATED);
	}
}
