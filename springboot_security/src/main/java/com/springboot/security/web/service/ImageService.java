package com.springboot.security.web.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springboot.security.web.dto.image.ImageDto;

@Service
public class ImageService {
	
	@Value("${file.path.profile}")
	private String uploadFolder;
	
	public void ImageUpload(ImageDto imageDto) {
		String imageFileName = imageDto.getProfileImageFile().getOriginalFilename();
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		try {
			Files.write(imageFilePath, imageDto.getProfileImageFile().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
