package com.example.loomlearnproject.service;

import com.example.loomlearnproject.dto.AuthRequestDto;
import com.example.loomlearnproject.dto.AuthResponseDto;
import com.example.loomlearnproject.dto.RegisterDto;
import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.exception.BusinessValidationException;
import com.example.loomlearnproject.exception.ResourceNotFoundException;
import com.example.loomlearnproject.repository.AcademicUserRepository;
import com.example.loomlearnproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AcademicAuthService {

    @Autowired
    private AcademicUserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ================= REGISTER =================
    public AuthResponseDto register(RegisterDto dto) {

        if (repo.existsByEmail(dto.getEmail())) {
            throw new BusinessValidationException("Email already exists");
        }

        AcademicUser user = new AcademicUser();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));

        // SAFE ROLE
        user.setRole(
                dto.getRole() != null ?
                        dto.getRole() :
                        AcademicUser.UserRole.LEARNER
        );

        user.setDepartment(dto.getDepartment());
        user.setBio(dto.getBio());

        // STATUS LOGIC
        if (user.getRole() == AcademicUser.UserRole.MENTOR) {
            user.setStatus(AcademicUser.UserStatus.PENDING);
        } else {
            user.setStatus(AcademicUser.UserStatus.APPROVED);
        }

        AcademicUser saved = repo.save(user);

        String token = jwtUtil.generateToken(saved.getEmail());

        return new AuthResponseDto(
                saved.getId(),
                token,
                saved.getFullName(),
                saved.getEmail(),
                saved.getRole().name()
        );
    }

    // ================= LOGIN =================
    public AuthResponseDto login(AuthRequestDto dto) {

        AcademicUser user = repo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BusinessValidationException("Invalid password");
        }

        if (user.getStatus() == AcademicUser.UserStatus.PENDING ||
            user.getStatus() == AcademicUser.UserStatus.BLOCKED) {
            throw new BusinessValidationException("Account not active");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDto(
                user.getId(),
                token,
                user.getFullName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}