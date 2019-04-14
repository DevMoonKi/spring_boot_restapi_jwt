package com.example.SpringBoot.rest.login.service;

import java.util.List;

import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.MemberVO;
import com.example.SpringBoot.io.SearchVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;

public interface LoginService {
	
	MemberVO selectMap(MemberVO memberVO) throws Exception;
	
	int idCheck(MemberVO memberVO) throws Exception;
	
	//회원 가입
	void userSignUpEmail(InUserSignUpVO inUserSignUpVO) throws Exception;
	
	//이메일 인증
	ResponseVO<Object> userEmailConfirm(EmailConfirmVO emailConfirmVO) throws Exception;


	
}
