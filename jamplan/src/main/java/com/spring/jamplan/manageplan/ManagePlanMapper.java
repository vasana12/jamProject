package com.spring.jamplan.manageplan;

import java.util.ArrayList;

import com.spring.jamplan.model.TeamInfoVO;

public interface ManagePlanMapper {
	public ArrayList<TeamInfoVO> getOthers(TeamInfoVO teamInfo);
}
