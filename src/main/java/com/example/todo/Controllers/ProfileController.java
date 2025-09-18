package com.example.todo.Controllers;

import com.example.todo.DTOs.UpdatePasswordDTO;
import com.example.todo.DTOs.UserProfileDTO;
import com.example.todo.Repositories.UserRepository;
import com.example.todo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {
    UserService userService;
    UserRepository userRepository;
    public ProfileController(UserService userService,UserRepository userRepository){
        this.userService=userService;
        this.userRepository=userRepository;
    }
    @GetMapping("/update-profile")
    String updateProfile(ModelMap model){
        String username = getLoggedInUsername();
        UserProfileDTO profile = userService.getProfileByUsername(username);
        model.addAttribute("profile", profile);
        UpdatePasswordDTO updatePasswordDTO=new UpdatePasswordDTO();
        model.addAttribute("password",updatePasswordDTO);
        return "UpdateProfile";
    }

    @PostMapping("/update-profile")
    String updateProfile(ModelMap model,@Valid @ModelAttribute("profile")UserProfileDTO userProfileDTO, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("profile", userProfileDTO);
            model.addAttribute("password", new UpdatePasswordDTO());
            model.addAttribute("passwordSection", false);
            return "UpdateProfile";
        }
        try {
            userProfileDTO.setUsername(getLoggedInUsername());
            userService.updateUserProfile(userProfileDTO);
        } catch (RuntimeException ex) {
            result.rejectValue("email", "error.profile", ex.getMessage());
            model.addAttribute("passwordSection", false);
            return "UpdateProfile";
        }

        return "redirect:/todos";
    }
    @PostMapping("/update-password")
    public String updatePassword(
            @Valid @ModelAttribute("password") UpdatePasswordDTO updatePasswordDTO,
            BindingResult result,
            ModelMap model) {

        UserProfileDTO profile = userService.getProfileByUsername(getLoggedInUsername());
        model.addAttribute("profile", profile);

        if (result.hasErrors()) {
            model.addAttribute("password", updatePasswordDTO);
            model.addAttribute("passwordSection", true);
            return "UpdateProfile";
        }

        try {
            userService.updateUserPassword(updatePasswordDTO, getLoggedInUsername());
        } catch (RuntimeException ex) {
            String message = ex.getMessage();

            if (message.contains("Current")) {
                result.rejectValue("currentPassword", "error.password", message);
            } else if (message.contains("Confirm")) {
                result.rejectValue("confirmPassword", "error.password", message);
            } else {
                result.rejectValue("newPassword", "error.password", message);
            }

            model.addAttribute("password", updatePasswordDTO);
            model.addAttribute("passwordSection", true);
            return "UpdateProfile";
        }


        return "redirect:/todos";
    }


    private String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); // returns username of logged-in user
    }
}
