package com.example.todo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome";  // This will load Welcome.jsp
    }
}
