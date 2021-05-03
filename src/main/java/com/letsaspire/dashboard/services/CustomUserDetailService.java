package com.letsaspire.dashboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.letsaspire.dashboard.domain.UserCredentials;
import com.letsaspire.dashboard.repositories.UserCredentialsRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserCredentialsRepository userCredentialsRepository;
	

	public UserDetails loadUserByUsername(Long phonenumber) throws UsernameNotFoundException {

		UserCredentials userCredentials = userCredentialsRepository.findByPhoneNumber(phonenumber);
		return new CustomUserDetail(userCredentials);
	}
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		
		UserCredentials userCredentials = userCredentialsRepository.findByEmail(email);
		
		return new CustomUserDetail(userCredentials);
	}
	
	

}
