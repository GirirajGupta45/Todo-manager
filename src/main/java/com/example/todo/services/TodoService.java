package com.example.todo.services;

import com.example.todo.Entities.Todo;
import com.example.todo.Repositories.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@SessionAttributes("name")
@Service
public class TodoService {
    public static final List<Todo> todos=new ArrayList<>();
    public static int todosCount=0;

    TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository){
        this.todoRepository=todoRepository;
    }
    public void addTodo(String description, String username,String category, LocalDateTime targetDateTime, boolean done) {
        Todo todo = new Todo(++todosCount,description,username,category,targetDateTime,done);
        todos.add(todo);
    }
    public void addTodo(Todo todo){
    addTodo(todo.getDescription(),todo.getUsername(),todo.getCategory(),todo.getTargetDateTime(),todo.isDone());
    }
    public List<Todo> findByUserName(String username){
        Predicate<? super Todo> predicate =
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public List<Todo> getFilteredTodos(String username, String filterCategory, String filterStatus) {
        Boolean done = null;

        if ("done".equalsIgnoreCase(filterStatus)) {
            done = true;
        } else if ("notDone".equalsIgnoreCase(filterStatus)) {
            done = false;
        }

        String category = (filterCategory != null && !filterCategory.isEmpty()) ? filterCategory : null;

        return todoRepository.findTodosByFilters(username, category, done);
    }

}
