package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.entity.CourseContent;
import com.example.loomlearnproject.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course-content")
public class CourseContentController {

    @Autowired
    private CourseContentService service;

    
    @PostMapping
    @PreAuthorize("hasAnyRole('ACADEMIC_ADMIN','MENTOR')")
    public ResponseEntity<CourseContent> create(@RequestBody CourseContent content) {
        return ResponseEntity.ok(service.create(content));
    }

    
    @GetMapping
    @PreAuthorize("hasAnyRole('ACADEMIC_ADMIN','MENTOR','LEARNER')")
    public ResponseEntity<List<CourseContent>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

   
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ACADEMIC_ADMIN','MENTOR','LEARNER')")
    public ResponseEntity<CourseContent> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

   
    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('ACADEMIC_ADMIN','MENTOR','LEARNER')")
    public ResponseEntity<List<CourseContent>> getByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(service.getByCourseId(courseId));
    }

    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ACADEMIC_ADMIN','MENTOR')")
    public ResponseEntity<CourseContent> update(
            @PathVariable Long id,
            @RequestBody CourseContent content) {

        return ResponseEntity.ok(service.update(id, content));
    }

   
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.ok("Course content deleted successfully");
    }
}