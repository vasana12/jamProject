package com.spring.jamplan.admin;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Service
public class AdminDAOService implements AdminDAO {

	@Autowired(required=false) 
	private SqlSession sqlSession;
	
	@Autowired(required=false)
	private AdminMapper adminMapper;
	
	@Override
	public ArrayList<TeamInfoVO> adminTeamSearch(TeamInfoVO teamInfo) {
		System.out.println("adminTeamSearch DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		ArrayList<TeamInfoVO> teamList = null;
		
		try {
			if(adminMapper.adminTeamSearch(teamInfo).size() != 0) {
				teamList = adminMapper.adminTeamSearch(teamInfo);
				System.out.println(teamList.get(0));
				return teamList;
			}else {
				System.out.println("null 나옴");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("adminTeamSearch DAOService OUT");
		return teamList;
	}

	@Override
	public ArrayList<PlanVO> adminPlanSearch(PlanVO plan) {
		System.out.println("adminPlanSearch DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		ArrayList<PlanVO> planList = adminMapper.adminPlanSearch(plan);
		
		System.out.println("adminPlanSearch DAOService OUT");
		return planList;
	}

	@Override
	public UserVO adminUserSearch(UserVO user) {
		System.out.println("adminUserSearch DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		user = adminMapper.adminUserSearch(user);
		
		System.out.println("adminUserSearch DAOService OUT");
		return user;
	}
	
	@Override 
	public int removeTeam(TeamInfoVO teamInfo) {
		System.out.println("removeTeam DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		int check = 0;
		
		if(adminMapper.removeTeam(teamInfo) != 0) {
			check = 1;
			System.out.println("removeTeam DAOService OUT");
			
			return check;
		}else {
			System.out.println("removeTeam DAOService OUT");
			return check;
		}
		
	}
	
	@Override 
	public int removeUser(UserVO user) {
		System.out.println("removeUser DAOService IN");
		adminMapper = sqlSession.getMapper(AdminMapper.class);
		int check = 0;
		
		if(adminMapper.removeUser(user) != 0) {
			check = 1;
			System.out.println("removeUser DAOService OUT");
			
			return check;
		}else {
			System.out.println("removeUser DAOService OUT");
			return check;
		}
	}

}
