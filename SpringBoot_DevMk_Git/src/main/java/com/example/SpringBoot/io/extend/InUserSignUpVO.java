package com.example.SpringBoot.io.extend;

import com.example.SpringBoot.io.MemberVO;

import io.swagger.annotations.ApiModelProperty;

public class InUserSignUpVO extends MemberVO {
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="이메일인증 URL")
	private String emailConfirmUrl;
	
	public String getEmailConfirmUrl() {
		return emailConfirmUrl;
	}
	public void setEmailConfirmUrl(String emailConfirmUrl) {
		this.emailConfirmUrl = emailConfirmUrl;
	}
	@Override
	public String toString() {
		return "InUserSignUpVO [emailConfirmUrl=" + emailConfirmUrl + "]";
	}
}
