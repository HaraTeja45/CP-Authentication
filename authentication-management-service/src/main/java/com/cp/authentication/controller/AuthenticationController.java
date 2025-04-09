package com.cp.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cp.authentication.bean.AuthenticationRequest;
import com.cp.authentication.service.TokenManagementService;

@RestController
public class AuthenticationController {

	@Autowired
	private TokenManagementService tokenManagementService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {

		String webTokenByUsername = tokenManagementService.generateWebTokenByUsername(authenticationRequest);

		return new ResponseEntity<>(webTokenByUsername, HttpStatus.OK);

	}

}
