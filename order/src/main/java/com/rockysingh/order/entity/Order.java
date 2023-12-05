package com.rockysingh.order.entity;

import com.rockysingh.order.dto.FoodItemsDTO;
import com.rockysingh.order.dto.RestaurantDTO;
import com.rockysingh.order.dto.UserDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    private Integer orderId;
    private List<FoodItemsDTO> foodItemsDTOList;
    private RestaurantDTO restaurant;
    private UserDTO userDTO;
}
