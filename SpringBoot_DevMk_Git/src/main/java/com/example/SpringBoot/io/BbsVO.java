package com.example.SpringBoot.io;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModelProperty;

//@JsonInclude(JsonInclude.Include.NON_NULL) 널은 제외한다는 속성이다. upFile은 널이라서 안나옴.
@JsonSerialize
public class BbsVO {
	
	//@JsonIgnore json 객체 제외하기

	@JsonProperty("upFile")
	@ApiModelProperty(value="파일")
	private MultipartFile upFile;
	
	@JsonProperty("restBbsIdx")
	@ApiModelProperty(value="게시판 키")
    private int restBbsIdx;
	
	@JsonProperty("title")
	@ApiModelProperty(value="게시판 제목")
    private String title;

	@JsonProperty("contents")
	@ApiModelProperty(value="게시판 내용")
    private String contents;

	@JsonProperty("crtDtm")
	@ApiModelProperty(value="등록일")
    private String crtDtm;
    
	@JsonProperty("updDtm")
	@ApiModelProperty(value="수정일")
    private String updDtm;
    
	@JsonProperty("memberIdx")
	@ApiModelProperty(value="회원 키")
    private String memberIdx;
	
	@JsonProperty("fileName")
	@ApiModelProperty(value="파일 명")
    private String fileName;


	public int getRestBbsIdx() {
		return restBbsIdx;
	}


	public void setRestBbsIdx(int restBbsIdx) {
		this.restBbsIdx = restBbsIdx;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public String getCrtDtm() {
		return crtDtm;
	}


	public void setCrtDtm(String crtDtm) {
		this.crtDtm = crtDtm;
	}


	public String getUpdDtm() {
		return updDtm;
	}


	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}


	public String getMemberIdx() {
		return memberIdx;
	}


	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
	}
	
	public MultipartFile getUpFile() {
		return upFile;
	}


	public void setUpFile(MultipartFile upFile) {
		this.upFile = upFile;
	}
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
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
