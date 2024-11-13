package com.example.UserManagementSystem.services;

import com.example.UserManagementSystem.model.AdminDto;

import java.util.List;

public interface AdminService {

    List<AdminDto> search(String query);
}
