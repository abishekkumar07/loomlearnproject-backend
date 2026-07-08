package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.MentorFeedback;
import com.example.loomlearnproject.exception.BusinessValidationException;
import com.example.loomlearnproject.exception.ResourceNotFoundException;
import com.example.loomlearnproject.repository.MentorFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MentorFeedbackService {

    @Autowired
    private MentorFeedbackRepository repo;

    
    public MentorFeedback save(MentorFeedback feedback) {

        if (feedback.getRating() == null || feedback.getRating() < 1 || feedback.getRating() > 5) {
            throw new BusinessValidationException("Rating must be between 1 and 5");
        }

        return repo.save(feedback);
    }

    public List<MentorFeedback> getAll() {
        return repo.findAll();
    }

    
    public List<MentorFeedback> getByMentorId(Long mentorId) {

        List<MentorFeedback> list = repo.findByMentorId(mentorId);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No feedback found for mentor id: " + mentorId);
        }

        return list;
    }

    
    public void delete(Long id) {

        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Feedback not found with id: " + id);
        }

        repo.deleteById(id);
    }
}