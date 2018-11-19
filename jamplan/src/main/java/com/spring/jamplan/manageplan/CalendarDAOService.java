package com.spring.jamplan.manageplan;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.CalendarVO;
import com.spring.jamplan.model.UserVO;

@Service
public class CalendarDAOService {
	@Autowired
	private SqlSession sqlSession;//mybatix(ibatis) 라이브러리가 제공하는 클래스
	
	private ArrayList<CalendarVO> voList;
	
	public ArrayList<CalendarVO> getSelectDate(int planNo){
		SelectCalendarMapper mapper = sqlSession.getMapper(SelectCalendarMapper.class);
		voList = mapper.getCalendarSelectDate(planNo);
		return voList;
	}
}
