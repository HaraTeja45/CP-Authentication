package com.cp.authentication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cp.authentication.bean.AuthenticationRequest;
import com.cp.authentication.repository.UserRepository;
import com.cp.authentication.util.JWTUtil;

@Service
public class TokenManagementServiceImpl implements TokenManagementService {

	private final UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	TokenManagementServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String generateWebTokenByUsername(AuthenticationRequest authenticationRequest) {

		String jwtToken = null;
		try {
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authenticate);

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			jwtToken = jwtUtil.generateJWTToken(userDetails.getUsername(), extractRolesFromAuthorities(userDetails));
		} catch (UsernameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			// throw custom exception
		}

		return jwtToken;
	}

	private static List<String> extractRolesFromAuthorities(UserDetails userDetails) {

		return userDetails.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.toList());
	}
}
