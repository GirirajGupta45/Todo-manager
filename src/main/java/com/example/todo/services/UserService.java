package com.example.todo.services;

import com.example.todo.DTOs.UpdatePasswordDTO;
import com.example.todo.DTOs.UserProfileDTO;
import com.example.todo.DTOs.UserRegistrationDTO;
import com.example.todo.Entities.User;
import com.example.todo.Repositories.UserRepository;
import jakarta.validation.Valid;
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

    public void updateUserProfile(@Valid UserProfileDTO userProfileDTO) {
        User user = userRepository.findByUsernameIgnoreCase(userProfileDTO.getUsername());
        if (user == null) throw new RuntimeException("User not found!");

        user.setUsername(userProfileDTO.getUsername());
        user.setEmail(userProfileDTO.getEmail());
        userRepository.save(user);
    }

    public void updateUserPassword(@Valid UpdatePasswordDTO updatePasswordDTO,String username) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user==null) {
            throw new RuntimeException("User not found");
        }
        // 1. Check if current password is correct
        if (!encoder.matches(updatePasswordDTO.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        // 2. Check if new password is same as old
        if (encoder.matches(updatePasswordDTO.getNewPassword(), user.getPassword())) {
            throw new RuntimeException("New password cannot be same as the current password");
        }

        // 3. Check if new password and confirmation match
        if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("New password and Confirm Password do not match");
        }

        // 4. Update and save
        user.setPassword(encoder.encode(updatePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }
}
