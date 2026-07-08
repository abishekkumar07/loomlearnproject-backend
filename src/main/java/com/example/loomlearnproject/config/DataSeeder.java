package com.example.loomlearnproject.config;

import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.entity.StudySubject;
import com.example.loomlearnproject.repository.AcademicUserRepository;
import com.example.loomlearnproject.repository.StudySubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataSeeder {

    @Autowired
    private StudySubjectRepository subjectRepo;

    @Autowired
    private AcademicUserRepository userRepo;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void seed() {

        renameSubject("amar", "Web Development", "Frontend and backend web development");
        renameSubject("arun", "Data Structures", "Core data structures and algorithms");
        renameSubject("cprogram", "C Programming", "Programming fundamentals using C");
        ensureSubject("Java", "Java programming");
        ensureSubject("Mathematics", "Math subject");
        ensureSubject("Computer Science", "CS subject");
        ensureSubject("Physics", "Physics subject");

   
        if (userRepo.count() == 0) {

            AcademicUser learner = new AcademicUser();
            learner.setFullName("Alice Learner");
            learner.setEmail("alice@gmail.com");
            learner.setPassword(encoder.encode("password123"));
            learner.setRole(AcademicUser.UserRole.LEARNER);
            learner.setStatus(AcademicUser.UserStatus.APPROVED);

            AcademicUser mentor = new AcademicUser();
            mentor.setFullName("Bob Mentor");
            mentor.setEmail("bob@gmail.com");
            mentor.setPassword(encoder.encode("password123"));
            mentor.setRole(AcademicUser.UserRole.MENTOR);
            mentor.setStatus(AcademicUser.UserStatus.APPROVED);

            AcademicUser admin = new AcademicUser();
            admin.setFullName("Charlie Admin");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(encoder.encode("password123"));
            admin.setRole(AcademicUser.UserRole.ACADEMIC_ADMIN);
            admin.setStatus(AcademicUser.UserStatus.APPROVED);

            AcademicUser support = new AcademicUser();
            support.setFullName("Diana Support");
            support.setEmail("support@gmail.com");
            support.setPassword(encoder.encode("password123"));
            support.setRole(AcademicUser.UserRole.SUPPORT_AGENT);
            support.setStatus(AcademicUser.UserStatus.APPROVED);

            userRepo.save(learner);
            userRepo.save(mentor);
            userRepo.save(admin);
            userRepo.save(support);
        }
    }

    private void ensureSubject(String name, String description) {
        if (subjectRepo.findByNameIgnoreCase(name).isPresent()) {
            return;
        }

        StudySubject subject = new StudySubject();
        subject.setName(name);
        subject.setDescription(description);
        subjectRepo.save(subject);
    }

    private void renameSubject(String oldName, String newName, String description) {
        subjectRepo.findByNameIgnoreCase(oldName).ifPresent(subject -> {
            if (subjectRepo.findByNameIgnoreCase(newName).isPresent()) {
                subjectRepo.delete(subject);
                return;
            }

            subject.setName(newName);
            subject.setDescription(description);
            subjectRepo.save(subject);
        });
    }
}
