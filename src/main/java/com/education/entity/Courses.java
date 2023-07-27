package com.education.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Courses {

	@Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
  //  @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String courseName;
	
	@ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<StudentEntity> students;
	
 }