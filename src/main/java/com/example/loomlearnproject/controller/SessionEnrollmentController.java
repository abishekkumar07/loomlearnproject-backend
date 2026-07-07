package com.example.loomlearnproject.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session-enrollments")
public class SessionEnrollmentController {

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id) {
        return "Session enrollment details for id: " + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "Session enrollment deleted: " + id;
    }
}