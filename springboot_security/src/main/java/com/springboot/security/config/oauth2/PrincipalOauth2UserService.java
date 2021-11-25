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
import com.springboot.security.handler.ex.CustomOAuth2Exception;

import lombok.RequiredArgsConstructor;

//해당 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	//구글로 부터 받은 userRequest데이터에 대한 후처리되는 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) {
		System.out.println(userRequest.getClientRegistration()); //registrationid로 어떤 OAuth로 로그인 했는지 확인
		System.out.println(userRequest.getAccessToken());
		//userRequest의 역할
		/*
		 * 1.구글 로그인 버튼클릭
		 * 2. 구글 로그인창
		 * 3. 로그인 완료
		 * 4. code를 리턴(OAuth-client라이브러리)
		 * 5. AccessToken요청
		 * 6. userRequest정보를 받음
		 * 7. loadUser함수 호출
		 * 8. 구글로부터 회원프로필을 받아준다.
		 * 
		 */
		OAuth2User oAuth2User = super.loadUser(userRequest);
		Map<String, Object> oAuth2UserAttributes = oAuth2User.getAttributes();
		
		String provider = userRequest.getClientRegistration().getRegistrationId(); //google
		String providerId = null;
		if(provider.equals("google")) {
			providerId = (String)oAuth2UserAttributes.get("sub");
		}else if(provider.equals("facebook")) {
			providerId = (String)oAuth2UserAttributes.get("id");
		}else if(provider.equals("naver")) {
			oAuth2UserAttributes = (Map<String, Object>)oAuth2UserAttributes.get("response");
			providerId = (String)oAuth2UserAttributes.get("id");
		}else {
			providerId = UUID.randomUUID().toString().replaceAll("-", "");
		}
		System.out.println(oAuth2UserAttributes);
		String username = provider + "_" + providerId;
		
		OAuth2UserDto oAuth2UserDto = OAuth2UserDto.builder()
										.username(username)
										//.password(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()))
										.email((String)oAuth2UserAttributes.get("email"))
										.name((String)oAuth2UserAttributes.get("name"))
										//.role("ROLE_USER")
										.provider(provider)
										.build();
		
		User userEntity = userRepository.getUser(username);// 처음 가입한 사용자인지 확인
		
		if(userEntity == null) {
			userEntity = oAuth2UserDto.toEntity();
		}
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}
	
}
