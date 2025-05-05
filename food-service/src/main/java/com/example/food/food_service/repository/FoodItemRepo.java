package com.example.food.food_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food.food_service.entity.FoodItem;

public interface FoodItemRepo extends JpaRepository<FoodItem, String> {
}
