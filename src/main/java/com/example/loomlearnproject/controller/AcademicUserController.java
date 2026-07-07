package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.service.AcademicUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AcademicUserController {

    @Autowired
    private AcademicUserService service;

    @PostMapping
    public AcademicUser create(@RequestBody AcademicUser user) {
        return service.save(user);
    }

    @GetMapping
    public List<AcademicUser> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AcademicUser getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "User deleted successfully";
    }
}