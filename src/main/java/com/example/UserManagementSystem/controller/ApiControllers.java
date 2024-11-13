package com.example.UserManagementSystem.controller;

import com.example.UserManagementSystem.model.AdminDto;
import com.example.UserManagementSystem.model.Posts;
import com.example.UserManagementSystem.model.PostsDto;
import com.example.UserManagementSystem.repo.PostRepo;
import com.example.UserManagementSystem.services.PostService;
import com.example.UserManagementSystem.services.impl.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import com.example.UserManagementSystem.model.Admins;
import com.example.UserManagementSystem.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class ApiControllers {
    @Autowired
    private PostServiceImpl postService;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PostRepo postRepo;

    @GetMapping(value = {"","/"}, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public String showAdminInfo(Model model) {
        List<Admins> admins = adminRepo.findAll();
        model.addAttribute("admins", admins);
        model.addAttribute("posts", postService.getAllBlogs());
        return "user/index";
    }

    @GetMapping("/create")
    public String showCreateAdmin(Model model){
        AdminDto adminDto = new AdminDto();
        model.addAttribute("adminDto", adminDto);
        return "mod/createAdmin";
    }

    @PostMapping("/create")
    public String createAdmin(
            @Valid @ModelAttribute AdminDto adminDto,
            BindingResult result){
        if (result.hasErrors()){
            return "mod/createAdmin";
        }
        Admins admin = adminRepo.findByEmail(adminDto.getEmail());
        if (admin != null) {
            result.addError(new FieldError("adminDto", "email",
                    "Email address is already used!")
            );
            return "mod/createAdmin";
        }
        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            Admins admins = new Admins();
            admins.setName(adminDto.getName());
            admins.setEmail(adminDto.getEmail());
            admins.setAddress(adminDto.getAddress());
            admins.setContactNumber(adminDto.getContactNumber());
            admins.setPassword(bCryptEncoder.encode(adminDto.getPassword()));
            admins.setRole(adminDto.getRole());

            adminRepo.save(admins);
        }catch (Exception e) {
            result.addError(
                    new FieldError("adminDto", "name",
                            e.getMessage())
            );
            return "mod/createAdmin";
        }
        return "redirect:/admins";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam int id)
        {
        try {
            Admins admins = adminRepo.findById((long) id).get();
            model.addAttribute("admins", admins);

            AdminDto adminDto = new AdminDto();

            adminDto.setName(admins.getName());
            adminDto.setEmail(admins.getEmail());
            adminDto.setAddress(admins.getAddress());
            adminDto.setContactNumber(admins.getContactNumber());
            adminDto.setPassword(admins.getPassword());
            adminDto.setRole(admins.getRole());

            model.addAttribute("adminDto", adminDto);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/admins";
        }
        return "mod/editAdmin";
    }

    @PostMapping("/edit")
    public String editAdmin(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute AdminDto adminDto,
            BindingResult result
    ) {
        if (result.hasErrors()){
            return "mod/editAdmin";
        }

        try{
            Admins admins = adminRepo.findById((long) id).get();
            model.addAttribute("admins", admins);

            var bCryptEncoder = new BCryptPasswordEncoder();

            admins.setName(adminDto.getName());
            admins.setEmail(adminDto.getEmail());
            admins.setAddress(adminDto.getAddress());
            admins.setContactNumber(adminDto.getContactNumber());
            admins.setPassword(bCryptEncoder.encode(adminDto.getPassword()));
            admins.setRole(adminDto.getRole());

            adminRepo.save(admins);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/admins";
    }

    @GetMapping("/delete")
    public String deleteAdmin(
            @RequestParam int id
    ){
        try{
            Admins admins = adminRepo.findById((long) id).get();
            adminRepo.delete(admins);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/admins";
    }

    @GetMapping("/signup")
    public String showSignup(){
        return "account/signup";
    }

    @GetMapping("/blog/create")
    public String showCreateBlog(Model model){
        Posts posts = new Posts();
        model.addAttribute("posts", posts);

        return "mod/createBlog";
    }

    @PostMapping(value ="/blog/save")
    public String saveBlog(@ModelAttribute Posts posts, @RequestParam("file") MultipartFile file, Model model) {
        try {
           postService.saveImage(posts, file);
           return "redirect:/admins";
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "File upload failed.");
            return "redirect:/admins";
        }
    }

    @GetMapping("/blog/edit")
    public String showEditBlogPage(
            Model model,
            @RequestParam int id)
    {
        try {
            Posts post = postRepo.findById((long) id).get();
            model.addAttribute("post", post);

            PostsDto postsDto = new PostsDto();

            postsDto.setTitle(post.getTitle());
            postsDto.setDescription(post.getDescription());
            postsDto.setType(post.getType());
            postsDto.setImageData(post.getImageData());

            model.addAttribute("postsDto", postsDto);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/admins";
        }
        return "mod/editBlog";
    }

    @PostMapping("/blog/edit")
    public String editBlog(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute Posts postsDto,
            MultipartFile file
    ) {
        try{
            Posts post = postRepo.findById((long) id).get();
            model.addAttribute("post", post);

            post.setTitle(postsDto.getTitle());
            post.setDescription(postsDto.getDescription());
            post.setType(postsDto.getType());
            post.setImageData(file.getBytes());
            Date today = Calendar.getInstance().getTime();
            post.setCreateAt(String.valueOf(today));

            postRepo.save(post);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/admins";
    }

    @GetMapping("/blog/delete")
    public String deletePost(
            @RequestParam int id
    ){
        try{
            Posts posts = postRepo.findById((long) id).get();
            postRepo.delete(posts);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/admins";
    }
}
