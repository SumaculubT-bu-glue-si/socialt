package com.example.UserManagementSystem.services;

import com.example.UserManagementSystem.model.PostsDto;

import java.util.List;

public interface PostService {

    List<PostsDto> filter(String type);
}
