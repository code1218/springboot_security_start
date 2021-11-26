package com.springboot.security.web.dto.image;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ImageDto {
	private MultipartFile profileImageFile;
}
