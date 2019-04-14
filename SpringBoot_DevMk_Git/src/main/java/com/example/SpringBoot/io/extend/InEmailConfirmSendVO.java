package com.example.SpringBoot.io.extend;

import com.example.SpringBoot.io.EmailConfirmVO;

import io.swagger.annotations.ApiModelProperty;

public class InEmailConfirmSendVO extends EmailConfirmVO {
	
	@ApiModelProperty(value="이메일")
	private String email;
	@ApiModelProperty(value="이메일인증 URL")
	private String emailConfirmUrl;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailConfirmUrl() {
		return emailConfirmUrl;
	}
	public void setEmailConfirmUrl(String emailConfirmUrl) {
		this.emailConfirmUrl = emailConfirmUrl;
	}
	@Override
	public String toString() {
		return "InEmailConfirmSendVO [email=" + email + ", emailConfirmUrl=" + emailConfirmUrl + "]";
	}
}
