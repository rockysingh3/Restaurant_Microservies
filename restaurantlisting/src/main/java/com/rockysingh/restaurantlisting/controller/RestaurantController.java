package com.rockysingh.restaurantlisting.controller;

import com.rockysingh.restaurantlisting.dto.RestaurantDTO;
import com.rockysingh.restaurantlisting.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/fetchAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants() {
        List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO restaurantAdded = restaurantService.addRestaurantInDB(restaurantDTO);

        return new ResponseEntity<>(restaurantAdded, HttpStatus.OK);
    }

    @GetMapping("fetchById/{id}")
    public ResponseEntity<RestaurantDTO> fetchById(@PathVariable Integer id) {
        return restaurantService.fetchRestaurantById(id);
    }


}
