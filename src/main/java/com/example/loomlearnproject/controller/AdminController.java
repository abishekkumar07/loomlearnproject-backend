package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    
    @GetMapping("/pending-mentors")
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public List<AcademicUser> getPendingMentors() {
        return adminService.getPendingMentors();
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public AcademicUser approveMentor(@PathVariable Long id) {
        return adminService.approveMentor(id);
    }
}