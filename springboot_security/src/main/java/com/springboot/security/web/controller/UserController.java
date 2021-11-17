package com.springboot.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/user/update")
	public String updateForm() {
		return "user/update";
	}
}
