package com.example.todo.Controllers;

import com.example.todo.Beans.Todo;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TodoController {

    @RequestMapping("/add-todo")
    public String addTodo(Model model){
        model.addAttribute("todo", new Todo());
       // System.out.println("add todo vaa page ");
        return "addTodo";
    }

}
