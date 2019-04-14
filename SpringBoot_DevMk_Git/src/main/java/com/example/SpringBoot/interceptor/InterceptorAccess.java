package com.example.SpringBoot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;

import com.example.SpringBoot.component.CommonComponent;

public class InterceptorAccess implements HandlerInterceptor{
	
	@Autowired
	private CommonComponent common;
	
	private static final Logger logger = LoggerFactory.getLogger(InterceptorAccess.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
		logger.info("=================================/Access 인터셉터 시작/===============================");

		String apiKey = common.getConfig().getApiKey();
		logger.info("apiKey 확인 : "+apiKey);
		String headerApiKey = request.getHeader("apiKey");
		headerApiKey = headerApiKey != null ? headerApiKey : "";
		boolean check = false;
		if(apiKey.equals(headerApiKey)) {
			check = true;
		}else {
			response.sendRedirect("/error/apikey");
		}
		return check;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
	
	

}
