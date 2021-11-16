package com.springboot.security.web.service;

import com.springboot.security.web.dto.auth.SignupDto;

public interface AuthService {
	public int signup(SignupDto signupDto);
}
