package com.springboot.security.web.dto.email;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmailTokenDto {
	private String confirmationTokenId;
	private LocalDateTime now;
	private int expired;
}
