package com.springboot.security.config.oauth2;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.springboot.security.config.auth.PrincipalDetails;
import com.springboot.security.config.oauth2.provider.OAuth2UserDto;
import com.springboot.security.domain.user.User;
import com.springboot.security.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	private final UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println(userRequest.getClientRegistration());
		System.out.println(userRequest.getAccessToken());
		System.out.println(super.loadUser(userRequest).getAttributes());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> attributes = oAuth2User.getAttributes();
		
		String provider = userRequest.getClientRegistration().getRegistrationId();
		String providerId = null;
		if(provider.equals("google")) {
			providerId = (String)attributes.get("sub");
		}else if(provider.equals("facebook")) {
			providerId = (String)attributes.get("id");
		}else if(provider.equals("naver")) {
			
		}else {
			providerId = UUID.randomUUID().toString().replaceAll("-", "");
		}
		
		String username = provider + "_" + providerId;
		
		User userEntity = userRepository.getUser(username);
		
		if(userEntity == null) {
			//처음 사이트 로그인
			OAuth2UserDto oAuth2UserDto = OAuth2UserDto.builder()
											.username(username)
											.password(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()))
											.email((String)attributes.get("email"))
											.name((String)attributes.get("name"))
											.role("ROLE_USER")
											.provider(provider)
											.build();
			
			userEntity = oAuth2UserDto.toEntity();
			userRepository.signup(userEntity);
		}
		return new PrincipalDetails(userEntity);
	}
}
