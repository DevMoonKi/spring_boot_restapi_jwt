package com.example.SpringBoot.rest.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.SpringBoot.common.CrudMapper;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.MemberVO;

@Mapper
public interface MemberMapper<E, P, V> extends CrudMapper<E, P, V> {

	int emailConfirmUpdate(EmailConfirmVO emailConfirmVO);

	int emailConfirmKeyCheck(EmailConfirmVO emailConfirmVO);

	void emailConfirmKeyDelete(EmailConfirmVO emailConfirmVO);

	void emailConfirmInsert(EmailConfirmVO emailConfirmVO);
	
	int idCheck(MemberVO memberVO);
	
}
