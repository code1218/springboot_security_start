package com.springboot.security.web.service;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
public class CoolSMSService {
	
	private final String API_KEY = "NCSPDTEQWNOG134Z";
	private final String API_SECRET = "RGGCRQQE2PD20TO3ISW4SICGSJBXTSZI";
	
	public String createAuthenticationCode() {
		String authenticationCode = "";
		
		Random randNumber = new Random();
		int codeLength = 4;
		
		for(int i = 0; i < codeLength; i++) {
			String randCode = Integer.toString(randNumber.nextInt(10));
			authenticationCode += randCode;
		}
		
		return authenticationCode;
	}
	
	public String sendAuthenticationCode(String phoneNumber) {
		Message coolsms = new Message(API_KEY, API_SECRET);
		String authenticationCode = createAuthenticationCode();
		phoneNumber = phoneNumber.replaceAll("-", "");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber); //누구에게 보낼거냐?
		params.put("from", "01099881916"); //누가 보낼거냐?
		params.put("type", "SMS");
		params.put("text", "[준일 홈페이지] 문자인증 메세지\n" + "인증번호는 [" + authenticationCode + "]입니다.");
		params.put("app_version", "junilHome app 1.2");
		
		try {
			coolsms.send(params);
		} catch (CoolsmsException e) {
			e.printStackTrace();
		}
		
		return authenticationCode;
	}
}




