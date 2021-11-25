package com.springboot.security.web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.security.domain.email.ConfirmationToken;
import com.springboot.security.domain.user.User;
import com.springboot.security.domain.user.UserRepository;
import com.springboot.security.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{
	
	private final UserRepository userRepository;
	private final ConfirmationTokenService confirmationTokenService;
	
	@Override
	public int signup(SignupDto signupDto) {
		User user = signupDto.toEntity();
		int usernameCheckResult = userRepository.usernameCheck(user);
		if(usernameCheckResult == 1) {
			return 2;
		}else {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setRole("ROLE_USER");
			int signupResult = userRepository.signup(user);
			return signupResult;
		}
	}
	
	@Override
	public void confirmEmail(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
		User user = userRepository.getUser(confirmationToken.getEmail());
		confirmationToken.useToken();
		System.out.println("인증됨");
	}
	
}
