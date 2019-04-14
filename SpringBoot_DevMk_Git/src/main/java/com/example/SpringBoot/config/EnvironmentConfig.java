package com.example.SpringBoot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentConfig {
	
	@Value("${devmk.api.key}")
	private String apiKey;
	
	//@Value("${email.confirm.url}")
	//private String emailConfirmUrl;
	
	public String getApiKey() {
		return apiKey;
	}
	
	//public String getEmailConfirmUrl() {
		//return emailConfirmUrl;
	//}

}
