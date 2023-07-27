package com.education.service;

import java.util.List;

import com.education.entity.Courses;

public interface CourseService {
    public String addCourse(String courseName);
    public List<Courses> getAllCourse();
    public String updateCourse(Long courseId, String courseName);
    public String deleteCourse(Long courseId);

}
