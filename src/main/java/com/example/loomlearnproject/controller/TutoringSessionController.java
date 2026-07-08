package com.example.loomlearnproject.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutoring-sessions")
public class TutoringSessionController {

    @GetMapping("/{id}")
    public String getTutoringSession(@PathVariable Long id) {
        return "Tutoring session details for id: " + id;
    }

    @PostMapping
    public String createTutoringSession() {
        return "Tutoring session created";
    }

    @PutMapping("/{id}")
    public String updateTutoringSession(@PathVariable Long id) {
        return "Tutoring session updated with id: " + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "Tutoring session deleted with id: " + id;
    }
}