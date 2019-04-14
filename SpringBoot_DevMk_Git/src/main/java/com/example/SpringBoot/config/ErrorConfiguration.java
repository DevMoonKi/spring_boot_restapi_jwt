package com.example.SpringBoot.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration 
public class ErrorConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>{
	
	@Override
	public void customize(ConfigurableServletWebServerFactory container) {
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
	    container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error"));
	    container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error"));
		container.addErrorPages(new ErrorPage(Exception.class, "/error"));
	} 
}

