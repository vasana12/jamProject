package com.spring.jamplan.myinfo;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Service
public class MyInfoDAOService implements MyInfoDAO {
	
	@Autowired(required=false)
	private SqlSession sqlSession;
	
	@Autowired(required=false)
	private MyInfoMapper myInfoMapper;

	@Override
	public UserVO getMyInfo(UserVO user) {
		System.out.println("GET_MY_INFO IN");
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		try {
			user = myInfoMapper.getMyInfo(user);
		}catch (Exception e) {
			e.printStackTrace();
			return user;
		}
		System.out.println("GET_MY_INFO OUT");
		
		return user;
	}

	@Override
	public int updateMyInfo(UserVO user) {
		System.out.println("DAOService updateMyInfo IN");
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		System.out.println("매퍼 생성 지나감");
		int result = myInfoMapper.updateMyInfo(user);
		System.out.println("DAOService result = " + result);
		System.out.println("updateMyInfo OUT");
		return result;
	}
	
	// user의 프로필 사진을 등록하는 메소드
	@Override
	public int setProfileImage(UserVO user) {
		System.out.println("setProfileImage IN");
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = myInfoMapper.setProfileImage(user);
		
		System.out.println("setProfileImage OUT");
		return result;
	}

	@Override
	public ArrayList<TeamInfoVO> getTeamListAs(UserVO user) {
		System.out.println("getTeamListAs IN");
		ArrayList<TeamInfoVO> teamList = null;
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		teamList = myInfoMapper.getTeamListAs(user);
		
		System.out.println("getTeamListAs OUT");
		return teamList;
	}
	
	
	public int setTeamImage(TeamInfoVO team) {
		System.out.println("setTeamImage DAO IN");
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = 0;
		try {
			result = myInfoMapper.setTeamImage(team);
			
			if (result >= 1) {
				System.out.println("setTeamImage DAO OUT WITH SUCCESS");
			}else {
				System.out.println("setTeamImage DAO OUT WITH FAILURE");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public int removeTeamAsLeader(TeamInfoVO teamInfo) {
		System.out.println("removeTeamAsLeader IN");
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = myInfoMapper.removeTeamAsLeader(teamInfo);
		System.out.println(result);
		System.out.println("removeTeamAsLeader OUT");
		return result;
	}

	@Override
	public int signOutTeamAsMember(TeamInfoVO teamInfo) {
		System.out.println("signOutTeamAsMember IN");
		myInfoMapper = sqlSession.getMapper(MyInfoMapper.class);
		int result = myInfoMapper.signOutTeamAsMember(teamInfo);
		System.out.println(result);
		System.out.println("signOutTeamAsMember OUT");
		return result;
	}

}
