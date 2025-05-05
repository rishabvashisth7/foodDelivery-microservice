package com.example.restaurant.restaurant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restaurant.restaurant_service.entity.Restaurant;

import java.util.Optional;


public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
   Optional<Restaurant> findByName(String name);

}