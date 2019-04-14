package com.example.SpringBoot.rest.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper<SearchVO, Integer, MemberVO> memberMapper;
	
	@Autowired
	CommonComponent common;

	@Override
	public List<MemberVO> selectList(SearchVO search) throws Exception {
		return memberMapper.selectList(search);
	}
	
	@Override
	public MemberVO selectOne(int seq) throws Exception {
		return memberMapper.selectOne(seq);	
	}
	@Override
	public MemberVO selectMap(MemberVO memberVO) throws Exception {
		return memberMapper.selectMap(memberVO);	
	}
	@Override
	public int insert(MemberVO memberVO) throws Exception {
		return memberMapper.insert(memberVO);	
	}
	@Override
	public int update(MemberVO memberVO) throws Exception {
		return memberMapper.update(memberVO);	
	}
	@Override
	public int delete(MemberVO memberVO) throws Exception {
		return memberMapper.delete(memberVO);	
	}
	
}
