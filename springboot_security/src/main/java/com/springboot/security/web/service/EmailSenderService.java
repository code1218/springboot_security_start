package com.springboot.security.web.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailSenderService {
	private final JavaMailSender javaMailSender;
	
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}
}
