package com.example.SpringBoot.rest.login.service;
import java.io.IOException;
import com.example.SpringBoot.api.EndpointAPI; 
import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;

import retrofit2.Call; 
import retrofit2.Response;
 
 public class UserSignUpAPI extends EndpointAPI<UserSignUp> {
	 
	 //아래의 super는 EndpointAPI클래스(부모)의 있는 생성자임
	 public UserSignUpAPI(String apiKey) { 
		 super(apiKey); 
	}
	 @Override 
	 protected UserSignUp create() { 
		 return retrofit.create(UserSignUp.class); 
	}
	 //사용자 가입
	 public ResponseVO<Object> signUp(InUserSignUpVO inUserSignUpVO){ 
		 
		 Call<ResponseVO<Object>> call = this.create().signUp(apiKey, inUserSignUpVO); 
		 
		 try {
		
			 Response<ResponseVO<Object>> response = call.execute();
			 
			 if(response.isSuccessful()) { 
				 return response.body(); 
			 }
			 
		 } catch(IOException e){ 
			 
			 e.printStackTrace(); 
			 
		 }
		 
		 return null; 
		 
	 }
}
