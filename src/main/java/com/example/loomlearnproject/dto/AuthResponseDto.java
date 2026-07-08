package com.example.loomlearnproject.dto;

public class AuthResponseDto {

    private Long id;
    private String token;
    private String fullName;
    private String email;
    private String role;

    public AuthResponseDto(Long id, String token, String fullName, String email, String role) {
        this.id = id;
        this.token = token;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}