package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.TutoringSession;
import com.example.loomlearnproject.exception.ResourceNotFoundException;
import com.example.loomlearnproject.repository.TutoringSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TutoringSessionService {

    @Autowired
    private TutoringSessionRepository repo;

    public TutoringSession save(TutoringSession session) {

        if (session.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Start time cannot be in past");
        }

        return repo.save(session);
    }

    public List<TutoringSession> getAll() {
        return repo.findAll();
    }

    public TutoringSession getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));
    }

    public void delete(Long id) {

        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Session not found");
        }

        repo.deleteById(id);
    }
}