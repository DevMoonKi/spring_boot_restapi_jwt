package com.example.SpringBoot.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.SpringBoot.bean.HandlerAsync;
import com.example.SpringBoot.config.EnvironmentConfig;
import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;
import com.example.SpringBoot.rest.login.service.UserSignUpAPI;

import org.springframework.stereotype.Component;

@Component
public class UserSignUpClient {
	
	@Autowired
	private CommonComponent common;
	
	private UserSignUpAPI create() {
		return new UserSignUpAPI(common.getConfig().getApiKey());
	}
	
	/*기본 사용자 가입*/
	public ResponseVO<Object> signUp(InUserSignUpVO inUserSignUpVO){
		return this.create().signUp(inUserSignUpVO);
	}
}
