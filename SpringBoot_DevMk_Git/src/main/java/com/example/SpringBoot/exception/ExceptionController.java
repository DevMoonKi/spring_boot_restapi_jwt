package com.example.SpringBoot.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.SpringBoot.io.ResponseVO;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vivie on 2017-06-16.
 */

/*
 *  @ControllerAdvice는 Controller에서 예외가 던져질 경우에 처리하는 클래스입니다.
 *  그래서 404error를 처리하지 못합니다.
 * */

@Slf4j
@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseVO<?> exceptionHandler(Exception e){
    	
    	ResponseVO<Object> resp = new ResponseVO<Object>();
    	
    	resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    	resp.setCheck(false);
        final String errorMsg = e.getMessage();
        if(errorMsg == null){
        	resp.setMessage("fail");
        }else{
        	resp.setMessage(errorMsg);
        }
        
        this.handleUnauth(e, resp);

        this.handleLog(e);

        return resp;
    }
    
    private void handleUnauth(Exception e, ResponseVO<?> resp){
        if(e instanceof  UnauthorizedException){
        	HttpStatus unauth = HttpStatus.UNAUTHORIZED;
        	resp.setStatusCode(unauth);
        }
    }
    
    private void handleLog(Exception e){
    	if(logger.isInfoEnabled()){
            e.printStackTrace();
        }else{
        	logger.error("Error ::: {}", e.getMessage());
        }
    }
}
