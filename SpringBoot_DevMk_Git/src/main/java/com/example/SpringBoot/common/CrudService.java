package com.example.SpringBoot.common;
import java.util.List;
import java.util.Map;

import com.example.SpringBoot.io.ResponseVO;
import com.example.SpringBoot.io.EmailConfirmVO;
import com.example.SpringBoot.io.FileVO;
import com.example.SpringBoot.io.extend.InUserSignUpVO;

/**
 * CRUD 서비스 인터페이스
 */
public interface CrudService<E , P , V> {

	/**
	 * 개수 반환
	 *
	 * @param params P
	 * @return 개수
	 */
	int selectCount(E params) throws Exception;

	/**
	 * 1건 반환
	 *
	 * @param params E
	 * @return 1건
	 */
	V selectOne(P params) throws Exception;

	/**
	 * 모두 반환
	 *
	 * @param params P
	 * @return 결과 List<Map<String, Object>
	 */
	List<V> selectList(E params) throws Exception;

	/**
	 * 등록
	 *
	 * @param params V
	 */
	int insert(V params) throws Exception;

	/**
	 * 수정
	 *
	 * @param params V
	 */
	int update(V params) throws Exception;

	/**
	 * 삭제
	 *
	 * @param params V
	 */
	int delete(V params) throws Exception;
	
	ResponseVO<Object> userEmailConfirm(EmailConfirmVO emailConfirmVO) throws Exception;

	void userSignUpEmail(InUserSignUpVO inUserSignUpVO) throws Exception;
	
}
