package com.example.SpringBoot.rest.member.service;

import java.util.List;

import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.MemberVO;
import com.example.SpringBoot.io.SearchVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;

public interface MemberService {
	
	List<MemberVO> selectList(SearchVO searchVO) throws Exception;

	MemberVO selectOne(int seq) throws Exception;

	MemberVO selectMap(MemberVO memberVO) throws Exception;
	
	int insert(MemberVO memberVO) throws Exception;
	
	int update(MemberVO memberVO) throws Exception;

	int delete(MemberVO memberVO) throws Exception;
	
}
