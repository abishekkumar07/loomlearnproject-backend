package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.entity.MentorFeedback;
import com.example.loomlearnproject.service.MentorFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class MentorFeedbackController {

    @Autowired
    private MentorFeedbackService service;

    @PostMapping
    public MentorFeedback create(@RequestBody MentorFeedback feedback) {
        return service.save(feedback);
    }

    @GetMapping
    public List<MentorFeedback> getAll() {
        return service.getAll();
    }

    @GetMapping("/mentor/{mentorId}")
    public List<MentorFeedback> getByMentor(@PathVariable Long mentorId) {
        return service.getByMentorId(mentorId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Feedback deleted successfully";
    }
}