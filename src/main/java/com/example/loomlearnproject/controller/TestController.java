package com.example.loomlearnproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public String admin() {
        return "✅ Admin Access Granted";
    }

    @GetMapping("/mentor")
    @PreAuthorize("hasRole('MENTOR')")
    public String mentor() {
        return "✅ Mentor Access Granted";
    }

    @GetMapping("/learner")
    @PreAuthorize("hasRole('LEARNER')")
    public String learner() {
        return "✅ Learner Access Granted";
    }
}