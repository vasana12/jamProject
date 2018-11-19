package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.List;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

public interface MainMapper {

	public ArrayList<TeamInfoVO> getTeamInfo(String id);
	public UserVO getUserInfo(String id); 
	public List<PlanVO> getPlanList();
	public void insertUser(UserVO vo);
	public int idcheck(String id);
	public void createAuthKey(UserVO vo);
}
