package com.example.SpringBoot.exception;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBoot.component.CommonComponent;
import com.example.SpringBoot.io.ResponseVO;

import io.swagger.annotations.ApiOperation;

/*
 *  404 예외가 던져질 경우에 처리하는 클래스입니다.
 * 일반 스프링 web.xml영역에서 설정했던 부분이다.
 *  ErrorConfiguration의 설정부분이다.
 */
 

@RestController
public class HttpErrorController implements ErrorController{
	
	@Autowired
	CommonComponent common;
	
	@ApiOperation(value="", notes = "에러처리")
	@RequestMapping("/error")
	public ResponseVO<?> handllerError(Locale locale) {
		ResponseVO<Object> rspn = new ResponseVO<Object>();
		//Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		//Integer statusCode = Integer.valueOf(status.toString());
		rspn.setStatusCode(HttpStatus.NOT_FOUND);
		rspn.setMessage("Page Not Found");
		rspn.setCheck(false);
		return rspn;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
/*	
	@ApiOperation(value="", notes = "apiKey 에러처리")
	@GetMapping("/apikey")
	public ResponseEntity<ResponseVO<Object>> apiKey(Locale locale){
		ResponseVO<Object> responseVO = new ResponseVO<>();
		String message = null;
		message = common.getMsg().getMessage("no_key",new String[] {"Api"}, locale);
		responseVO.setCheck(false);
		responseVO.setMessage(message);
		return new ResponseEntity<ResponseVO<Object>>(responseVO, HttpStatus.OK);
	}
*/	
}
	
