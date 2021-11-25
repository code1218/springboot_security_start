package com.springboot.security.domain.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.springboot.security.web.dto.auth.SignupDto;

@Mapper
public interface UserRepository {
	public int usernameCheck(User user);
	public int signup(User User);
	public User getUser(String username);
}
