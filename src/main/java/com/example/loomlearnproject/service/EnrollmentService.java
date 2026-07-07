package com.example.loomlearnproject.service;

import com.example.loomlearnproject.dto.EnrollmentSummaryDto;
import com.example.loomlearnproject.entity.AcademicUser;
import com.example.loomlearnproject.entity.Course;
import com.example.loomlearnproject.entity.Enrollment;
import com.example.loomlearnproject.exception.BusinessValidationException;
import com.example.loomlearnproject.repository.AcademicUserRepository;
import com.example.loomlearnproject.repository.CourseRepository;
import com.example.loomlearnproject.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository repo;

    @Autowired
    private AcademicUserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    
    public Enrollment enroll(Long userId, Long courseId) {

      
        if (repo.existsByUserIdAndCourseId(userId, courseId)) {
            throw new BusinessValidationException("User already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(userId, courseId);
        return repo.save(enrollment);
    }

    public List<Enrollment> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }


    public List<Enrollment> getByCourse(Long courseId) {
        return repo.findByCourseId(courseId);
    }

    public List<EnrollmentSummaryDto> getAllEnrollmentSummaries() {
        return repo.findAll().stream()
                .map(this::toSummary)
                .toList();
    }

  
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private EnrollmentSummaryDto toSummary(Enrollment enrollment) {
        AcademicUser user = userRepository.findById(enrollment.getUserId()).orElse(null);
        Course course = courseRepository.findById(enrollment.getCourseId()).orElse(null);

        return new EnrollmentSummaryDto(
                enrollment.getId(),
                enrollment.getUserId(),
                user != null ? user.getFullName() : "Unknown user",
                user != null ? user.getEmail() : "",
                enrollment.getCourseId(),
                course != null ? course.getTitle() : "Unknown course",
                enrollment.getEnrolledAt()
        );
    }
}
