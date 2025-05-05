package com.example.food.food_service.dto;

import lombok.Getter; import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodCategoryDTO {
    private String id;
    private String name;
    private String description;
    private List<FoodItemDTO> foodItems;
}