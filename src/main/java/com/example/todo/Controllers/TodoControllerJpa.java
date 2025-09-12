package com.example.todo.Controllers;

import com.example.todo.Entities.Todo;
import com.example.todo.Repositories.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoControllerJpa {

    private final TodoRepository todoRepository;

    public TodoControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // ✅ Utility method to get logged-in username
    private String getLoggedInUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName(); // returns username of logged-in user
    }

    @RequestMapping("/todos")
    public String showTodos(ModelMap model) {
        String username = getLoggedInUsername();
        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos", todos);
        return "Todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model) {
        String username = getLoggedInUsername();
        Todo todo = new Todo(0, "", username, LocalDate.now().plusYears(1), false);
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(@Valid Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todos";
    }

    @RequestMapping("/delete-todo")
    public String delete(@RequestParam int id) {
        todoRepository.deleteById(id);
        return "redirect:todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id, Model model) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            model.addAttribute("todo", todo.get());
            return "addTodo";
        }
        return "redirect:todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todos";
    }
}
