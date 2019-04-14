package com.example.SpringBoot.rest.bbs.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBoot.common.CrudService;
import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.BbsVO;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.FileVO;
import com.example.SpringBoot.io.SearchVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;
import com.example.SpringBoot.rest.bbs.mapper.BbsMapper;

//@Service("restService") 하나의 인터페이스를 다수의 클래스가 구현할때
@Service("bbsService")
@Transactional
public class BbsService implements CrudService<SearchVO, Integer, BbsVO>{
	
	@Autowired
	BbsMapper<SearchVO, Integer, BbsVO> bbsMapper;

	@Override
	public List<BbsVO> selectList(SearchVO searchVO) throws Exception {
		return bbsMapper.getSelectRestBbsList(searchVO);
	}
	@Override
	public int selectCount(SearchVO searchVO) throws Exception {
		return bbsMapper.selectCount(searchVO);	
	}
	@Override
	public BbsVO selectOne(Integer seq) throws Exception {
		return bbsMapper.selectOne(seq);	
	}
	@Override
	public int insert(BbsVO bbsVO) throws Exception {
		return bbsMapper.insert(bbsVO);	
	}
	
	@Override
	public int update(BbsVO bbsVO) throws Exception {
		return bbsMapper.update(bbsVO);	
	}
	@Override
	public int delete(BbsVO bbsVO) throws Exception {
		return bbsMapper.delete(bbsVO);	
	}
	
	//사용안하지만 상속때문에 선언됨
	@Override
	public ResponseVO<Object> userEmailConfirm(EmailConfirmVO emailConfirmVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//사용안하지만 상속때문에 선언됨
	@Override
	public void userSignUpEmail(InUserSignUpVO inUserSignUpVO) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
