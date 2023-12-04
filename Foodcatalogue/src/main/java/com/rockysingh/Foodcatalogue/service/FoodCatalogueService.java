package com.rockysingh.Foodcatalogue.service;

import com.rockysingh.Foodcatalogue.dto.FoodCataloguePageDTO;
import com.rockysingh.Foodcatalogue.dto.FoodItemDTO;
import com.rockysingh.Foodcatalogue.dto.RestaurantDTO;
import com.rockysingh.Foodcatalogue.entity.FoodItem;
import com.rockysingh.Foodcatalogue.mapper.FoodItemMapper;
import com.rockysingh.Foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSaved = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));

        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItemSaved);
    }

    public FoodCataloguePageDTO fetchFoodCataloguePageDetails(Integer restaurantId) {

        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        RestaurantDTO restaurantDTO = fetchResaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurantDTO);
    }

    /*
    * FoodCataloguePage is a DTO made up list of foods and a single restaurant details
    * combines the list of food and restaurant by there id
    *
    * */
    private FoodCataloguePageDTO createFoodCataloguePage(List<FoodItem> foodItemList, RestaurantDTO restaurantDTO) {
        FoodCataloguePageDTO foodCataloguePageDTO = new FoodCataloguePageDTO();
        foodCataloguePageDTO.setFoodItemsList(foodItemList);
        foodCataloguePageDTO.setRestaurantDTO(restaurantDTO);
        return foodCataloguePageDTO;
    }
    private RestaurantDTO fetchResaurantDetailsFromRestaurantMS(Integer restaurantId) {

        /*
        * Gets the object from restaurant-service and returns a restaurantDTO
        *
        * Cuz of loadbalancing we can tell Eurka what service to use in the URL (dont have to define the port number)
        * */
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, RestaurantDTO.class);

    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {

        return foodItemRepo.findByRestaurantId(restaurantId);

    }
}
