package com.springboot.security.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.security.web.dto.auth.SignupDto;
import com.springboot.security.web.dto.auth.SignupRespDto;
import com.springboot.security.web.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;

	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	@ResponseBody
	@PostMapping("/auth/signup")
	public Object signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			//유효성 검사 오류 있음
			Map<String, String> errorMap  = new HashMap<String, String>();
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			SignupRespDto<Map<String, String>> signupRespDto = new SignupRespDto<Map<String,String>>();
			signupRespDto.setCode(400);
			signupRespDto.setMsg(errorMap);
			return signupRespDto;
		}else {
			//회원가입 진행
			SignupRespDto<String> signupRespDto = new SignupRespDto<String>();
			int signupResult = authService.signup(signupDto);
			if(signupResult == 1) {
				//회원가입 성공
				signupRespDto.setCode(200);
				signupRespDto.setMsg("회원가입 성공.");
			}else if(signupResult == 2) {
				signupRespDto.setCode(410);
				signupRespDto.setMsg("이미 가입된 username입니다.");
			}else {
				signupRespDto.setCode(500);
				signupRespDto.setMsg("회원가입 실패. 관리자에게 문의하세요.");
			}
			return signupRespDto;
		}
	}
}





