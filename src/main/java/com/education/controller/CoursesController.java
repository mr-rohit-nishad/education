package com.education.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.education.entity.Courses;
import com.education.service.CourseService;
import com.education.utils.CommonValidator;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Courses", description = "Courses APIs")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CoursesController {
	@Autowired CourseService courseService;
	
	   @PostMapping("/addCourse")
    public String addCourse(@RequestParam String courseName) {
    	return courseService.addCourse(courseName);
    }
    
	   @GetMapping("/getAllCourse")
    public List<Courses> getAllCourse(){
		   List<Courses> getList=new ArrayList<>();
		  getList= courseService.getAllCourse();
		  if(CommonValidator.isEmpty(getList)) {
			  return getList;
		  }
		  return getList;
    }
    
	 @PutMapping("/updateCourse")
    public String updateCourse(@RequestParam Long courseId,@RequestParam String courseName) {
		return courseService.updateCourse(courseId, courseName);
    	
    }
    
	 @DeleteMapping("/deleteCourse")
    public String deleteCourse(@RequestParam Long courseId) {
		return courseService.deleteCourse(courseId);
    	
    }

	

}