package com.spring.jamplan.myinfo;

import java.util.ArrayList;

import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

public interface MyInfoDAO {
	
	UserVO getMyInfo(UserVO user);
	int updateMyInfo(UserVO user);
	int setProfileImage(UserVO user);
	
	ArrayList<TeamInfoVO> getTeamListAs(UserVO user);
	int setTeamImage(TeamInfoVO team);
	int removeTeamAsLeader(TeamInfoVO teamInfo);
	int signOutTeamAsMember(TeamInfoVO teamInfo);
	
	
}
