package com.springboot.security.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.web.service.CoolSMSService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PhoneNumberCheckController {
	
	private final CoolSMSService coolSMSService;
	
	@GetMapping("/check/sendSMS")
	public String sendSMS(String phoneNumber) {
		System.out.println("test");
		String authenticationCode = null;
		authenticationCode = coolSMSService.sendAuthenticationCode(phoneNumber);
		return authenticationCode;
	}
}
