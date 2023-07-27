package com.education.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.education.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

	public Optional<StudentEntity> findStudentByEmailId(String email);

	public Optional<StudentEntity> findStudentByFullName(String username);

	public Boolean existsStudentByFullName(String username);

	public Boolean existsStudentByEmailId(String email);
 }
