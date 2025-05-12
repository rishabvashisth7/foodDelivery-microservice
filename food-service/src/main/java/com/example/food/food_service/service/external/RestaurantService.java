package com.example.food.food_service.service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.food.food_service.dto.RestaurantDto;

//@FeignClient(name="restaurant-service",url="lb://restaurant-service")
@FeignClient(name = "restaurant-service")
public interface RestaurantService {

	@GetMapping("/api/v1/restaurants/{id}")
	RestaurantDto getRestaurantById(@PathVariable("id") String id);
	
}
