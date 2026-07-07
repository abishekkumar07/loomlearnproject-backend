package com.example.loomlearnproject.controller;

import com.example.loomlearnproject.entity.StudySubject;
import com.example.loomlearnproject.service.StudySubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class StudySubjectController {

    @Autowired
    private StudySubjectService service;

    @PostMapping
    public StudySubject create(@RequestBody StudySubject subject) {
        return service.save(subject);
    }

    @GetMapping
    public List<StudySubject> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StudySubject getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Subject deleted successfully";
    }
}