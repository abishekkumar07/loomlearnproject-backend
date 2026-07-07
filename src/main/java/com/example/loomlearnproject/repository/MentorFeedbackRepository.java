package com.example.loomlearnproject.repository;

import com.example.loomlearnproject.entity.MentorFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorFeedbackRepository extends JpaRepository<MentorFeedback, Long> {

    List<MentorFeedback> findByMentorId(Long mentorId);
}