package com.example.SpringBoot.common;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface CrudMapper<E, P, V> {
	 List<V> selectList(E vo) throws Exception;
	 V selectOne(P seq) throws Exception;
	 V selectMap(V vo) throws Exception;
	 int selectCount(E vo) throws Exception;
	 int insert(V vo) throws Exception;
	 int update(V vo) throws Exception;
	 int delete(V vo) throws Exception;
}