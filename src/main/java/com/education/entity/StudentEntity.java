package com.education.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	 @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence")
	private Long id;
	private String fullName;
	private String emailId;
	private String telephoneNumber;
	private String address;
	private String provider;
	private String password;
	
	 @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	    @JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = {@JoinColumn(name = "COURSE_ID") })
	    private Set<Courses> courses;
	 
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();
	    
	 public StudentEntity(String username, String email, String encode) {
		 this.fullName=username;
		 this.emailId=email;
		 this.password=encode;
			 
		}
	 
	public void addCourse(Courses course) {
	        this.courses.add(course);
	        course.getStudents().add(this);
	    }
	    public void removeCourse(Courses course) {
	        this.getCourses().remove(course);
	        course.getStudents().remove(this);
	    }
	    public void removeCourses() {
	        for (Courses course : new HashSet<>(courses)) {
	            removeCourse(course);
	        }
	    }
		 

}