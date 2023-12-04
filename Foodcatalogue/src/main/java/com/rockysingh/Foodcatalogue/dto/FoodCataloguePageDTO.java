package com.rockysingh.Foodcatalogue.dto;

import com.rockysingh.Foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePageDTO {

    private List<FoodItem> foodItemsList;
    private RestaurantDTO restaurantDTO;
}
