package com.example.UserManagementSystem.controller;

import com.example.UserManagementSystem.model.AdminDto;
import com.example.UserManagementSystem.model.Admins;
import com.example.UserManagementSystem.repo.AdminRepo;
import com.google.gson.Gson;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AdminRepo adminRepo;

    @GetMapping("/register")
    public String showHome(Model model) {
        AdminDto adminDto = new AdminDto();
        model.addAttribute("adminDto", adminDto);
        return "account/register";
    }

    @PostMapping("/register")
    public String createUser(
            @Valid @ModelAttribute AdminDto adminDto,
            BindingResult result) {
        Admins admin = adminRepo.findByEmail(adminDto.getEmail());
        if (admin != null) {
            result.addError(new FieldError("adminDto", "email",
                    "Email address is already used!")
            );
            return "account/register";
        }
        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            Admins admins = new Admins();
            admins.setName(adminDto.getName());
            admins.setEmail(adminDto.getEmail());
            admins.setAddress(adminDto.getAddress());
            admins.setContactNumber(adminDto.getContactNumber());
            admins.setPassword(bCryptEncoder.encode(adminDto.getPassword()));
            admins.setRole("Client");

            adminRepo.save(admins);
        } catch (Exception e) {
            result.addError(
                    new FieldError("adminDto", "name",
                            e.getMessage())
            );
            return "account/register";
        }
        return "redirect:/home";
    }

    @GetMapping("/user")
    public String showUserPage(Model model, @RequestParam String email)
    {
        try {
            Admins admins = adminRepo.findByEmail(email);
            model.addAttribute("admins", admins);

            List<Admins> admin = adminRepo.findAll();
            List<String> list = admin.stream()
                    .map(Admins::getEmail)
                    .collect(Collectors.toList());

            model.addAttribute("list", new Gson().toJson(list));
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/home";
        }
        return "landing/user";
    }
}

