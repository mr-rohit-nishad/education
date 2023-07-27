package com.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.education.repository")
@OpenAPIDefinition(info = @Info(title = "Student API", version = "2.0", description = "Student Information"))
public class EducationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
		System.out.println("Server Started!!");
	}

}
