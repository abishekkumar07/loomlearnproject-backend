package com.example.loomlearnproject.repository;

import com.example.loomlearnproject.entity.StudySubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudySubjectRepository extends JpaRepository<StudySubject, Long> {

    Optional<StudySubject> findByName(String name);

    Optional<StudySubject> findByNameIgnoreCase(String name);
}
