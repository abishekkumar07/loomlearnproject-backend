package com.example.loomlearnproject.repository;

import com.example.loomlearnproject.entity.AcademicUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AcademicUserRepository extends JpaRepository<AcademicUser, Long> {

    Optional<AcademicUser> findByEmail(String email);

    boolean existsByEmail(String email);

    List<AcademicUser> findByRoleAndStatus(
            AcademicUser.UserRole role,
            AcademicUser.UserStatus status
    );
}