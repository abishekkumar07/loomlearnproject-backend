package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

  
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ACADEMIC_ADMIN')")
    public Map<String, Object> adminDashboard() {
        return dashboardService.getAdminDashboard();
    }

   
    @GetMapping("/mentor/{id}")
    @PreAuthorize("hasRole('MENTOR')")
    public Map<String, Object> mentorDashboard(@PathVariable Long id) {
        return dashboardService.getMentorDashboard(id);
    }

   
    @GetMapping("/learner/{id}")
    @PreAuthorize("hasRole('LEARNER')")
    public Map<String, Object> learnerDashboard(@PathVariable Long id) {
        return dashboardService.getLearnerDashboard(id);
    }
}