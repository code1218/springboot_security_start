package com.springboot.security.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.security.config.auth.PrincipalDetails;

@Controller
public class UserController {
	@GetMapping("/user/update")
	public String update(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println(principalDetails);
		return "user/update";
	}
	
	@GetMapping("/test/phone")
	public String phone() {
		return "user/phoneNumberCheck";
	}
}
