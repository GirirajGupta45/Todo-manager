package com.example.todo.services;

import com.example.todo.Entities.Todo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@SessionAttributes("name")
@Service
public class TodoService {
    public static final List<Todo> todos=new ArrayList<>();
    public static int todosCount=0;


    public void addTodo(String description, String username, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount,description,username,targetDate,done);
        todos.add(todo);
    }
    public void addTodo(Todo todo){
    addTodo(todo.getDescription(),todo.getUsername(),todo.getTargetDate(),todo.isDone());
    }
    public List<Todo> findByUserName(String username){
        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
}
