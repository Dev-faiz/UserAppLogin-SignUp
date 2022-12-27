package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.model.MyUser;
import com.app.repository.MyUserRepo;

import jakarta.transaction.Transactional;

@Service
public class MyUserServiceImpl implements MyUserService , UserDetailsService {
	
	@Autowired
	private MyUserRepo myUserRepo ; 

		@Transactional
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			MyUser user = myUserRepo.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
			return UserDetailsImpl.build(user);
		}
		
	

}
