package com.example.food.food_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.food.food_service.dto.FoodItemDTO;
import com.example.food.food_service.dto.RestaurantDto;
import com.example.food.food_service.service.FoodItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food-items")
public class FoodItemController {



    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public List<FoodItemDTO> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }
    
//    @GetMapping("/restaurants")
//    public ResponseEntity<List<RestaurantDto>> getAllRestaurantDtos() {
//        return new ResponseEntity<>(foodItemService.getAllRestaurantDtos(),HttpStatus.OK);
//    }
    
    @GetMapping("/{id}")
    public FoodItemDTO getFoodItemById(@PathVariable String id) {
        return foodItemService.getFoodItemById(id);
    }

    @PostMapping
    public FoodItemDTO createFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        return foodItemService.createFoodItem(foodItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodItem(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
    }

}

