package com.example.loomlearnproject.repository;

import com.example.loomlearnproject.entity.TutoringSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutoringSessionRepository extends JpaRepository<TutoringSession, Long> {

    List<TutoringSession> findByStatus(TutoringSession.SessionStatus status);
}