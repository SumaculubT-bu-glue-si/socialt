package com.example.UserManagementSystem.model;

import org.apache.tomcat.util.codec.binary.Base64;

public class PostsDto {

    private String createAt;
    private String title;
    private String description;
    private String type;
    private Long likes;
    private Long comments;
    private Long shares;

    private byte[] imageData;

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public Long getShares() {
        return shares;
    }

    public void setShares(Long shares) {
        this.shares = shares;
    }

    public String getImageDataBase64() {
        return Base64.encodeBase64String(this.imageData);
    }

    public void setImageDataBase64(String imageDataBase64) {
        this.imageData = Base64.decodeBase64(imageDataBase64);
    }
}
