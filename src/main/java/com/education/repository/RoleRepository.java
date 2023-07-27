package com.education.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.education.entity.ERole;
import com.education.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	public Role findByName(String name);

	  Optional<Role> findByName(ERole name);

}
