package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.Course;
import com.example.loomlearnproject.exception.BusinessValidationException;
import com.example.loomlearnproject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repo;

    public Course create(Course course) {
        course.setTitle(clean(course.getTitle()));
        course.setCategory(clean(course.getCategory()));
        course.setDescription(clean(course.getDescription()));

        if (repo.findAll().stream().anyMatch(existing -> courseKey(existing).equals(courseKey(course)))) {
            throw new BusinessValidationException("Course already exists");
        }
        return repo.save(course);
    }

    public List<Course> getAll() {
        Map<String, Course> uniqueCourses = new LinkedHashMap<>();
        for (Course course : repo.findAll()) {
            uniqueCourses.putIfAbsent(courseKey(course), course);
        }
        return new ArrayList<>(uniqueCourses.values());
    }

    public Course getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course update(Long id, Course course) {
        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        existing.setCategory(course.getCategory());
        existing.setPrice(course.getPrice());

        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        repo.deleteById(id);
    }

    private String courseKey(Course course) {
        return clean(course.getTitle()).toLowerCase();
    }

    private String clean(String value) {
        return value == null ? "" : value.trim();
    }
}
