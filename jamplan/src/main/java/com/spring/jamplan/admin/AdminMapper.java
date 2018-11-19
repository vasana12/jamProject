package com.spring.jamplan.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Service
public interface AdminMapper {
	ArrayList<TeamInfoVO> adminTeamSearch(TeamInfoVO teamInfo);
	ArrayList<PlanVO> adminPlanSearch(PlanVO plan);
	UserVO adminUserSearch(UserVO user);
	
	int removeTeam(TeamInfoVO teamInfo);
	int removeUser(UserVO user);
}
