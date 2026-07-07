package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.exception.BusinessValidationException;
import com.example.loomlearnproject.exception.ResourceNotFoundException;
import com.example.loomlearnproject.repository.AcademicUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicUserService {

    @Autowired
    private AcademicUserRepository repo;

    public AcademicUser save(AcademicUser user) {

        if (repo.existsByEmail(user.getEmail())) {
            throw new BusinessValidationException("Email already exists");
        }

        return repo.save(user);
    }

    public List<AcademicUser> getAll() {
        return repo.findAll();
    }

    
    public AcademicUser getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public void delete(Long id) {

        // optional but recommended
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        repo.deleteById(id);
    }
}