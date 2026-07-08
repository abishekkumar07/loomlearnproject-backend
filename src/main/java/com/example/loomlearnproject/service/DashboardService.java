package com.example.loomlearnproject.service;

import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private AcademicUserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudySubjectRepository subjectRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private long uniqueCourseCount() {
        return courseRepository.findAll().stream()
                .map(course -> course.getTitle() == null ? "" : course.getTitle().trim().toLowerCase())
                .distinct()
                .count();
    }
    
    public Map<String, Object> getAdminDashboard() {

        Map<String, Object> map = new HashMap<>();

        map.put("totalUsers", userRepository.count());
        map.put("totalCourses", uniqueCourseCount());
        map.put("totalSubjects", subjectRepository.count());
        map.put("totalEnrollments", enrollmentRepository.count());

        long pendingMentors = userRepository
                .findByRoleAndStatus(AcademicUser.UserRole.MENTOR,
                        AcademicUser.UserStatus.PENDING)
                .size();

        map.put("pendingMentors", pendingMentors);

        return map;
    }

    
    public Map<String, Object> getMentorDashboard(Long mentorId) {

        Map<String, Object> map = new HashMap<>();

        map.put("totalCourses", uniqueCourseCount());

        map.put("totalEnrollments", enrollmentRepository.count());

        return map;
    }

    public Map<String, Object> getLearnerDashboard(Long learnerId) {

        Map<String, Object> map = new HashMap<>();

        map.put("totalCourses", uniqueCourseCount());

        map.put("totalEnrollments", enrollmentRepository.count());

        return map;
    }
}
