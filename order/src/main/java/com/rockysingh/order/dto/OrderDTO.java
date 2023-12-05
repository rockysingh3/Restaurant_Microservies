package com.rockysingh.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private List<FoodItemsDTO> foodItemsDTOList;
    private RestaurantDTO restaurant;
    private UserDTO userDTO;
}
