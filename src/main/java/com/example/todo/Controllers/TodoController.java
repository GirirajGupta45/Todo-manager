/*package com.example.todo.Controllers;

import com.example.todo.Beans.Todo;
import com.example.todo.services.TodoService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
  TodoService todoService;
  TodoController(TodoService todoService){
      super();
      this.todoService=todoService;
  }

    @RequestMapping("/todos")
    public String showTodos(ModelMap model){
      String username=(String)model.getAttribute("name");
        List<Todo> todos = todoService.findByUserName(username);
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
        todoService.addTodo(todo.getDescription(),(String)model.getAttribute("name"),todo.getTargetDate(),false);
        return "redirect:todos";
    }


}
*/