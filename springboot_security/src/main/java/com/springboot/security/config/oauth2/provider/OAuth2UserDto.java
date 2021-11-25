package com.springboot.security.config.oauth2.provider;

import com.springboot.security.domain.user.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OAuth2UserDto {
	private String username;
	private String password;
	private String email;
	private String name;
	private String role;
	private String provider;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.role(role)
				.provider(provider)
				.build();
				
	}
}
