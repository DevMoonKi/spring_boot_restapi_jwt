package com.example.SpringBoot.io;

import io.swagger.annotations.ApiModelProperty;

public class FileVO{
	
	//@JsonIgnore json 객체 제외하기
	
	@ApiModelProperty(value="파일 키")
    private int restFileIdx;
	
	@ApiModelProperty(value="게시판 키")
    private int restBbsIdx;
	
	@ApiModelProperty(value="파일사이즈")
    private long fileSize;

	@ApiModelProperty(value="파일명")
    private String fileName;

	@ApiModelProperty(value="파일경로")
    private String filePath;
	
	@ApiModelProperty(value="파일타")
    private String fileType;
	
	@ApiModelProperty(value="등록")
    private String crtDtm;
    
	@ApiModelProperty(value="회원 키")
    private String memberIdx;

	public int getRestFileIdx() {
		return restFileIdx;
	}

	public void setRestFileIdx(int restFileIdx) {
		this.restFileIdx = restFileIdx;
	}

	public int getRestBbsIdx() {
		return restBbsIdx;
	}

	public void setRestBbsIdx(int restBbsIdx) {
		this.restBbsIdx = restBbsIdx;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getCrtDtm() {
		return crtDtm;
	}

	public void setCrtDtm(String crtDtm) {
		this.crtDtm = crtDtm;
	}

	public String getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(String memberIdx) {
		this.memberIdx = memberIdx;
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
