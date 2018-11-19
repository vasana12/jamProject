package com.spring.jamplan.snslogin;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.snslogin.SnsMapper;
import com.spring.jamplan.model.UserVO;

@Service   
public class SnsDAOService {
	@Autowired
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스
	
	public void insertUser(UserVO vo) {
		System.out.println("service 들어옴");
		SnsMapper snsMapper = sqlSession.getMapper(SnsMapper.class);
		System.out.println("매퍼들어오기전");
		snsMapper.insertUser(vo);
		System.out.println("매퍼들어갓다옴");
	}
	
	public UserVO getUserInfo(String email) {
			SnsMapper snsMapper = sqlSession.getMapper(SnsMapper.class);
			UserVO vo =snsMapper.getUserInfo(email);
				return vo;
	}
}