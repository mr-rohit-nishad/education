package com.education.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.entity.Courses;
import com.education.repository.CourseRepository;
import com.education.service.CourseService;
import com.education.utils.CommonValidator;

@Service
public class CourseServiceImpl implements CourseService{
	private static final Logger logger=LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired CourseRepository courseRepository;

	@Override
	public String addCourse(String courseName) {
		 try {
			 Courses courses=new Courses();
			 if(!CommonValidator.isEmpty(courseName)) { 
				 courses.setCourseName(courseName);
				Courses savedCourse=courseRepository.save(courses);
				return savedCourse.getCourseName();
			 }else {
				 return "Course is Empty";
			 }
		} catch (Exception e) {
			logger.error("Exception occured during saving course",e);
		}
		return courseName;
	}

	@Override
	public List<Courses> getAllCourse() { 
		return courseRepository.findAll();
	}

	@Override
	public String updateCourse(Long courseId, String courseName) {
		try {
			Courses updateProduct=new Courses();
			Courses getData=new Courses();
			if(courseId !=null) { 
					Optional<Courses> data= courseRepository.findById(courseId);
					if(!data.isEmpty()) {
						updateProduct.setCourseName(courseName); 
						getData=courseRepository.save(updateProduct);
					}else {
						return "ID not exist";
					} 
			}else {
				return "ID not Found";
			}
			return getData.getCourseName();
		} catch (Exception e) {
			logger.error("Exception occured during updating course",e);
		}
		return "Unable to update course";
		
	}

	@Override
	public String deleteCourse(Long courseId) {
		try {
			Courses getCourse= courseRepository.findById(courseId).get();
			if(CommonValidator.isNull(getCourse)) {
				return courseId+" id not found";
			}else {
				courseRepository.deleteById(courseId);
			}
			return "Deleted successfully";
		} catch (Exception e) {
			logger.error("Exception occured during delete course",e);
		}
		return "Unable to delete course";
		
	}

}