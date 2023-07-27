package com.education.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.Courses;
import com.education.repository.CourseRepository;
import com.education.service.CourseService;
import com.education.utils.CommonValidator;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired CourseRepository courseRepository;

	@Override
	public String addCourse(String courseName) {
		 Courses courses=new Courses();
		 courses.setCourseName(courseName);
		Courses savedCourse=courseRepository.save(courses);
		return savedCourse.getCourseName();
	}

	@Override
	public List<Courses> getAllCourse() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public String updateCourse(Long courseId, String courseName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCourse(Long courseId) {
		Courses getCourse= courseRepository.findById(courseId).get();
		if(CommonValidator.isNull(getCourse)) {
			return courseId+" id not found";
		}else {
			courseRepository.deleteById(courseId);
		}
		return "Deleted successfully";
	}

}