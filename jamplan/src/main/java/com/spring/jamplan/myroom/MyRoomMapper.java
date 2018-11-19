package com.spring.jamplan.myroom;

import java.util.ArrayList;

import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanUpdateVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;


/**
 * @author Taehyuk, Kim
 *
 */

public interface MyRoomMapper {
		
	ArrayList<TeamInfoVO> getTeamList(String id);
	ArrayList<TeamInfoVO> getTeamMember(UserVO user);
	ArrayList<TeamInfoVO> getPlanList(TeamInfoVO team);
	ArrayList<PlanUpdateVO> checkUpdate(UserVO user);
	UserVO getUserInfo(UserVO user);
	ArrayList<MessageVO> getMessageList(String receiver);
	ArrayList<TeamInfoVO> searchTeam(TeamInfoVO team);
	ArrayList searchPlan(TeamInfoVO plan);
	int makeTeam(TeamInfoVO team);
	TeamInfoVO findTeamName(PlanVO vo);
	void makePlan(PlanVO plan);
	void insertPlan(TeamInfoVO vo);
	void updateMessage(MessageVO vo);
	void insertToMember(TeamInfoVO team);
	ArrayList<TeamInfoVO> validationTeamName(TeamInfoVO team);
	int deleteTeam(TeamInfoVO team);
	int deletePlan(PlanVO plan);
	ArrayList<TeamInfoVO> getTeamInfo (TeamInfoVO vo);
	int getMaxPlanNo();
	void deleteNullPlan(String teamName);
	TeamInfoVO getRole(TeamInfoVO team);
	ArrayList<TeamInfoVO> getTeam(TeamInfoVO team);
	ArrayList<TeamInfoVO> getTeamReceiver(TeamInfoVO team);
	void insertApplyMessage(MessageVO vo);
	void deleteCansleMessage(MessageVO vo);
	ArrayList<MessageVO> checkApplyMessage(MessageVO vo);	
	ArrayList<MessageVO> getMessageListById(MessageVO vo);	
	ArrayList<TeamInfoVO> getTeamMemberList(TeamInfoVO vo);
}
