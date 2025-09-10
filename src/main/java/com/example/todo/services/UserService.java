package com.example.todo.services;

import com.example.todo.DTOs.UserRegistrationDTO;
import com.example.todo.Entities.User;
import com.example.todo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
  UserRepository userRepository;
    public  void registerNewUser(UserRegistrationDTO userDTO){
        if (!userRepository.findByUsername(userDTO.getUsername()).isEmpty()) {
            throw new RuntimeException("Username already exists!");
        }
        User user=new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getEmail());
        userRepository.save(user);
    }
}
