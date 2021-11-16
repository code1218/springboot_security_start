package com.springboot.security.domain.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
	public int usernameCheck(User user); //아이디 중복확인
	public int signup(User user); //회원가입 insert
}
