package com.example.loomlearnproject.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @GetMapping("/{id}")
    public String getSession(@PathVariable Long id) {
        return "Session details for id: " + id;
    }

    @PostMapping
    public String createSession() {
        return "Session created";
    }

    @PutMapping("/{id}")
    public String updateSession(@PathVariable Long id) {
        return "Session updated with id: " + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return "Session deleted with id: " + id;
    }
}