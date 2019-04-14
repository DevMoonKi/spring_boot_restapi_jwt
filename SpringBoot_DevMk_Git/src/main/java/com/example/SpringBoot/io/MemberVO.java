package com.example.SpringBoot.io;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberVO {
	
	//@JsonIgnore json 객체 제외하기
	
	@JsonProperty("memberIdx")
	@ApiModelProperty(value="회원 키")
    private int memberIdx;

    @JsonProperty("memberId")
	@ApiModelProperty(value="회원 아이디")
    private String memberId;

    @JsonProperty("memberName")
	@ApiModelProperty(value="회원 이름")
    private String memberName;

    @JsonProperty("memberPw")
	@ApiModelProperty(value="회원 비밀번호")
    private String memberPw;
    
    @JsonProperty("crdDtm")
	@ApiModelProperty(value="등록일")
    private String crdDtm;
    
    @JsonProperty("updDtm")
	@ApiModelProperty(value="수정일")
    private String updDtm;
    
    @JsonProperty("email")
	@ApiModelProperty(value="이메일")
	private String email;
    @JsonProperty("userTypeCode")
    
	@ApiModelProperty(value="사용자 타입")
	private String userTypeCode = "NORMAL";
    
    @JsonProperty("statusCode")
	@ApiModelProperty(value="사용자 상태")
	private String statusCode = "EMAIL_CONFIRM";

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserTypeCode() {
		return userTypeCode;
	}

	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode = userTypeCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getCrdDtm() {
		return crdDtm;
	}

	public void setCrdDtm(String crdDtm) {
		this.crdDtm = crdDtm;
	}

	public String getUpdDtm() {
		return updDtm;
	}

	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	@Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
