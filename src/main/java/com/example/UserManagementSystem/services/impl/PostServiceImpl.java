package com.example.UserManagementSystem.services.impl;

import com.example.UserManagementSystem.model.AdminDto;
import com.example.UserManagementSystem.model.Admins;
import com.example.UserManagementSystem.model.Posts;
import com.example.UserManagementSystem.model.PostsDto;
import com.example.UserManagementSystem.repo.PostRepo;
import com.example.UserManagementSystem.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    public List<Posts> getAllBlogs() {
        return postRepo.findAll();
    }

    public Posts saveImage(Posts blog, MultipartFile file) throws IOException {
        Posts posts = new Posts();
        posts.setTitle(blog.getTitle());
        posts.setDescription(blog.getDescription());
        posts.setImageData(file.getBytes());
        posts.setType(blog.getType());
        posts.setLikes(0L);
        posts.setComments(0L);
        posts.setShares(0L);

        Date today = Calendar.getInstance().getTime();
        posts.setCreateAt(String.valueOf(today));
        return postRepo.save(posts);
    }

    @Override
    public List<PostsDto> filter(String type) {
        List<Posts> posts = postRepo.filter(type);
        return posts.stream().map(post -> mapToAdminDto(post)).collect(Collectors.toList());
    }

    private PostsDto mapToAdminDto(Posts posts) {
        PostsDto postsDto = new PostsDto();
        postsDto.setTitle(posts.getTitle());
        postsDto.setDescription(posts.getDescription());
        postsDto.setType(posts.getType());
        postsDto.setCreateAt(posts.getCreateAt());
        postsDto.setLikes(posts.getLikes());
        postsDto.setComments(posts.getComments());
        postsDto.setShares(posts.getShares());
        postsDto.setImageDataBase64(posts.getImageDataBase64());

        return postsDto;
    }
}
