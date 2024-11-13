package com.example.UserManagementSystem.model;
import jakarta.validation.constraints.NotEmpty;
import org.apache.tomcat.util.codec.binary.Base64;

public class AdminDto {

    public int getId(int id) {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    @NotEmpty(message = "Name field required!")
    private String name;

    @NotEmpty(message = "Email field required!")
    private String email;

    @NotEmpty(message = "Address field required!")
    private String address;

    @NotEmpty(message = "Contact Number field required!")
    private String contactNumber;

    @NotEmpty(message = "Password field required!")
    private String password;

    @NotEmpty(message = "Role field required!")
    private String role;


    private byte[] profilPic;

    private byte[] bgPic;

    private String bio;

    private String summary;

    private String skills;

    private String education;

    private String experience;


    public @NotEmpty(message = "Name field required!") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name field required!") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Email field required!") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email field required!") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Address field required!") String getAddress() {
        return address;
    }

    public void setAddress(@NotEmpty(message = "Address field required!") String address) {
        this.address = address;
    }

    public @NotEmpty(message = "Contact Number field required!") String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(@NotEmpty(message = "Contact Number field required!") String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public @NotEmpty(message = "Password field required!") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password field required!") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "Role field required!") String getRole() {
        return role;
    }

    public void setRole(@NotEmpty(message = "Role field required!") String role) {
        this.role = role;
    }

    public byte[] getBgPic() {
        return bgPic;
    }

    public void setBgPic(byte[] bgPic) {
        this.bgPic = bgPic;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public byte[] getProfilPic() {
        return profilPic;
    }

    public void setProfilPic(byte[] profilPic) {
        this.profilPic = profilPic;
    }

    public String getImageDataBase64() {
        return Base64.encodeBase64String(this.profilPic);
    }

    public String getImageDataBase644() {
        return Base64.encodeBase64String(this.bgPic);
    }

    public void setImageDataBase64(String imageDataBase64) {
        this.profilPic = Base64.decodeBase64(imageDataBase64);
    }
}
