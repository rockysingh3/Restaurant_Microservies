package com.rockysingh.Foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* DTO is the object that is used to pass back and fourth between the client and service layer since we don't ever
* want to pass the actual entity object.. you can also call this model
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
