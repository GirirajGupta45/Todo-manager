package com.example.todo.services;

import com.example.todo.DTOs.UserProfileDTO;
import com.example.todo.DTOs.UserRegistrationDTO;
import com.example.todo.Entities.User;
import com.example.todo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
  UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    public  void registerNewUser(UserRegistrationDTO userDTO){
        if (!userRepository.findByUsername(userDTO.getUsername()).isEmpty()) {
            throw new RuntimeException("Username already exists!");
        }
         String password=userDTO.getPassword();
        String encoded = encoder.encode(password);
        User user=new User(userDTO.getUsername(),encoded,userDTO.getEmail());
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        User user= userRepository.findByUsernameIgnoreCase(username);
        return user;
    }


    public UserProfileDTO getProfileByUsername(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user==null) {
            throw new RuntimeException("User not found");
        }

        UserProfileDTO profile = new UserProfileDTO();
        profile.setUsername(user.getUsername());
        profile.setEmail(user.getEmail());
        return profile;
    }
}
