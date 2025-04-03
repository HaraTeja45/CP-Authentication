package com.cp.authentication.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cp.authentication.model.User;
import com.cp.authentication.repository.UserRepository;
import com.cp.authentication.util.AuthenticationConstant;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User customUserDetails = userRepository.findByUsernameAndIsactive(username, AuthenticationConstant.ISACTIVE)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));

		return new org.springframework.security.core.userdetails.User(customUserDetails.getUsername(),
				customUserDetails.getPassword(), Collections.emptyList());
	}

}
