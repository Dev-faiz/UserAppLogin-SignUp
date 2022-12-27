package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.MyUser;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser, Long> {
	
	
	Optional<MyUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
