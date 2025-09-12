package com.example.todo.Repositories;

import com.example.todo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findByUsername(String username);
    public User findByUsernameIgnoreCase(String username);
}
