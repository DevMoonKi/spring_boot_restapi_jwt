package com.example.SpringBoot.rest.member.controller;

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
import com.example.SpringBoot.rest.member.service.MemberService;

import io.swagger.annotations.ApiOperation;

/*
@RestController 어노테이션을 통해 컨트롤러를 restful로 정의해줍니다.
이렇게 될 경우 @ResponseBody가 필요 없어집니다.
URL정
GET	index/retrieve	모든/특정 리소스를 조회
POST	create	리소스를 생성
PUT	update	리소스를 갱신
DELETE	delete	리소스를 삭제
@RequestBody 는 { "column" : "1"} 형태의 json을 받는다.
@RequestParam은 post나 get파라미터를 받는
*/

@RestController
@RequestMapping(value = "/rest")
public class MemberController{	
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	CommonComponent common;
	
	/**
	 * 회원 전체조회
	 * @param search
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/member")
	public ResponseVO<?> getSelectRestMemberList(@RequestParam SearchVO searchVO) throws Exception{
		ResponseVO<List<MemberVO>> resp = new ResponseVO<>();
		resp.setResponse(memberService.selectList(searchVO)); 
		return resp;
	}
	
	/**
	 * 사용자 상세조회
	 * @param seq
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/member/{seq}")
	public ResponseVO<?> getSelectRestMemberDetail(@PathVariable("seq") int seq) throws Exception{
		ResponseVO<MemberVO> resp = new ResponseVO<>();
		resp.setResponse(memberService.selectOne(seq));
		return resp;
	}
	
	/**
	 * 사용자 개인정보수정
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/member") //update @PathVariable("seq") int seq, 
	public ResponseVO<?> setUpdateRestMember(@RequestBody MemberVO memberVO) throws Exception{
		ResponseVO<?> resp = new ResponseVO<>();
		if(memberService.update(memberVO) != 1) {
			resp.setCheck(false);
		}
		return resp;
	}
	
	/**
	 * 사용자 탈퇴
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/member") //delete @PathVariable("seq") int seq,
	public ResponseVO<?> setDeleteRestMember(@RequestBody MemberVO memberVO) throws Exception{
		ResponseVO<?> resp = new ResponseVO<>();
		if(memberService.delete(memberVO) != 1) {
			resp.setCheck(false);
		}
		return resp;
	}
}
