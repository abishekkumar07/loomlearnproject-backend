package com.example.loomlearnproject.dto;

import com.example.loomlearnproject.entity.AcademicUser;

public class RegisterDto {

    private String fullName;
    private String email;
    private String password;
    private AcademicUser.UserRole role;
    private String department;
    private String bio;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AcademicUser.UserRole getRole() {
        return role;
    }

    public void setRole(AcademicUser.UserRole role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}