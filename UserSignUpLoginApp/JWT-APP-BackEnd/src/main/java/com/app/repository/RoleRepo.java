package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.ERole;
import com.app.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByName(ERole name);
}
