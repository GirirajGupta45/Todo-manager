package com.example.todo.Controllers;

import com.example.todo.DTOs.UserProfileDTO;
import com.example.todo.Entities.Todo;
import com.example.todo.Repositories.TodoRepository;
import com.example.todo.services.TodoService;
import com.example.todo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoControllerJpa {

    private final TodoRepository todoRepository;
    private final TodoService todoService;
    private final UserService userService;

    public TodoControllerJpa(TodoRepository todoRepository, TodoService todoService, UserService userService) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
        this.userService = userService;

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

        UserProfileDTO profile = userService.getProfileByUsername(username);
        model.addAttribute("profile", profile);
        return "Todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model) {
        String username = getLoggedInUsername();
        Todo todo = new Todo(0, "", username,"", LocalDateTime.now().plusYears(1), false);
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addTodo";
        }
        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todos";
    }

    @RequestMapping(value="/filtered-todos",method=RequestMethod.POST)
    public String showTodosWithFilteres(@RequestParam(required = false) String filterCategory,
                                        @RequestParam(required = false) String filterStatus,
                                        @RequestParam(required = false) String sortOrder,
                                        Model model){


        List<Todo> todos = todoService.getFilteredTodos(getLoggedInUsername(), filterCategory, filterStatus);

        if (sortOrder != null && !sortOrder.isEmpty()) {
            if ("asc".equalsIgnoreCase(sortOrder)) {
                todos.sort(Comparator.comparing(Todo::getTargetDateTime));
            } else if ("desc".equalsIgnoreCase(sortOrder)) {
                todos.sort(Comparator.comparing(Todo::getTargetDateTime).reversed());
            }
        }
        model.addAttribute("todos", todos);
        model.addAttribute("filterCategory", filterCategory);
        model.addAttribute("filterStatus", filterStatus);
        model.addAttribute("sortOrder", sortOrder);
        return "Todos";
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
