package com.example.loomlearnproject.dto;

import java.time.LocalDateTime;

public class EnrollmentSummaryDto {

    private Long id;
    private Long userId;
    private String userName;
    private String userEmail;
    private Long courseId;
    private String courseTitle;
    private LocalDateTime enrolledAt;

    public EnrollmentSummaryDto(Long id, Long userId, String userName, String userEmail,
                                Long courseId, String courseTitle, LocalDateTime enrolledAt) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.enrolledAt = enrolledAt;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}
