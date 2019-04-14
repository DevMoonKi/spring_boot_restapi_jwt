package com.example.SpringBoot.io;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.example.SpringBoot.io.MemberVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/*	응답 도메인
 * */
@Data
public class ResponseVO<E>{
    //<E>의는 파라미터로 해당타입을 받을 수 있다.
	
	@ApiModelProperty(value="응답코드")
	private HttpStatus statusCode = HttpStatus.OK;
	@ApiModelProperty(value="상태코드")
	private int code;
	@ApiModelProperty(value="메세지")
    private String message;
	@ApiModelProperty(value="성공여부")
    private boolean check = true;
	@ApiModelProperty(value="json데이터")
    private E response;
	@ApiModelProperty(value="json데이터 data건수")
    private int responseRowCnt;
	@ApiModelProperty(value="data체크 건수")
    private int selectCheckCnt;
	
	
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public E getResponse() {
		return response;
	}
	public void setResponse(E response) {
		this.response = response;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getResponseRowCnt() {
		return responseRowCnt;
	}
	public void setResponseRowCnt(int responseRowCnt) {
		this.responseRowCnt = responseRowCnt;
	}
	public int getSelectCheckCnt() {
		return selectCheckCnt;
	}
	public void setSelectCheckCnt(int selectCheckCnt) {
		this.selectCheckCnt = selectCheckCnt;
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
