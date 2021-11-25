package com.springboot.security.domain.email;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ConfirmationToken {
	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;
	
	private String id;
	private LocalDateTime expiration_date;
	private int expired;
	private String email;
	private LocalDateTime createdate;
	private LocalDateTime updatedate;
	
	public static ConfirmationToken createEmailConfirmationToken(String receiverEmail) {
		ConfirmationToken confirmationToken = new ConfirmationToken();
		confirmationToken.id = UUID.randomUUID().toString().replaceAll("-", "");
		confirmationToken.expiration_date = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
		confirmationToken.email = receiverEmail;
		confirmationToken.expired = 0;
		return confirmationToken;
	}
	
	public void useToken() {
		expired = 1;
	}
}
