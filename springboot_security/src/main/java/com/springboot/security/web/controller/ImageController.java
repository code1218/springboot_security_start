package com.springboot.security.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.security.web.dto.image.ImageDto;
import com.springboot.security.web.service.ImageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ImageController {
	
	private final ImageService imageService;
	
	@GetMapping("/image")
	public String imageForm() {
		return "user/image";
	}
	
	@PostMapping("/image/upload")
	public String imageUpload(Model model, ImageDto imageDto) {
		imageService.ImageUpload(imageDto);
		model.addAttribute("imageName", imageDto.getProfileImageFile().getOriginalFilename());
		return "user/profile";
	}
}
