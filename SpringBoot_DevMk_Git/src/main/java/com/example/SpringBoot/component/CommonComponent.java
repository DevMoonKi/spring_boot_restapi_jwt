package com.example.SpringBoot.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.SpringBoot.bean.HandlerAsync;
import com.example.SpringBoot.config.EnvironmentConfig;
import org.springframework.stereotype.Component;

@Component
public class CommonComponent {
	
	@Autowired
	private HandlerAsync async;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MessageSource msg;
	@Autowired
	private EnvironmentConfig config;
	
	public HandlerAsync getAsync() {
		return async;
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	public MessageSource getMsg() {
		return msg;
	}
	public EnvironmentConfig getConfig() {
		return config;
	}
	

}
