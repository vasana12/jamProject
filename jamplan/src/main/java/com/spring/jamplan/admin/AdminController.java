package com.spring.jamplan.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Controller
public class AdminController {
	
	ArrayList<TeamInfoVO> teamList;
	ArrayList<PlanVO> planList;
	
	@Autowired(required=false)
	private AdminDAO adminDAO;
	
	@Autowired(required=false)
	private PlanVO plan;
	
	@Autowired(required=false)
	private UserVO user;
	
	@RequestMapping(value="adminPage.admin")
	public String adminPage(UserVO user) {
		return "admin/adminPage";
	}
	
	@RequestMapping(value="adminTeamSearch.admin", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String adminTeamSearch(HttpServletRequest request, TeamInfoVO teamInfo) {
		System.out.println("adminTeamSearch IN");
		teamInfo.setTeamName(request.getParameter("searchItem"));;
		System.out.println("teamInfo에 set 완료 = " + teamInfo.getTeamName());
		
		if (adminDAO.adminTeamSearch(teamInfo) != null) {
			System.out.println("CONTROLLER에서 DB갔다옴");
			teamList = adminDAO.adminTeamSearch(teamInfo);
			
			String teamListToJson = "";
			ObjectMapper mapper = new ObjectMapper();
			try {
				teamListToJson = mapper.writeValueAsString(teamList);
				System.out.println(teamListToJson);
			}catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("adminTeamSearch OUT with teamList");
			return teamListToJson;
			
		}else {
			System.out.println("adminTeamSearch OUT with null");
			return null;
		}
	}
	
	@RequestMapping(value="adminPlanSearch.admin", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String adminPlanSearch(HttpServletRequest request, PlanVO plan) {
		System.out.println("adminPlanSearch IN");
		plan.setPlanName(request.getParameter("searchItem"));
		System.out.println("plan에 set 완료 = " + plan.getPlanName());

		if(adminDAO.adminPlanSearch(plan).size() != 0) {
			planList = adminDAO.adminPlanSearch(plan);
			
			String planListToJson = "";
			ObjectMapper mapper = new ObjectMapper();
			try {
				planListToJson = mapper.writeValueAsString(planList);
				System.out.println(planListToJson);
			}catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("adminPlanSearch OUT with planList");
			return planListToJson;
		}else {
			System.out.println("adminPlanSearch OUT with null");
			return "null";
		}
	}
	
	@RequestMapping(value="adminUserSearch.admin", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String adminUserSearch(HttpServletRequest request, UserVO user) {
		System.out.println("adminUserSearch IN");
		user.setId(request.getParameter("searchItem"));
		System.out.println("user에 set 완료 = " + user.getId());
		
		if(adminDAO.adminUserSearch(user) != null) {
			String userToJson = "";
			ObjectMapper mapper = new ObjectMapper();
			try {
				userToJson = mapper.writeValueAsString(user);
				System.out.println(userToJson);
			}catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("adminUserSearch OUT");
			return userToJson;
		}else {
			return null;
		}
		
	}
	
	@RequestMapping(value="removeTeam.admin", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String removeTeam(TeamInfoVO teamInfo) {
		System.out.println("removeTeam In");
		System.out.println(teamInfo.getTeamName());
		int check = 0;
		
		if(adminDAO.removeTeam(teamInfo) != 0) {
			check = 1;
			
		}else {
		}
		String checkToString = String.valueOf(check);
		System.out.println("removeTeam Out");
		
		return checkToString;
	}
	
	@RequestMapping(value="removeUser.admin", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String removeUser(UserVO user) {
		System.out.println("removeUser In");
		System.out.println(user.getId());
		int check = 0;
		
		if(adminDAO.removeUser(user) != 0) {
			check = 1;
		}else {
		}
		
		String checkToString = String.valueOf(check);
		System.out.println("removeUser Out");
		
		return checkToString;
	}
}
