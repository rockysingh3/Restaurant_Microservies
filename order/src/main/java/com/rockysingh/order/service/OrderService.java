package com.rockysingh.order.service;

import com.rockysingh.order.dto.OrderDTO;
import com.rockysingh.order.dto.OrderDTOFromFE;
import com.rockysingh.order.dto.UserDTO;
import com.rockysingh.order.entity.Order;
import com.rockysingh.order.mapper.OrderMapper;
import com.rockysingh.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrderInDB(OrderDTOFromFE orderDetails) {

//        increment the sequence
        Integer newOrderID = sequenceGenerator.generateNextOrderId();

//        get the details for the User microservice
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());

//        create an order object
        Order orderToBeSaved = new Order(newOrderID, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO);

//        save it to the db
        orderRepo.save(orderToBeSaved);

//        map it back to a dto and it to the controller
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToBeSaved);

    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {

        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/"+userId, UserDTO.class);
    }
}
