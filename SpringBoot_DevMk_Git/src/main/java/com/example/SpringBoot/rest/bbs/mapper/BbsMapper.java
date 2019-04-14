package com.example.SpringBoot.rest.bbs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.SpringBoot.common.CrudMapper;
import com.example.SpringBoot.io.FileVO;

@Mapper
public interface BbsMapper<E, P, V> extends CrudMapper<E, P, V> {

	List<V> getSelectRestBbsList(E searchVO);
	 
}
