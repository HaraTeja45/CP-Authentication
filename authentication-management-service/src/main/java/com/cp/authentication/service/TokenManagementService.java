package com.cp.authentication.service;

import com.cp.authentication.bean.AuthenticationRequest;

public interface TokenManagementService {

	public String generateWebTokenByUsername(AuthenticationRequest authenticationRequest);
}
