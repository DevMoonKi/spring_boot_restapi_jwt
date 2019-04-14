package com.example.SpringBoot.rest.login.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBoot.common.CrudService;
import com.example.SpringBoot.common.JwtService;
import com.example.SpringBoot.component.CommonComponent;
import com.example.SpringBoot.component.UserSignUpClient;
import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.MemberVO;
import com.example.SpringBoot.io.SearchVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;
import com.example.SpringBoot.rest.login.service.LoginService;
import com.example.SpringBoot.rest.member.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/rest")
public class LoginController{	
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	CommonComponent common;
	
	/**
	 * 아이디 중복조회
	 * @param memberVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login/check")
	public ResponseVO<?> idCheck(@RequestParam(value="memberId", required=false) String memberId) throws Exception{
		ResponseVO<MemberVO> resp = new ResponseVO<>();
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberId(memberId);
		resp.setSelectCheckCnt(loginService.idCheck(memberVO));
		return resp;
	}
	
	/**
	 * 회원 등록
	 * @param inUserSignUpVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/login/insert")
	public ResponseVO<?> setInsertMember(@RequestBody InUserSignUpVO inUserSignUpVO) throws Exception{
		ResponseVO<Object> responseVO = new ResponseVO<>();
		loginService.userSignUpEmail(inUserSignUpVO);
		return responseVO;
	}
	
	/**
	 * 회원 로그인 성공, 토큰 발급
	 * @param member
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @PostMapping(value="/login/login")
    public ResponseVO<?> signin(@RequestParam(value="memberId", required=false) String memberId, @RequestParam(value="memberPw", required=false) String memberPw, HttpServletResponse response) throws Exception{
    	ResponseVO<MemberVO> responseVO = new ResponseVO<>();
    	
    	MemberVO memberVO = new MemberVO();
    	memberVO.setMemberId(memberId);
    	memberVO.setMemberPw(memberPw);
    	
        MemberVO getMemberVO = loginService.selectMap(memberVO);
        
        if(getMemberVO.getMemberId() == null) {
        	responseVO.setCheck(false);
        	responseVO.setMessage("fali");
        }else {
	        String token = jwtService.create("member", getMemberVO, "user");
	        response.setHeader("Authorization", token);
	        responseVO.setResponse(getMemberVO);
        }
        
        return responseVO;
    }
	
	/**
	 * 사용자 이메일인증
	 * @param locale
	 * @param emailConfirmVO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="", notes = "사용자 이메일인증")
	@GetMapping("/login/emailConfirm")
	public ResponseVO<Object> emailConfirm(Locale locale, @RequestParam(value="emailKey", required=false) String emailKey) throws Exception{
		ResponseVO<Object> responseVO = null;
		String message = null;
		EmailConfirmVO emailConfirmVO = new EmailConfirmVO();
		
		emailConfirmVO.setEmailKey(emailKey);
		
		responseVO = loginService.userEmailConfirm(emailConfirmVO);
		
		if(responseVO.isCheck()) {
			message = common.getMsg().getMessage("success", new String[] {"E-mail authentication"}, locale);
		}else {
			if(responseVO.getCode() == 0) {
				message = common.getMsg().getMessage("no_key", new String[] {"emailKey"}, locale);
			}else if(responseVO.getCode() == 1) {
				message = common.getMsg().getMessage("no_validity", new String[] {"emailKey"}, locale);
			}
		}
		responseVO.setMessage(message);
		return responseVO;
	}
	
}
