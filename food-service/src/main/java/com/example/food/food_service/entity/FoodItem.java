 package com.example.food.food_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="food_item")
public class FoodItem {
	
	@Id
	private String id;
	
	private String title;
	
	private String description;
	
	private int quantity;
	
	private boolean outOfStock= true;
	
	@Enumerated(EnumType.STRING)
	private FoodType foodType =FoodType.VEG;
	

    @ManyToOne
    private  FoodCategory foodCategory;

	@Column(nullable = false)
	private String restaurantId;
	
	
}
