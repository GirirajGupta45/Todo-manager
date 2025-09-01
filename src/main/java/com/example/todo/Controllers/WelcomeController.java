package com.example.todo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("name")
@Controller
public class WelcomeController {
    @RequestMapping("/")
    public String welcome(ModelMap model) {
        model.addAttribute("name","Giriraj Gupta");
        return "Welcome";
    }

}
