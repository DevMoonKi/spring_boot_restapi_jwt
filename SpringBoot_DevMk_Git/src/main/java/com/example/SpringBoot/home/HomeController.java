package com.example.SpringBoot.home;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SpringBoot.component.UserSignUpClient;
import com.example.SpringBoot.io.extend.InUserSignUpVO;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	@Autowired
	UserSignUpClient userSignUpClient;
	
	@RequestMapping("/home")
	public String home() {
		return  "home/home";
	}
	
	/**
	 * client 테스트
	 */
	
	@RequestMapping("/test") 
	public String signTest() {
		
/*		
		InUserSignUpVO inUserSignUpVO = new InUserSignUpVO();  
		inUserSignUpVO.setMemberId("signTest");
		inUserSignUpVO.setMemberPw("1234");
		inUserSignUpVO.setMemberName("이문기");
		inUserSignUpVO.setEmail("flower2362@naver.com");
		inUserSignUpVO.setEmailConfirmUrl("http://localhost:8080/rest/member/emailConfirm");
		userSignUpClient.signUp(inUserSignUpVO);
*/	
		
		return "home/test";
		
	}
	
}
	
