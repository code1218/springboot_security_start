package com.springboot.security.handler.ex;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import com.springboot.security.config.auth.PrincipalDetails;

public class CustomOAuth2Exception extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private PrincipalDetails user;
	
	public CustomOAuth2Exception(String message) {
		super(message);
		System.out.println(message);
	}
	
	public CustomOAuth2Exception(String message, PrincipalDetails user) {
		super(message);
		this.user = user;
		System.out.println(user);
		System.out.println(user);
	}
	
	public PrincipalDetails getUser() {
		return user;
	}
}
