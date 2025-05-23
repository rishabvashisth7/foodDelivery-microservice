package com.example.food.food_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.food_service.dto.FoodCategoryDTO;
import com.example.food.food_service.service.FoodCategoryService;

@RestController
@RequestMapping("/api/v1/food-categories")
public class FoodCategoryController {


    @Autowired
    private FoodCategoryService foodCategoryService;


    @GetMapping
    public List<FoodCategoryDTO> getAllFoodCategories() {
        return foodCategoryService.getAllFoodCategories();
    }

    @GetMapping("/{id}")
    public FoodCategoryDTO getFoodCategoryById(@PathVariable String id) {
        return foodCategoryService.getFoodCategoryById(id);
    }

    @PostMapping
    public FoodCategoryDTO createFoodCategory(@RequestBody FoodCategoryDTO foodCategoryDTO) {
        return foodCategoryService.createFoodCategory(foodCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodCategory(@PathVariable String id) {
        foodCategoryService.deleteFoodCategory(id);
    }
}
