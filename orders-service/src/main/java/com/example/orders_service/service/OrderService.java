package com.example.orders_service.service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.orders_service.dto.OrderCreateRequestDto;
import com.example.orders_service.dto.OrderDto;
import com.example.orders_service.entity.Order;


@Service
public class OrderService {

		@Autowired
		private StreamBridge streamBridge;
		
		public Order createOrder(OrderCreateRequestDto dto){
			
			Order order = new Order();
			order.setOrderId(UUID.randomUUID().toString());
			order.setAmount(dto.getAmount());
			order.setUserId(dto.getUserId());
			order.setStatus("Created");
			order.setProductId(dto.getProductId());
			order.setUserId(dto.getUserId());
			order.setOrderDate(LocalDate.now());
			System.out.println("Order is created")    ;
			
			OrderDto orderDto= new OrderDto(order.getOrderId(),Integer.parseInt(order.getAmount()),order.getStatus());
			Message<OrderDto> message= MessageBuilder.withPayload(orderDto).build();
			
			streamBridge.send("orderCreated-out-0", message);
			
			return order;
			
		}
		
		@Bean
		public Consumer<Message<String>> orderAcknowdged(){
			return message->{
				String msg= message.getPayload();
				System.out.println("message received : "+msg);
				
			};
		}
	
}
