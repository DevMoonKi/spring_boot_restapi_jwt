package com.example.SpringBoot.rest.file.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.SpringBoot.common.CrudMapper;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.FileVO;
import com.example.SpringBoot.io.MemberVO;

@Mapper
public interface FileMapper<E, P, V> extends CrudMapper<E, P, V> {
	
	int fileInsert(FileVO fileVO);
	
	int selectMaxRestBbsIdx();
	
}
