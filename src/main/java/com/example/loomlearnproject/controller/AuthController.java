package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.dto.AuthRequestDto;
import com.example.loomlearnproject.dto.AuthResponseDto;
import com.example.loomlearnproject.dto.RegisterDto;
import com.example.loomlearnproject.service.AcademicAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AcademicAuthService authService;

    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
        AuthResponseDto response = authService.register(dto);
        return ResponseEntity.ok(response);
    }

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto dto) {
        AuthResponseDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }
}