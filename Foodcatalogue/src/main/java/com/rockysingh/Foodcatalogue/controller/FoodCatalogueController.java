package com.rockysingh.Foodcatalogue.controller;

import com.rockysingh.Foodcatalogue.dto.FoodCataloguePageDTO;
import com.rockysingh.Foodcatalogue.dto.FoodItemDTO;
import com.rockysingh.Foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {

        FoodItemDTO foodItemSaved = foodCatalogueService.addFoodItem(foodItemDTO);

        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePageDTO> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId) {

        FoodCataloguePageDTO foodCataloguePageDTO = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
        /*
        * FoodCataloguePage is a DTO of list of foods and restaurant details
        * */
        return new ResponseEntity<>(foodCataloguePageDTO, HttpStatus.OK);
    }
}
