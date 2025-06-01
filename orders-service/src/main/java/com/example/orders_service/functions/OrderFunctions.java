package com.example.orders_service.functions;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.orders_service.dto.OrderDto;

@Configuration
public class OrderFunctions {

	@Bean
	public Function<OrderDto,String> createOrder(){
		return orderDto->{
			return "order is created with the id = "+orderDto.orderId();
		};
	}
	
}
