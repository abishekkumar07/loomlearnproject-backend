package com.example.loomlearnproject.repository;

import com.example.loomlearnproject.entity.SessionEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionEnrollmentRepository extends JpaRepository<SessionEnrollment, Long> {

    List<SessionEnrollment> findByLearnerId(Long learnerId);

    Optional<SessionEnrollment> findByLearnerIdAndSessionId(Long learnerId, Long sessionId);

    boolean existsByLearnerIdAndSessionId(Long learnerId, Long sessionId);
}