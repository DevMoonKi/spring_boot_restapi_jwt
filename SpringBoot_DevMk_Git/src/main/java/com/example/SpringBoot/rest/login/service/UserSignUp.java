package com.example.SpringBoot.rest.login.service;
import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;

import retrofit2.Call; 
import retrofit2.http.Body; 
import retrofit2.http.Header; 
import retrofit2.http.POST;

public interface UserSignUp {
	//사용자 회원가입 API
	@POST("/rest/member") 
	public Call<ResponseVO<Object>> signUp(@Header("apiKey") String apiKey, @Body InUserSignUpVO inUserSignUpVO); 
}
 