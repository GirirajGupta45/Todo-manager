package com.example.todo.Repositories;

import com.example.todo.Entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    public List<Todo> findByUsername(String username);

    @Query("SELECT t FROM Todo t " +
            "WHERE t.username = :username " +
            "AND (:category IS NULL OR t.category = :category) " +
            "AND (:done IS NULL OR t.done = :done)")
    List<Todo> findTodosByFilters(@Param("username") String username,
                                  @Param("category") String category,
                                  @Param("done") Boolean done);


}
