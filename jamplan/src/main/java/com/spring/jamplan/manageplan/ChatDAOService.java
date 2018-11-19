package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Service
public class ChatDAOService {
	
	@Autowired(required=false) 
	private SqlSession sqlSession;
	
	public ArrayList<TeamInfoVO> chatConnect(TeamInfoVO teamInfo) {
		ArrayList<TeamInfoVO> chatList = null;
		System.out.println("chatConnect IN");
		ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
		System.out.println(teamInfo.getTeamName());
		try {
			chatList = mapper.chatConnect(teamInfo);
			System.out.println("chatConnect에서 chatList가 나왔는지: " + chatList.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return chatList;
	}
	
	public UserVO getImageName(UserVO user) {
		try {
			System.out.println("getOnUserList DAOService IN");
			ChatMapper mapper = sqlSession.getMapper(ChatMapper.class);
			user = mapper.getImageName(user);
			
			System.out.println("getOnUserList DAOService OUT");
		}catch(Exception e) {
			System.out.println("AAAAAAAAAAAAAAAAAAAA");
			e.printStackTrace();
		}
		return user;
	}
}
