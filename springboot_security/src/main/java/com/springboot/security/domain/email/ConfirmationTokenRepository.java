package com.springboot.security.domain.email;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.security.web.dto.email.EmailTokenDto;

@Mapper
public interface ConfirmationTokenRepository {
	public ConfirmationToken getConfirmationToken(EmailTokenDto emailTokenDto);
	public int createToken(ConfirmationToken confirmationToken);
}
