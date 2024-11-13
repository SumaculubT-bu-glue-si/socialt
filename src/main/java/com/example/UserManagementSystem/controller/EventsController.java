package com.example.UserManagementSystem.controller;

import com.example.UserManagementSystem.model.AdminDto;
import com.example.UserManagementSystem.model.Admins;
import com.example.UserManagementSystem.model.Posts;
import com.example.UserManagementSystem.model.PostsDto;
import com.example.UserManagementSystem.repo.AdminRepo;
import com.example.UserManagementSystem.repo.PostRepo;
import com.example.UserManagementSystem.services.PostService;
import com.example.UserManagementSystem.services.impl.AdminServiceImpl;
import com.example.UserManagementSystem.services.impl.PostServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventsController {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private PostRepo postRepo;

    @GetMapping({"", "/"})
    public String showEvents(Model model) {
        model.addAttribute("posts", postService.getAllBlogs());

        List<Admins> admin = adminRepo.findAll();
        List<String> list = admin.stream()
                .map(Admins::getEmail)
                .collect(Collectors.toList());
        model.addAttribute("list", new Gson().toJson(list));

        return "landing/events";
    }

    @GetMapping("/filter")
    public String showFilteredPost(@RequestParam(value = "type") String type, Model model) {

        List<PostsDto> postDto = postService.filter(type);
        model.addAttribute("postDto", postDto);

        List<Admins> admin = adminRepo.findAll();
        List<String> list = admin.stream()
                .map(Admins::getEmail)
                .collect(Collectors.toList());
        model.addAttribute("list", new Gson().toJson(list));

        return "landing/filteredPost";
    }



    @GetMapping("/search")
    public String search(@RequestParam(value = "query") String query, Model model){
        List<AdminDto> admin = adminServiceImpl.search(query);
        model.addAttribute("admin", admin);

        return "landing/search";
    }

}