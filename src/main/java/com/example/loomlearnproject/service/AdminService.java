package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.repository.AcademicUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AcademicUserRepository userRepository;

    
    public List<AcademicUser> getPendingMentors() {

        return userRepository.findByRoleAndStatus(
                AcademicUser.UserRole.MENTOR,
                AcademicUser.UserStatus.PENDING
        );
    }

    public AcademicUser approveMentor(Long id) {

        AcademicUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(AcademicUser.UserStatus.APPROVED);

        return userRepository.save(user);
    }
}