package com.springboot.security.handler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.security.handler.ex.CustomOAuth2Exception;

@Controller
@ControllerAdvice
public class ControllerExceptionHandler{
	
	@ExceptionHandler(CustomOAuth2Exception.class)
	public String oAuth2Exception(Model model, CustomOAuth2Exception e) {
		System.out.println("호출?");
		model.addAttribute("oauth2User", e.getUser());
		return "oauth2/signup";
	}
	
}
