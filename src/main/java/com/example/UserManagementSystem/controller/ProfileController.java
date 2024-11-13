package com.example.UserManagementSystem.controller;

import com.example.UserManagementSystem.model.AdminDto;
import com.example.UserManagementSystem.model.Admins;
import com.example.UserManagementSystem.repo.AdminRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AdminRepo adminRepo;

    @GetMapping("")
    public String showEditBio(
            Model model,
            Principal principal, Authentication auth)
    {
        try {
            Admins user = adminRepo.findByEmail(principal.getName());
            model.addAttribute("user",user);
            AdminDto adminDto = new AdminDto();

            adminDto.setBio(user.getBio());

            adminDto.setAddress(user.getAddress());
            adminDto.setName(user.getName());
            adminDto.setPassword(user.getPassword());
            adminDto.setRole(user.getRole());
            adminDto.setEmail(user.getEmail());
            adminDto.setContactNumber(user.getContactNumber());

            model.addAttribute("adminDto", adminDto);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/profile";
        }
        return "user/profile";
    }

    @PostMapping("")
    public String EditBio(
            Model model,
            @Valid @ModelAttribute AdminDto adminDto,
            Principal principal
    )
    {
        try {
            Admins users = adminRepo.findByEmail(principal.getName());
            model.addAttribute("users", users);

            users.setBio(adminDto.getBio());

            users.setAddress(adminDto.getAddress());
            users.setName(adminDto.getName());
            users.setPassword(adminDto.getPassword());
            users.setRole(adminDto.getRole());
            users.setEmail(adminDto.getEmail());
            users.setContactNumber(adminDto.getContactNumber());

            adminRepo.save(users);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/profile";
    }

    @PostMapping("/profileimg")
    public String EditProfileImg(
            Model model,
            @Valid @ModelAttribute AdminDto adminDto,
            Principal principal,
            @RequestParam("file") MultipartFile file
    )
    {
        try {
            Admins users = adminRepo.findByEmail(principal.getName());
            model.addAttribute("users", users);

            users.setAddress(adminDto.getAddress());
            users.setName(adminDto.getName());
            users.setPassword(adminDto.getPassword());
            users.setRole(adminDto.getRole());
            users.setEmail(adminDto.getEmail());
            users.setContactNumber(adminDto.getContactNumber());
            users.setProfilPic(file.getBytes());

            adminRepo.save(users);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/profile";
    }

    @PostMapping("/bgimg")
    public String EditBgImg(
            Model model,
            @Valid @ModelAttribute AdminDto adminDto,
            Principal principal,
            @RequestParam("file") MultipartFile file
    )
    {
        try {
            Admins users = adminRepo.findByEmail(principal.getName());
            model.addAttribute("users", users);

            users.setAddress(adminDto.getAddress());
            users.setName(adminDto.getName());
            users.setPassword(adminDto.getPassword());
            users.setRole(adminDto.getRole());
            users.setEmail(adminDto.getEmail());
            users.setContactNumber(adminDto.getContactNumber());
            users.setBgPic(file.getBytes());

            adminRepo.save(users);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/profile";
    }
}
