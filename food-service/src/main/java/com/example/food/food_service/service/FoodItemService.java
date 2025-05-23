package com.example.food.food_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.food.food_service.dto.FoodCategoryDTO;
import com.example.food.food_service.dto.FoodItemDTO;
import com.example.food.food_service.dto.RestaurantDto;
import com.example.food.food_service.entity.FoodCategory;
import com.example.food.food_service.entity.FoodItem;
import com.example.food.food_service.repository.FoodCategoryRepo;
import com.example.food.food_service.repository.FoodItemRepo;
import com.example.food.food_service.service.external.RestWebClientService;
import com.example.food.food_service.service.external.RestaurantService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepo foodItemRepository;
    @Autowired
    private FoodCategoryRepo foodCategoryRepository;

//    @Autowired
//    private RestTemplate restTemplate;
    
    @Autowired
    private RestaurantService restaurantService;
    
//    @Autowired
//    private WebClient webClient;

    @Autowired
    private RestWebClientService restWebClientService;

    public List<FoodItemDTO> getAllFoodItems() {
        return foodItemRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FoodItemDTO getFoodItemById(String id) {

        FoodItem foodItem = foodItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find food item with id: " + id));

        //calling restaurant services using Rest template
//        String restautantServiceUrl = "http://localhost:9091/api/v1/restaurants/" + foodItem.getRestaurantId();
//        RestaurantDto restaurantDto =  restTemplate.getForObject(restautantServiceUrl, RestaurantDto.class);

        //call using feign client
//        RestaurantDto restaurantDto= restaurantService.getRestaurantById(foodItem.getRestaurantId());

        
//        RestaurantDto restaurantDto = webClient.get()
//        		 .uri("/api/v1/restaurants/{id}",foodItem.getRestaurantId())
//        		 .retrieve()
//        		 .bodyToMono(RestaurantDto.class)
//        		 .block();
        
        RestaurantDto restaurantDto = restWebClientService.getById(foodItem.getRestaurantId());
        
        FoodItemDTO foodItemDTO = convertToDTO(foodItem);
        foodItemDTO.setRestaurant(restaurantDto);
        return foodItemDTO;


    }

    public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = convertToEntity(foodItemDTO);
        foodItem.setId(UUID.randomUUID().toString());


        FoodCategory foodCategory = foodCategoryRepository.findById(foodItemDTO.getFoodCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));

        foodItem.setFoodCategory(foodCategory);

        foodItem = foodItemRepository.save(foodItem);
        return convertToDTO(foodItem);
    }

    public void deleteFoodItem(String id) {
        foodItemRepository.deleteById(id);
    }

    private FoodItemDTO convertToDTO(FoodItem foodItem) {
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(foodItem.getId());
        dto.setTitle(foodItem.getTitle());
        dto.setDescription(foodItem.getDescription());
        dto.setQuantity(foodItem.getQuantity());
        dto.setOutOfStock(foodItem.isOutOfStock());
        dto.setFoodType(foodItem.getFoodType());
        dto.setFoodCategoryId(foodItem.getFoodCategory().getId());
        FoodCategoryDTO foodCategoryDTO = new FoodCategoryDTO();
        foodCategoryDTO.setId(foodItem.getFoodCategory().getId());
        foodCategoryDTO.setName(foodItem.getFoodCategory().getName());
        foodCategoryDTO.setDescription(foodItem.getFoodCategory().getDescription());
        dto.setFoodCategory(foodCategoryDTO);

        dto.setRestaurantId(foodItem.getRestaurantId());
        return dto;
    }

    private FoodItem convertToEntity(FoodItemDTO dto) {
        FoodItem foodItem = new FoodItem();
        foodItem.setId(dto.getId());
        foodItem.setTitle(dto.getTitle());
        foodItem.setDescription(dto.getDescription());
        foodItem.setQuantity(dto.getQuantity());
        foodItem.setOutOfStock(dto.isOutOfStock());
        foodItem.setFoodType(dto.getFoodType());
        foodItem.setRestaurantId(dto.getRestaurantId());
        // Set foodCategory and restaurantId as needed
        return foodItem;
    }
}