package com.example.payment.payment_service.functions;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.example.payment.payment_service.dto.OrderDto;

@Configuration
public class OrderNotification {
	
	@Autowired
	private StreamBridge streamBridge;
	
	@Bean
	public Consumer<Message<OrderDto>> orderEvent(){
		return message->{
			OrderDto orderDto= message.getPayload();
			System.out.println("order recieved with id="+orderDto.orderId());
			System.out.println("order recieved with amount="+orderDto.amount());
			System.out.println("order recieved with status="+orderDto.status());
			sendAcknowledgement();
			
		};
	}
	
 	public void sendAcknowledgement(){

		Message<String> message= MessageBuilder.withPayload("Message is received with success").build();
		
		streamBridge.send("orderacknowledge-out-0", message);
 		
	}
	
	
}
