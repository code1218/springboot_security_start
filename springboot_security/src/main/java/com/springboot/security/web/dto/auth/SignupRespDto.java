package com.springboot.security.web.dto.auth;

import java.util.Map;

import lombok.Data;

@Data
public class SignupRespDto<T> {
	private int code;
	private T msg;
}
