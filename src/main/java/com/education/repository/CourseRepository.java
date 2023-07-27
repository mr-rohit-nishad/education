package com.education.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.education.entity.Courses;
@Repository
public interface CourseRepository extends JpaRepository<Courses,Long>{

	public Courses findCoursesByCourseName(String courseName);

}
