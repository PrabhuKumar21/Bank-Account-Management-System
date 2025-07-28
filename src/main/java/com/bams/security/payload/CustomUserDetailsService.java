package com.bams.security.payload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bams.entity.User;
import com.bams.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		
		 User user = userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found"));
		 
		 return new CustomUserDetails(user);
		 
	}

}
