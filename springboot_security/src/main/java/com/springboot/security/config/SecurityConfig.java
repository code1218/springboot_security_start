package com.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.springboot.security.config.oauth2.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EnableWebSecurity //해당파일로 시큐리티 활성화
@Configuration //IoC등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**")
			.authenticated()
			.anyRequest()
			.permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/signin")
			.loginProcessingUrl("/auth/signin")
			.defaultSuccessUrl("/")
			.and()
			.oauth2Login()
			.loginPage("/auth/signin")
			/**로그인이 완료된 뒤의 후처리가 필요함. 
			 * 1.코드받기(인증)
			 * 2.엑세스토큰(권한)
			 * 3.사용자프로필 정보를 가져오기
			 * 4-1.그 정보를 토대로 회원가입을 자동으로 진행시키기도 함
			 * 4-2.(이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> 집주소, 회원등급 등 추가적인 설정이 필요함
			 * 
			 * oauth2 client를 쓰게되면 데이터를 한번에 받아옴
			 */
			.userInfoEndpoint()
			.userService(principalOauth2UserService)
			.and()
			.defaultSuccessUrl("/oauth2/signup");
			
	}
}
