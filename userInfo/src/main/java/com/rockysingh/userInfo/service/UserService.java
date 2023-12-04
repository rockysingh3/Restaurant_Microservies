package com.rockysingh.userInfo.service;

import com.rockysingh.userInfo.dto.UserDTO;
import com.rockysingh.userInfo.entity.User;
import com.rockysingh.userInfo.mapper.UserMapper;
import com.rockysingh.userInfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public UserDTO addUser(UserDTO userDTO) {
        User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
    }


    public ResponseEntity<UserDTO> fetchUserDetailsById(int userId) {
        Optional<User> savedUser = userRepo.findById(userId);
        if(savedUser.isPresent()) {
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(savedUser.get()), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
