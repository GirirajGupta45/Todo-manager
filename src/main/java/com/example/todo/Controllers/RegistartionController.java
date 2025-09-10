package com.example.todo.Controllers;

import com.example.todo.DTOs.UserRegistrationDTO;
import com.example.todo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistartionController {
  UserService userService;
  RegistartionController(UserService userService){
      this.userService=userService;
  }
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String showRegistration(ModelMap model){
        model.addAttribute("userDto",new UserRegistrationDTO());
        return "Register";
    }
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String showRegistration(@Valid @ModelAttribute("userDto") UserRegistrationDTO userDto,
                                   BindingResult result){
        if (result.hasErrors()) {
            return "Register";
        }
     try{
        userService.registerNewUser(userDto);
     }catch (RuntimeException e) {
         result.rejectValue("username", "error.userDto", e.getMessage());
         return "Register"; // redisplay form with error
     }
     return "redirect:/login";
    }

}

