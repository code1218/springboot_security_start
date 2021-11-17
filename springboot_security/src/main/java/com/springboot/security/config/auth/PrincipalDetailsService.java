package com.springboot.security.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.security.domain.user.User;
import com.springboot.security.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

//loadUser메소드가 종료시(return) @AuthenticationPrincipal 어노테이션을 사용할 수 있게 만들어 준다.
//@AuthenticationPrincipal //세션정보를 담는 공간이다.
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.getUser(username);
		if(userEntity == null) {
			return null;
		}else {
			UserDetails principalDetails = new PrincipalDetails(userEntity);
			return principalDetails;
		}
		
	}

}
