package com.education.serviceimpl;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.dto.StudentDto;
import com.education.entity.Courses;
import com.education.entity.StudentEntity;
import com.education.repository.CourseRepository;
import com.education.repository.StudentRepository;
import com.education.service.StudentService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
	private static final Logger log=LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Autowired StudentRepository studentRepository;
	@Autowired CourseRepository courseRepository;

	@Override
	public StudentDto addStudent(StudentDto studentDto) {
		StudentEntity student = new StudentEntity();
		ObjectMapper mapper=new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			log.info("Data :{}", mapper.writeValueAsString(studentDto));
			
		     mapDtoToEntity(studentDto, student);
		     log.info("map Data :{}");
		     log.info("savedStudent Data :{}", mapper.writeValueAsString(student));

		     StudentEntity savedStudent = studentRepository.save(student);
		     log.info("savedStudent Data :{}", mapper.writeValueAsString(savedStudent));
		     return mapEntityToDto(savedStudent);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return studentDto;
		 
	}

	 
	@Override
	public List<StudentDto> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public StudentDto updateStudent(Integer studentId, StudentDto student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteStudent(Integer studentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 
	 private void mapDtoToEntity(StudentDto studentDto, StudentEntity student) {
	        student.setFullName(studentDto.getFullName());
	        student.setAddress(studentDto.getAddress());
	        student.setEmailId(studentDto.getEmailId());
	        student.setTelephoneNumber(studentDto.getTelephoneNumber());
	        if (null == student.getCourses()) {
	            student.setCourses(new HashSet<>());
	        }
	        studentDto.getCourses().stream().forEach(courseName -> {
	            Courses course = courseRepository.findCoursesByCourseName(courseName);
	          
	            if (null == course) {
	                course = new  Courses();
	                course.setStudents(new HashSet<>());
	            }
	            course.setCourseName(courseName);
	            student.addCourse(course);
 	        });
	    }
    private StudentDto mapEntityToDto(StudentEntity student) {
        StudentDto responseDto = new StudentDto();
        responseDto.setFullName(student.getFullName());
        responseDto.setId(student.getId());
        responseDto.setCourses(student.getCourses().stream().map(Courses::getCourseName).collect(Collectors.toSet()));
        return responseDto;
    }

}