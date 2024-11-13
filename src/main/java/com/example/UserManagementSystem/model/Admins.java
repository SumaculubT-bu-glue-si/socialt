package com.example.UserManagementSystem.model;

import jakarta.persistence.*;
import org.apache.tomcat.util.codec.binary.Base64;


@Entity
@Table (name = "auth_user")
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "auth_user_id")
    private int id;
    @Column
    private String name;
    @Column (unique = true, nullable = false)
    private String email;
    private String address;

    @Column
    private String contactNumber;
    @Column
    private String password;
    @Column
    private String role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] profilPic;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] bgPic;
    @Column (columnDefinition = "longtext")
    private String bio;
    @Column (columnDefinition = "longtext")
    private String summary;
    @Column (columnDefinition = "longtext")
    private String skills;
    @Column (columnDefinition = "longtext")
    private String education;
    @Column (columnDefinition = "longtext")
    private String experience;


    public String getContactNumber() {
        return contactNumber;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public byte[] getProfilPic() {
        return profilPic;
    }

    public String getRole() {
        return role;
    }

    public byte[] getBgPic() {
        return bgPic;
    }

    public String getSummary() {
        return summary;
    }

    public String getBio() {
        return bio;
    }

    public String getSkills() {
        return skills;
    }

    public String getEducation() {
        return education;
    }

    public String getExperience() {
        return experience;
    }

    public Admins() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImageDataBase64() {
        return Base64.encodeBase64String(this.profilPic);
    }

    public void setProfilPic(byte[] profilPic) {
        this.profilPic = profilPic;
    }

    public String getImageDataBase644() {
        return Base64.encodeBase64String(this.bgPic);
    }

    public void setBgPic(byte[] bgPic) {
        this.bgPic = bgPic;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
