package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.SessionEnrollment;
import com.example.loomlearnproject.exception.BusinessValidationException;
import com.example.loomlearnproject.exception.ResourceNotFoundException;
import com.example.loomlearnproject.repository.SessionEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionEnrollmentService {

    @Autowired
    private SessionEnrollmentRepository repo;

    
    public SessionEnrollment save(SessionEnrollment enrollment) {

        if (repo.existsByLearnerIdAndSessionId(
                enrollment.getLearnerId(),
                enrollment.getSessionId())) {

            throw new BusinessValidationException("Learner already enrolled in this session");
        }

        return repo.save(enrollment);
    }

    public List<SessionEnrollment> getAll() {
        return repo.findAll();
    }

    
    public List<SessionEnrollment> getByLearnerId(Long learnerId) {

        List<SessionEnrollment> list = repo.findByLearnerId(learnerId);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No enrollments found for learner id: " + learnerId);
        }

        return list;
    }

    
    public void delete(Long id) {

        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + id);
        }

        repo.deleteById(id);
    }
}