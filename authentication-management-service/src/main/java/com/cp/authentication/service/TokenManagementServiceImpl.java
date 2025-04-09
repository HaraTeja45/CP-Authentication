package com.cp.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cp.authentication.bean.AuthenticationRequest;
import com.cp.authentication.util.JWTUtil;

@Service
public class TokenManagementServiceImpl implements TokenManagementService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public String generateWebTokenByUsername(AuthenticationRequest authenticationRequest) {

		String jwtToken = null;
		try {
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authenticate);

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			jwtToken = jwtUtil.generateJWTToken(userDetails.getUsername());
		} catch (UsernameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			// throw custom exception
		}

		return jwtToken;
	}

}
