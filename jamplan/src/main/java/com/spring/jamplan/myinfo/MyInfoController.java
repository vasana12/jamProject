package com.spring.jamplan.myinfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

@Controller
public class MyInfoController {
	
	ArrayList<TeamInfoVO> teamListAs = null;
	
	@Autowired(required=false)
	private MyInfoDAO myInfoDAO;
	
	@Autowired(required=false)
	private UserVO updatedUser;
	
	
	
	@RequestMapping(value="/myInfoPage.info", method=RequestMethod.POST)
	public ModelAndView myInfoPage(HttpSession session, HttpServletRequest request, UserVO user, ModelAndView mov) {
		System.out.println("myInfoPage CONTROLLER IN");
		
		user.setId(user.getId().replaceAll("'", ""));
		
		List<TeamInfoVO> teamListAs = null;

		try {
			if (user.getId().equals((String)session.getAttribute("id"))) {
				teamListAs = myInfoDAO.getTeamListAs(user);
				user = myInfoDAO.getMyInfo(user);
				
				int leaderNum = 0;
				int memberNum = 0;
				
				if (teamListAs.size() > 0) {
					for (TeamInfoVO teamInfo : teamListAs) {
						if (teamInfo.getRole() == 0)
							leaderNum += 1;
						else
							memberNum += 1;
					}
				}
				
				mov.setViewName("myInfo/infoPage");
				mov.addObject("teamListAs", teamListAs);
				mov.addObject("user", user);
				mov.addObject("leaderNum", leaderNum);
				mov.addObject("memberNum", memberNum);
				
				return mov;
			}else {
				mov.setViewName("main/JamHome");
				return mov;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("ERROR OCCURED");
		}
		
		mov.setViewName("main/JamHome");
		System.out.println("ERROR OCCURED");
		session.removeAttribute("id");
		
		System.out.println("myInfoPage CONTROLLER OUT");
		
		return mov;
	}
	
	
	@RequestMapping(value="/removeTeam.info", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String removeTeam(TeamInfoVO teamInfo) {
		System.out.println("removeTeam In");
		System.out.println(teamInfo.getTeamName());
		String check = String.valueOf(myInfoDAO.removeTeamAsLeader(teamInfo));
		System.out.println("removeTeam Out");
		return check;
	}
	
	@RequestMapping(value="/signOutTeam.info", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String signOutTeam(TeamInfoVO teamInfo) {
		System.out.println("signOutTeam In");
		System.out.println(teamInfo.getTeamName());
		String check = String.valueOf(myInfoDAO.signOutTeamAsMember(teamInfo));
		System.out.println("signOutTeam Out");
		return check;
	}
	
	
	@RequestMapping(value="/imageUpload.info", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public String imageUpload(@RequestParam("file")MultipartFile file, MultipartHttpServletRequest multiRequest, HttpSession session, 
			UserVO user, TeamInfoVO team, @RequestParam(value="team", required=false) String teamName) throws IllegalStateException, IOException {
		System.out.println("fileUpload IN");		
		
		user.setId((String)session.getAttribute("id"));
		
		System.out.println("TeamName = " + teamName);
		
		file = multiRequest.getFile("file");
		System.out.println("file.getSize() = " + file.getSize());;
		// 해당 경로에 지정해준 이름의 폴더가 없으면 만들어주게된다.
		String uploadPath = "C:\\BigDeep\\upload\\";
		
		System.out.println("originalFileExtension 들어가기 전");

		// 실제 파일의 확장자
		String originalFileExtension = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		System.out.println(originalFileExtension);

		// 실제 파일명
		String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
		System.out.println("storedFileName=" + storedFileName);
		
		
		team.setId((String)session.getAttribute("id"));
		team.setTeamName(teamName);
		team.setTeamImage(storedFileName);
		
		if(file.getSize() != 0) {
			file.transferTo(new File(uploadPath+storedFileName));
		}
		
		try {
			if(myInfoDAO.setTeamImage(team) >= 1) {
				System.out.println("fileUpload DAO PROCESS OUT");
				return "success";
			}	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("fileUpload OUT FAIL");
		return "fail";               
	}
	
	
	@RequestMapping(value="/updateMyInfo.info")
	public ModelAndView updateMyInfo(UserVO user, ModelAndView mav, HttpServletResponse response) throws Exception {
		System.out.println("UPDATE_MY_INFO In");
		
		try {
			myInfoDAO.updateMyInfo(user);
			
			teamListAs = myInfoDAO.getTeamListAs(user);
			updatedUser = myInfoDAO.getMyInfo(user);
			mav.addObject("teamListAs", teamListAs);
			mav.addObject("user", updatedUser);
			mav.setViewName("myInfo/infoPage");
			
			return mav;
			
		}catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("/jamplan/home.do");
		}
		
		System.out.println("UPDATE_MY_INFO Out");
		
		return mav;
	}
	
}
