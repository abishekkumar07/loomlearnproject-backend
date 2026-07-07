package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.StudySubject;
import com.example.loomlearnproject.exception.BusinessValidationException;
import com.example.loomlearnproject.exception.ResourceNotFoundException;
import com.example.loomlearnproject.repository.StudySubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudySubjectService {

    @Autowired
    private StudySubjectRepository repo;

    
    public StudySubject save(StudySubject subject) {

        if (repo.findByName(subject.getName()).isPresent()) {
            throw new BusinessValidationException("Subject already exists");
        }

        return repo.save(subject);
    }

    public List<StudySubject> getAll() {
        return repo.findAll();
    }

    
    public StudySubject getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
    }

    
    public void delete(Long id) {

        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Subject not found with id: " + id);
        }

        repo.deleteById(id);
    }
}