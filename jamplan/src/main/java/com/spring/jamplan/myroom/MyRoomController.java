package com.spring.jamplan.myroom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.MessageVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;



@Controller
public class MyRoomController {

	@Autowired(required=false)
	private MyRoomDAO myRoomDAO;
	
	@Autowired(required=false)
	private TeamInfoVO vo;
	
	@Autowired(required=false)
	private PlanVO plan;
		
	private HashMap<String, Object> map;

	@RequestMapping(value="movePlanMainPage.do", method=RequestMethod.POST)
	public String movePlanMainPage(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, TeamInfoVO team) {
		
		String id =(String) session.getAttribute("id");
		ArrayList<TeamInfoVO> teamInfoList = null;
		try {
			//System.out.println("세션 planNo: "+session.getAttribute("planNo"));
			System.out.println("vo planName :" + team.getPlanName());	
			team.setId(id);
			System.out.println("vo.getId() =" + team.getId());
			TeamInfoVO teamVO =  myRoomDAO.getRole(team);
			teamInfoList = myRoomDAO.getTeam(team);
			Cookie rememberCookie = new Cookie("PLAN", team.getPlanName());
			
			if(team.getPlanName() != null) {
				rememberCookie.setMaxAge(60 * 60 * 24 * 7);
			}else {
				rememberCookie.setMaxAge(0);
			}
			
			response.addCookie(rememberCookie);
			
			System.out.println("team get role : " + teamVO.getRole());
			session.setAttribute("teamName", teamInfoList.get(0).getTeamName());
			session.setAttribute("planName", team.getPlanName());
			session.setAttribute("planNo", teamInfoList.get(0).getPlanNo());
			session.setAttribute("role", teamVO.getRole());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

		System.out.println("페이지 이동 컨트롤러 진입");
		
		return "managePlan/main";
	}
	
	
	@RequestMapping(value="/idSession.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String idSession(HttpSession session) {
		System.out.println("세션"+session.getAttribute("id"));
		
		map = new HashMap<String, Object>();
		map.put("id", (String)session.getAttribute("id"));
		String teamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			teamListToJson = mapper.writeValueAsString(map);
			System.out.println(teamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ajaxPrintTeamList OUT");
		return teamListToJson;
	}
	
	@RequestMapping(value="/getPlanListById.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String getPlanListById(HttpSession session, TeamInfoVO teamInfo) {
		System.out.println("session.getId() = " + session.getId());
		System.out.println("getPlanListById CONTROLLER IN");
		String id = (String)session.getAttribute("id");
		System.out.println("start getPlanListByTeamName id : " + id);
		teamInfo.setId(id);
		
		System.out.println("teamInfo.getId() = " + teamInfo.getId() + "입니다. ");
		ArrayList<TeamInfoVO> planList = myRoomDAO.getPlanListById(teamInfo);
		
		String planListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			planListToJson = mapper.writeValueAsString(planList);
			System.out.println("planListToJson는 " + planListToJson + "입니다. ");
		}catch (Exception e) {
			e.printStackTrace();
		}
		/*System.out.println("getPlanListByTeamName OUT");*/
		//System.out.println(teamListToJson);
		return planListToJson;
	}
	
	
	   @RequestMapping(value="/receivePlanNo.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	   @ResponseBody
	   public String receivePlanNo(TeamInfoVO tvo,HttpSession session, PlanVO pvo, MessageVO mvo) {
	      System.out.println("receivePlanNo.do");//컨트롤러 들어옴
	      
	      tvo = myRoomDAO.findTeamName(pvo);//팀 객체 가져옴
	      String sender = (String)session.getAttribute("id");//id 가져와서 sender변수에 넣음
	      String teamName = tvo.getTeamName();            //팀객체로부터 팀이름 가져옴
	      System.out.println("sender="+sender);            //sender 변수 확인
	      
	      mvo.setSender(sender);                        //메세지vo sender설정
	      mvo.setTeamName(teamName);               //메세지vo 팀 이름 설정      
	      map = new HashMap<String, Object>();
	      
	      try {
	         int check =myRoomDAO.insertApplyMessage(mvo);
	         if(check==0) {
	            map.put("res", "이미 해당 팀원임");
	         }else if(check==1){
	            map.put("res", "이미 신청 했음");
	         }else {
	            map.put("res","success");
	         }
	      }catch (Exception e) {
	         map.put("res","fail");
	         e.printStackTrace();
	      }
	      String searchTeamListToJson = "";
	      ObjectMapper mapper = new ObjectMapper();
	      try {
	         searchTeamListToJson = mapper.writeValueAsString(map);
	         System.out.println(searchTeamListToJson);
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      System.out.println("applyTeam Out");
	      return searchTeamListToJson;
	      }
	

	   @RequestMapping(value="/acceptToMember.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	   @ResponseBody
	   public String acceptToMember(HttpSession session, MessageVO vo) throws JsonProcessingException {
	      System.out.println("CONT acceptToMember IN");
	      myRoomDAO.insertToMember(vo, session);
	      String teamListToJson = "";
	      map = new HashMap<String, Object>();
	      ObjectMapper mapper = new ObjectMapper();
	      try {
	         map.put("res", "ok");
	         teamListToJson = mapper.writeValueAsString(map);
	         System.out.println(teamListToJson);
	         
	      }catch (Exception e) {
	         map.put("res", "fail");
	         teamListToJson = mapper.writeValueAsString(map);
	         e.printStackTrace();
	         
	      }
	      System.out.println("CONT acceptToMember OUT");
	      return teamListToJson;
	   }
	
	@RequestMapping(value="/updateMessage.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateMessage(HttpSession session) {
		System.out.println("CONT updateMessage IN");
		String receiver = (String)session.getAttribute("id");
		String result = myRoomDAO.updateReadMessage(receiver);
		
		System.out.println("CONT updateMessage OUT");
		
		return result;
	}
	
	@RequestMapping(value="/getMessageById.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String getMessageById(HttpSession session, MessageVO vo) {
		System.out.println("CONT getMessage IN");
		//server에서 id값으로 메세지 테이블 값 가져오기
		
		String receiver = (String)session.getAttribute("id");
		vo.setReceiver(receiver);
	
		ArrayList<MessageVO> messageList=null;
		
		String messageToString = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			messageList	= myRoomDAO.getMessageList(vo);
			
			
			if (messageList.size() == 0) {
				messageList.add(vo);
				messageToString = mapper.writeValueAsString(messageList);
				System.out.println("message은 빈 깡통입니다.");
				
			}else {
				messageToString = mapper.writeValueAsString(messageList);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("messageToString ========= " + messageToString);
		System.out.println("CONT getMessage out");
		return messageToString;
	}
	
	@RequestMapping(value="/ajaxPrintTeamList.do", method=RequestMethod.POST, produces="application/json;charset=utf-8")
	@ResponseBody
	public String ajaxPrintTeamList(HttpSession session) {
		System.out.println("ajaxPrintTeamList IN");
		String id = (String)session.getAttribute("id");
		List<TeamInfoVO> teamList = myRoomDAO.getTeamList(id);
		
		String teamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			teamListToJson = mapper.writeValueAsString(teamList);
			System.out.println(teamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ajaxPrintTeamList OUT");
		return teamListToJson;
	}
	
	@RequestMapping(value="/insertPlan.do", method=RequestMethod.POST)
    @ResponseBody
    public String insertPlan(HttpSession session, TeamInfoVO vo, PlanVO voP) {
       
       //vo에 teamName planName이 저장됨
       System.out.println("insertplan run");
       ArrayList<TeamInfoVO> list = null;
       //id 저장
       vo.setId((String)session.getAttribute("id"));
       System.out.println("vo.getId() = " + vo.getId());
       System.out.println("vo.getTeamName() = " + vo.getTeamName());
       System.out.println("vo.getPlanName() = " + vo.getPlanName());
       //id와 teamName으로 role teamNo등등 값 가져오기
       //팀네임이 같은 테이블 정보 전부 가져오기
       try {
          list = myRoomDAO.getTeamMemberList(vo);
          System.out.println("list.toString() = " + list.toString());
       }catch (Exception e) {
          System.out.println("DAO insertPlan FAIL");
          e.printStackTrace();
       }
       
       //planNo값이 가장 큰 값을 가져와 +1 증가시켜 planNo 설정하기
       int maxplanNo=0;
       try {
          maxplanNo = myRoomDAO.getMaxPlanNo() + 1;
          vo.setPlanNo(maxplanNo);
          voP.setPlanNo(maxplanNo);

          vo.setTeamNo(list.get(0).getTeamNo());
          voP.setTeamNo(list.get(0).getTeamNo());
       }catch (Exception e) {
          System.out.println("DAO insertPlan, set planNo FAIL");
          e.printStackTrace();
       }
       
       //설정된 teaminfo를 insert하기
       int check = 0;
       Map<String, String> resultMap = new HashMap<String, String>();
       String planListToJson = "";
       ObjectMapper mapper = new ObjectMapper();
       System.out.println("list 길이 : " + list.size());
       try {
          for(int i = 0 ; i < list.size(); i++) {
             //vo.setPlanName();되어 있다.
             //vo.setPlanNo();되어 있다.
             //팀에 속해 있는 모든 유저의 데이터 삽입
             System.out.println(i + "번째 작업 중");
             vo.setId(list.get(i).getId()); 
             vo.setRole(list.get(i).getRole());            
             //vo.setJoinDate(list.get(i).getJoinDate());
          }
          
          System.out.println("vop.getPlanName() = " + voP.getPlanName());
          System.out.println("vop.getPlanNo() = " + vo.getPlanNo());
          
          
          myRoomDAO.insertPlan(vo);
          myRoomDAO.makePlan(voP);
          
          ArrayList<TeamInfoVO> planList = myRoomDAO.getPlanListById(vo);
          planListToJson = mapper.writeValueAsString(planList);
          myRoomDAO.deleteNullPlanTeaminfo(vo.getTeamName());
          
          System.out.println("resultMapToJson = " + planListToJson);
             
       }catch (Exception e) {
          e.printStackTrace();
          
       }
       System.out.println("ajaxPrintTeamList OUT");
       return planListToJson;
    }

	
	@RequestMapping(value="/makeTeam.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String makeTeam(@RequestParam("file") MultipartFile file, MultipartHttpServletRequest multiRequest,
			TeamInfoVO team, @RequestParam(value="teamName", required=false) String teamName, HttpSession session) 
					throws IllegalStateException, IOException{
		
		Map<String, Object> checkMap = new HashMap<String, Object>();	
		String checkMapToJson = null;
		ObjectMapper mapper = new ObjectMapper(); 
		
		System.out.println("팀만들기 컨트롤러 / 팀이미지 업로드");
		System.out.println("teamName : " + teamName);
		
		file = multiRequest.getFile("file");
		System.out.println("file.getSize() = " + file.getSize());;
		// 해당 경로에 지정해준 이름의 폴더가 없으면 만들어주게된다.
		String uploadPath = "C:\\BigDeep\\upload\\";
		
		System.out.println("originalFileExtension 들어가기 전");

		// 실제 파일의 확장자
		String originalFileExtension = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		
		try {
			// 실제 파일명
			String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
			System.out.println("Make Team storedFileName=" + storedFileName);
			
			// 아이디와 팀명, 팀이미지를 저장한다.
			team.setId((String)session.getAttribute("id"));
			team.setTeamName(teamName);
			team.setTeamImage(storedFileName);
							
			int check = myRoomDAO.makeTeam(team);
			
			if(file.getSize() != 0) {
				file.transferTo(new File(uploadPath+storedFileName));
			}
			
			if(check == 1) {
				checkMap.put("check", "SUCCESS");
				checkMapToJson = mapper.writeValueAsString(checkMap.get("check"));
			}else {
				checkMap.put("check", "FAIL");
				checkMapToJson = mapper.writeValueAsString(checkMap.get("check"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return checkMapToJson;
	}

	
	@RequestMapping(value="/validationCheck.do", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String validationCheck(TeamInfoVO team, PlanVO plan) {
		System.out.println("validationCheck In");
		System.out.println(team.getTeamName());
		String check = myRoomDAO.validationTeamName(team);
		System.out.println("validationCheck Out");
		return check;
	}
	
	
	
	@RequestMapping(value="/updateCheck.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateCheck(UserVO vo) {
		
		ArrayList<TeamInfoVO> planList = myRoomDAO.checkUpdate(vo);
		
		String updateListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			updateListToJson = mapper.writeValueAsString(planList);
			System.out.println(updateListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateListToJson;
	}


	   @RequestMapping(value="/applyToTeam.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	   @ResponseBody
	   public String applyToTeam(HttpSession session, MessageVO vo) {
	      String sender = (String)session.getAttribute("id");
	      vo.setSender(sender);
	      
	      map = new HashMap<String, Object>();
	      
	      try {
	         int check = myRoomDAO.insertApplyMessage(vo);      
	         if(check==0) {
	            map.put("res", "이미 해당 팀원임");
	         }else if (check ==1) {
	            map.put("res", "이미 신청 했음");
	         }else {
	            map.put("res", "메세지 저장 완료");
	         }
	         
	      }catch (Exception e) {
	         map.put("res", "fail");
	      }

	      
	      String searchTeamListToJson = "";
	      ObjectMapper mapper = new ObjectMapper();
	      try {
	         searchTeamListToJson = mapper.writeValueAsString(map);
	         System.out.println(searchTeamListToJson);
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      System.out.println("applyTeam Out");
	      return searchTeamListToJson;
	   }
	
	
	//deleteCansleMessage
	@RequestMapping(value="/deleteMessageToTeam.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String deleteMessageToTeam(HttpSession session, MessageVO vo) {
		System.out.println("deleteMessageToTeam 진입");
		System.out.println("sender : "+ vo.getSender());
		System.out.println("teamName : "+vo.getTeamName());
		//String teamName = vo.getTeamName();
		
		if(vo.getSender() == null) {
			vo.setSender((String)session.getAttribute("id"));
		}
		map = new HashMap<String, Object>();
		try {
			int check = myRoomDAO.deleteCansleMessage(vo);		
			if(check==0) {
				map.put("res", "이미 해당 팀원임");
			}else if (check ==1) {
				map.put("res", "메세지 삭제");
			}else {
				map.put("res", "신청한적 없음");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			map.put("res", "fail");
		}
		
		String searchTeamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			searchTeamListToJson = mapper.writeValueAsString(map);
			System.out.println(searchTeamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("deleteApplyMessageTeam Out");
		return searchTeamListToJson;
	}
	
	
	   @RequestMapping(value="/deleteMessageTrToTeam.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	   @ResponseBody
	   public String deleteMessageTrToTeam(HttpSession session, MessageVO vo) {
	      System.out.println("deleteMessageToTeam 진입");
	      System.out.println("sender : "+ vo.getSender());
	      System.out.println("teamName : "+vo.getTeamName());
	      //String teamName = vo.getTeamName();
	      
	      if(vo.getReceiver() == null) {
	         vo.setReceiver((String)session.getAttribute("id"));
	      }
	      map = new HashMap<String, Object>();
	      try {
	         int check = myRoomDAO.deleteCansleTrMessage(vo, session);      
	         if(check==0) {
	            map.put("res", "이미 해당 팀원임");
	         }else if (check ==1) {
	            map.put("res", "메세지 삭제");
	         }else {
	            map.put("res", "신청한적 없음");
	         }
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	         map.put("res", "fail");
	      }
	      
	      String searchTeamListToJson = "";
	      ObjectMapper mapper = new ObjectMapper();
	      try {
	         searchTeamListToJson = mapper.writeValueAsString(map);
	         System.out.println(searchTeamListToJson);
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      System.out.println("deleteApplyMessageTeam Out");
	      return searchTeamListToJson;
	   }
	
	
	@RequestMapping(value="/searchTeam.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String searchTeam(TeamInfoVO team) {
		System.out.println("searchTeam In");
		System.out.println("team.getTeamName() : " + team.getTeamName() + " 입니다.");
		
		ArrayList<TeamInfoVO> teamList = myRoomDAO.searchTeam(team);
		
/*		if(teamList == null) {
			map = new HashMap<String, Object>();
			map.put("res", "null");
		}*/
		String searchTeamListToJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			searchTeamListToJson = mapper.writeValueAsString(teamList);
			System.out.println("searchTeamListToJson = " + searchTeamListToJson);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("searchTeam Out");
		
		return searchTeamListToJson;
	}
}