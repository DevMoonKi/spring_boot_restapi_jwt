package com.example.SpringBoot.rest.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBoot.common.CrudService;
import com.example.SpringBoot.component.CommonComponent;
import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.MemberVO;
import com.example.SpringBoot.io.SearchVO;
import com.example.SpringBoot.io.extend.InEmailConfirmSendVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;
import com.example.SpringBoot.rest.member.mapper.MemberMapper;
import com.example.SpringBoot.utils.TempKey;

//@Service("restService") 하나의 인터페이스를 다수의 클래스가 구현할때
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	MemberMapper<SearchVO, Integer, MemberVO> memberMapper;
	
	@Autowired
	CommonComponent common;
	
	@Value("${devmk.confirm.url}")
	private String emailConfirmUrl;
	
	@Override
	public int idCheck(MemberVO memberVO) throws Exception {
		return memberMapper.idCheck(memberVO);	
	}
	@Override
	public MemberVO selectMap(MemberVO memberVO) throws Exception {
		return memberMapper.selectMap(memberVO);	
	}
	
	// 회원가입 및 이메일 인증메일 발송
	@Override
	public void userSignUpEmail(InUserSignUpVO inUserSignUpVO) throws Exception {
		// 사용자 정보 입력
		memberMapper.insert(inUserSignUpVO);
		
		// 사용자 이메일 인증 정보처리
		TempKey tempKey = new TempKey();
		String key = tempKey.getKey(50);
		EmailConfirmVO emailConfirmVO = new EmailConfirmVO();
		emailConfirmVO.setMemberId(inUserSignUpVO.getMemberId());
		emailConfirmVO.setUserTypeCode(inUserSignUpVO.getUserTypeCode());
		emailConfirmVO.setEmailKey(key);
		memberMapper.emailConfirmInsert(emailConfirmVO);
		
		
		// 사용자 인증메일 발송
		InEmailConfirmSendVO inEmailConfirmSendVO = new InEmailConfirmSendVO();
		inEmailConfirmSendVO.setEmail(inUserSignUpVO.getEmail());
		inEmailConfirmSendVO.setEmailKey(key);
		inEmailConfirmSendVO.setEmailConfirmUrl(emailConfirmUrl);
		
		common.getAsync().signUpEmailConfirm(inEmailConfirmSendVO);
	}
	
	// 이메일 인증
	@Override
	public ResponseVO<Object> userEmailConfirm(EmailConfirmVO emailConfirmVO) {
		ResponseVO<Object> responseVO = new ResponseVO<>();
		// 사용자 상태값 변경
		int checkNum = memberMapper.emailConfirmUpdate(emailConfirmVO);
		
		boolean check = checkNum > 0;
		if(!check) {
			responseVO.setCheck(check);
			// 실패 이유 체크
			checkNum = memberMapper.emailConfirmKeyCheck(emailConfirmVO);
			
			responseVO.setCode(checkNum);
		}else {
			// 성공시 이메일 인증키 삭제
			memberMapper.emailConfirmKeyDelete(emailConfirmVO);
		}
		return responseVO;
		
	}
}
