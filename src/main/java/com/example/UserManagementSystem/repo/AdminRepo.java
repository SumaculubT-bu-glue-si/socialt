package com.example.UserManagementSystem.repo;

import com.example.UserManagementSystem.model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admins, Long> {

    Admins findByEmail(String email);

    Optional<Admins> findByName(String name);

    @Query("SELECT c from Admins c WHERE c.name LIKE CONCAT('%', :query, '%')")
    List<Admins> search(String query);
}