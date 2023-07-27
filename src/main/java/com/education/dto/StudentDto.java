package com.education.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Setter
@Getter
public class StudentDto {
	private Long id;
	private String fullName;
	private String emailId;
	private String telephoneNumber;
	private String address;
	private Set<String> courses = new HashSet<>();

}
