package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.entity.Course;
import com.example.loomlearnproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    // ADMIN + MENTOR
    @PostMapping
    @PreAuthorize("hasAnyRole('ACADEMIC_ADMIN','MENTOR')")
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.ok(service.create(course));
    }

    // ALL USERS
    @GetMapping
    @PreAuthorize("hasAnyRole('LEARNER','MENTOR','ACADEMIC_ADMIN')")
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ALL USERS
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('LEARNER','MENTOR','ACADEMIC_ADMIN')")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ADMIN + MENTOR
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ACADEMIC_ADMIN','MENTOR')")
    public ResponseEntity<Course> update(@PathVariable Long id,
                                         @RequestBody Course course) {
        return ResponseEntity.ok(service.update(id, course));
    }

    // ADMIN ONLY
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}