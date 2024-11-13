package com.example.UserManagementSystem.repo;

import com.example.UserManagementSystem.model.Admins;
import com.example.UserManagementSystem.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Posts, Long> {
    Posts findByType(String type);

    @Query("SELECT p from Posts p WHERE p.type = :type")
    List<Posts> filter(String type);
}
