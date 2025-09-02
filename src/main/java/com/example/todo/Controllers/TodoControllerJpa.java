package com.example.todo.Controllers;

import com.example.todo.Beans.Todo;
import com.example.todo.Repositories.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SessionAttributes("name")

@Controller
public class TodoControllerJpa {
private TodoRepository todoRepository;
public TodoControllerJpa(TodoRepository todoRepository){
    this.todoRepository=todoRepository;
}
    @RequestMapping("/todos")
    public String showTodos(ModelMap model){
        String username=(String)model.getAttribute("name");
        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos", todos);
        return "Todos";
    }
    @RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String addTodo(ModelMap model){
        Todo todo=new Todo(0,"",(String)model.getAttribute("name"), LocalDate.now().plusYears(1),false);
        model.addAttribute("todo", todo);
        return "addTodo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model,Todo todo){
//    todo.setDone(false);
        String username=(String)model.getAttribute("name");
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todos";
    }
    @RequestMapping("/delete-todo")
    public String delete(@RequestParam int id){
    todoRepository.deleteById(id);
    return "redirect:todos";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.GET)
    public String updateTodo(@RequestParam int id, Model model){
        Optional<Todo> todo=todoRepository.findById(id);
        if(todo.isPresent()){
            model.addAttribute("todo", todo.get());
            return "addTodo";
        }
        return "todos";
    }
    @RequestMapping(value="/update-todo", method = RequestMethod.POST)
    public String updateTodo(Model model,Todo todo){
        String username=(String)model.getAttribute("name");
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:todos";
    }
}
