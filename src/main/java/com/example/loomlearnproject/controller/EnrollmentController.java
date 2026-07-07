package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.dto.EnrollmentSummaryDto;
import com.example.loomlearnproject.entity.Enrollment;
import com.example.loomlearnproject.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService service;

    
    @PostMapping
    public Enrollment enroll(@RequestParam Long userId,
                             @RequestParam Long courseId) {
        return service.enroll(userId, courseId);
    }

    @GetMapping("/user/{userId}")
    public List<Enrollment> getByUser(@PathVariable Long userId) {
        return service.getByUser(userId);
    }

  
    @GetMapping("/course/{courseId}")
    public List<Enrollment> getByCourse(@PathVariable Long courseId) {
        return service.getByCourse(courseId);
    }

    @GetMapping
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public List<EnrollmentSummaryDto> getAll() {
        return service.getAllEnrollmentSummaries();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Enrollment deleted: " + id;
    }
}
