package com.rockysingh.userInfo.controller;

import com.rockysingh.userInfo.dto.UserDTO;
import com.rockysingh.userInfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUserById/{userId}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Integer userId) {
//        we can return fetchUserById() detials cuz the service layer is sending us the responseEntity
//        the method above we had to return the reponseEntity cuz the service layer wasn't sending the entity
        return userService.fetchUserDetailsById(userId);
    }
}
