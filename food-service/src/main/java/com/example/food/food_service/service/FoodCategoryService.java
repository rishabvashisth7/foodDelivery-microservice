package com.example.food.food_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.food_service.dto.FoodCategoryDTO;
import com.example.food.food_service.entity.FoodCategory;
import com.example.food.food_service.repository.FoodCategoryRepo;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoodCategoryService {

    @Autowired
    private FoodCategoryRepo foodCategoryRepository;

    public List<FoodCategoryDTO> getAllFoodCategories() {
        return foodCategoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FoodCategoryDTO getFoodCategoryById(String id) {
        return foodCategoryRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public FoodCategoryDTO createFoodCategory(FoodCategoryDTO foodCategoryDTO) {
        FoodCategory foodCategory = convertToEntity(foodCategoryDTO);
        foodCategory.setId(UUID.randomUUID().toString());
        foodCategory = foodCategoryRepository.save(foodCategory);
        return convertToDTO(foodCategory);
    }

    public void deleteFoodCategory(String id) {
        foodCategoryRepository.deleteById(id);
    }

    private FoodCategoryDTO convertToDTO(FoodCategory foodCategory) {
        FoodCategoryDTO dto = new FoodCategoryDTO();
        dto.setId(foodCategory.getId());
        dto.setName(foodCategory.getName());
        dto.setDescription(foodCategory.getDescription());
        // Map foodItems if needed
        return dto;
    }

    private FoodCategory convertToEntity(FoodCategoryDTO dto) {
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setId(dto.getId());
        foodCategory.setName(dto.getName());
        foodCategory.setDescription(dto.getDescription());
        // Map foodItems if needed
        return foodCategory;
    }
}