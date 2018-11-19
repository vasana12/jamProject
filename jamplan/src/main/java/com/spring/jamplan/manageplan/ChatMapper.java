package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;


public interface ChatMapper {
	ArrayList<TeamInfoVO> chatConnect(TeamInfoVO teamInfo);
	UserVO getImageName(UserVO user);
}
