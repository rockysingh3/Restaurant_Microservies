package com.rockysingh.restaurantlisting.service;

import com.rockysingh.restaurantlisting.dto.RestaurantDTO;
import com.rockysingh.restaurantlisting.entity.Restaurant;
import com.rockysingh.restaurantlisting.mapper.RestaurantMapper;
import com.rockysingh.restaurantlisting.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {


    @Autowired
    RestaurantRepo restaurantRepo;


    /*

    gets all the restaurants from the DB and returns them as a Restaurant DTO

    */

    public List<RestaurantDTO> findAllRestaurants() {

        List<Restaurant> restaurants = restaurantRepo.findAll();

        // the mapper converts it from a list of restaurants to restaurantDTO
        List<RestaurantDTO> restaurantDTOS = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());

        return restaurantDTOS;
    }


    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {

        // We convert the restaurant from a DTO to restaurant entity and then save it
        Restaurant savedRestaurant = restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));

        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }



    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {

        /*
        * using optional cuz the restaurant could also not be in the DB
        *
        * */

//        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
//
//        if(restaurant.isPresent()) {
////            .get() is also part of isPresent()
//            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
//        }

        Optional<Restaurant> restaurant = restaurantRepo.findById(id);

        if(restaurant.isPresent()) {
            // map it to a DTO
            RestaurantDTO restaurant1 = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get());
            // return the the DTO as a responseEntity
            return new ResponseEntity<>(restaurant1, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
