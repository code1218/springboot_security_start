package com.springboot.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.security.handler.ex.CustomOAuth2Exception;

@Controller
public class PageController {

	@GetMapping({"/", "/index"})
	public String index() throws CustomOAuth2Exception {
		int i = 0;
		if(i == 0) {
			throw new CustomOAuth2Exception("회원가입 진행 필요");
		}
		
		return "index";
	}
}
