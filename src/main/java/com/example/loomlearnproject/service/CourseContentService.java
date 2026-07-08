package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.CourseContent;
import com.example.loomlearnproject.exception.ResourceNotFoundException;
import com.example.loomlearnproject.repository.CourseContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentService {

    @Autowired
    private CourseContentRepository repository;

   
    public CourseContent create(CourseContent content) {
        return repository.save(content);
    }

   
    public List<CourseContent> getAll() {
        return repository.findAll();
    }

    public CourseContent getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course content not found with id: " + id));
    }

    
    public List<CourseContent> getByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    public CourseContent update(Long id, CourseContent content) {

        CourseContent existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course content not found with id: " + id));

        existing.setTitle(content.getTitle());
        existing.setDescription(content.getDescription());
        existing.setVideoUrl(content.getVideoUrl());
        existing.setPdfUrl(content.getPdfUrl());
        existing.setCourseId(content.getCourseId());

        return repository.save(existing);
    }

   
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Course content not found with id: " + id);
        }

        repository.deleteById(id);
    }
}