package com.example.todo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "Login"; // Spring will resolve -> /WEB-INF/JSP/Login.jsp
    }
}
