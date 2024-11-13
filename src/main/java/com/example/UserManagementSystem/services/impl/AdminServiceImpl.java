package com.example.UserManagementSystem.services.impl;

import com.example.UserManagementSystem.model.AdminDto;
import com.example.UserManagementSystem.model.Admins;
import com.example.UserManagementSystem.repo.AdminRepo;
import com.example.UserManagementSystem.services.AdminService;
import com.zaxxer.hikari.pool.HikariProxyCallableStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdminServiceImpl implements UserDetailsService, AdminService {
    @Autowired
    private AdminRepo adminRepo;

    private AdminDto adminDto;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admins admins = adminRepo.findByEmail(email);

        if (admins == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return User.withUsername(admins.getEmail())
                .password(admins.getPassword())
                .roles(admins.getRole())
                .build();
    }

    @Override
    public List<AdminDto> search(String query) {
        List<Admins> admins = adminRepo.search(query);
        return admins.stream().map(admin -> mapToAdminDto(admin)).collect(Collectors.toList());
    }

    private AdminDto mapToAdminDto(Admins admin) {
        AdminDto adminDto = new AdminDto();

        adminDto.setImageDataBase64(admin.getImageDataBase64());
        adminDto.getImageDataBase64();
        adminDto.setName(admin.getName());
        adminDto.setEmail(admin.getEmail());
        adminDto.setAddress(admin.getAddress());
        adminDto.setContactNumber(admin.getContactNumber());
        adminDto.setPassword(admin.getPassword());
        adminDto.setRole(admin.getRole());

        return adminDto;
    }
}




