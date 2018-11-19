package com.spring.jamplan.searchcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.LikeVO;
import com.spring.jamplan.model.PagingVO;
import com.spring.jamplan.model.PlanTableVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.UserVO;
import com.spring.jamplan.model.CommentVO;
import com.spring.jamplan.model.DataVO;



@Controller
public class SearchController {
   
   @Autowired
   private SearchService searchService;
   
   
   @RequestMapping("main.search") 
   public String mainLoad() {
      return "search/searchPlanPage";
   }
   //검색페이지 이동
   @RequestMapping("plan.search")
   public String planLoad() {      
      return "search/searchPlanPage";
   }
   
   //planTable 리스트 뿌려주기
   @RequestMapping(value = "/getPlanList.search", method = RequestMethod.POST, produces="application/json;charset=utf-8")
   @ResponseBody
   public Map<String, Object> getPlanList(DataVO dataVO) {
	 //만약 page값이 0일경우 1로 변환하는 작업
	 if (dataVO.getPage() == 0) {
	    dataVO.setPage(1);
	 }
	 
	 int pageNum = 10;
	 int startrow = (dataVO.getPage()-1)*pageNum+1;
	 int endrow = startrow+pageNum-2; //startrow+pageNum-1 : 10이라는 값을 만들어주는 과정
	 dataVO.setStartrow(startrow);
	 dataVO.setEndrow(endrow);
	 System.out.println(dataVO.toString());
	 int listCount = searchService.getAllList(dataVO); // 처음 검색페이지 접근시 모두 불러오는 작업
	 
	 List<PlanVO> list = searchService.getPlanList(dataVO);
	 Map<String, Object> paraMap = new HashMap<String, Object>();
	 
	 PagingVO paging = new PagingVO();
	 paging.setData(dataVO.getPage(), pageNum, listCount);
	 
	 paraMap.put("list", list);
	 paraMap.put("paging", paging);
	 paraMap.put("dataVO", dataVO);
	 System.out.println("모두불러오기완료!@#$%^^&");
	 return paraMap;
  }

  //planTable 리스트 뿌려주기
   @RequestMapping(value = "/getPlanTable.search", method = RequestMethod.POST, produces="application/json;charset=utf-8")
   @ResponseBody
   public String getPlanTable(HttpSession session, PlanTableVO planTableVO) {
      System.out.println("test");
      
      System.out.println("----------------------------getTable----------------------------");
      System.out.println("planTableVO.getPlanNo()============="+planTableVO.getPlanNo());
      List<PlanTableVO> list = searchService.getPlanTablejson(planTableVO);
      //System.out.println("list.get(1).getPlanNo()="+list.get(0).getPlanNo());
      System.out.println("getPlanTable**************");
      String str = "";
      
      ObjectMapper mapper = new ObjectMapper();
      try {
         str = mapper.writeValueAsString(list);
         System.out.println("str=" + str);
      }
      catch (Exception e)
      {
         System.out.println("firest() mapper :" + e.getMessage());
      }
      return str;
   }

	//파일업로드페이지 이동
	@RequestMapping("formFile.search")
	public String formFile() {
	   return "search/formFile";
	}
	
	//일정검색컨트롤러
	@RequestMapping(value = "planSearch.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String planSearch(PlanVO planVO) {
	   //데이터 확인
	   System.out.println("jsp에서 넘어오는 값?" + planVO.getSelectDate());
	   System.out.println("jsp에서 넘어오는 값?" + planVO.getPlanName());
	         
	   ArrayList<PlanVO> planList = searchService.planSearch(planVO);
	   String str = "";
	   ObjectMapper mapper = new ObjectMapper();
	   
	   try {
	      str = mapper.writeValueAsString(planList);
	      System.out.println("str=" + str);
	   }
	   catch(Exception e) {
	      System.out.println("first() mapper :" + e.getMessage());
	   }
	   
	   return str;
	}
	
	//최신순,조회순...클릭시 관련된 사항 뿌려주는 컨트롤러
	@RequestMapping(value = "clickSearch.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String clickSearch(PlanVO planVO) {
	   //데이터 확인
	   System.out.println("jsp에서 넘어오는 값?" + planVO.getSelectDate());
	   System.out.println("jsp에서 넘어오는 값?" + planVO.getReadCount());
	   System.out.println("jsp에서 넘어오는 값?" + planVO.getGoodCount());
	   System.out.println("jsp에서 넘어오는 값?" + planVO.getPlanName());
	   
	   ArrayList<PlanVO> planList = searchService.clickSearch(planVO);
	   String str = "";
	   ObjectMapper mapper = new ObjectMapper();
	   
	   try {
	      str = mapper.writeValueAsString(planList);
	      System.out.println("str=" + str);
	   }
	   catch(Exception e) {
	      System.out.println("first() mapper :" + e.getMessage());
	   }
	   
	   return str;
	}
	
	//스케쥴페이지이동
	@RequestMapping(value = "schedule.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String moveSchedule(HttpServletRequest request, PlanVO planVO) {
	   System.out.println("moveSchedule");
	   
	   try {
	 	//조회수
	 	int readCountRes = searchService.updateReadCount(planVO);
	 	
	 	if (readCountRes != 0) {
	 		return "success";
	 	}
	   } catch (Exception e) {
	 	  e.printStackTrace();
	   }
	   
	   return "fail";
	}
	
	//login test
	@RequestMapping("login.search")
	public String moveLogin() {
	   System.out.println("movelogin");
	   return "search/login";
	}
	
	
	@RequestMapping("inpugLogin.search")
	public String inputLogin(HttpSession session, UserVO userVO) {
	   System.out.println("log");
	   UserVO vo = searchService.getUserId(userVO.getId());
	   System.out.println(vo.getId());
	   session.setAttribute("checkID", vo.getId());
	   return "search/searchPlanPage";
	}
	
	//like 불러오는거
	@RequestMapping(value = "heartCheck.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody 
	public Map<String, Object> likeCheck(LikeVO likeVO) {
	   System.out.println("like1");
	   System.out.println("아이디!!!!!!"+likeVO.getId());
	   System.out.println("글번호!!!!!!"+likeVO.getPlanNo());
	   Map<String, Object> retVal = new HashMap<String, Object>();
	   //라이크-로그인db에서 받아오기
	   /*LikeVO vo = searchService.likeUserId(likeVO.getUserId());
	   System.out.println(vo.getUserId());*/
	   
	   /*likeVO.setUserId((String)session.getAttribute("id"));*/
	   
	   String likeYn ="";
	   
	   
	   likeYn =  searchService.likeCheck(likeVO);
	   
	   System.out.println("@@@@@@@@@@@@@likeYn  =  " +likeYn);
	   System.out.println("like4");
	   retVal.put("likeYn", likeYn);
	   System.out.println("likeYn값" + likeYn);
	   System.out.println("retVal값" + retVal);
	   
	   
	   return retVal; 
	     
	}

	//like Update
	@RequestMapping(value = "likeUpdate.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody 
	public Map<String, Object> likeUpdate(LikeVO likeVO) {
	   System.out.println("like20");
	   Map<String, Object> retVal = new HashMap<String, Object>();
	   System.out.println("컨트롤러아이디#####"+likeVO.getId());
	   System.out.println("컨트롤러글번호#####"+likeVO.getPlanNo());
	   String likeYn ="";
	
	   searchService.likeUpdate(likeVO);
	
	   likeYn =  searchService.likeCheck(likeVO);
	   
	   System.out.println("updatelikeYn  =  " +likeYn);
	   retVal.put("likeYn", likeYn);
	   System.out.println("likeYn값" + likeYn);
	   System.out.println("retVal값" + retVal);
	   
	   
	   return retVal; 
	
	}
  
   // ---------- 모달 댓글 ------------ //
   @RequestMapping(value = "getComments.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
   @ResponseBody 
   public String getComments(int planNo) {
	   String str = "";
	   
	   System.out.println("getComments _ controller1");
	   ArrayList<CommentVO> comments = searchService.getComments(planNo);
	      
	   ObjectMapper mapper = new ObjectMapper();
      
	   try {
		   str = mapper.writeValueAsString(comments);
		   System.out.println("str=" + str);
	   }
	   catch(Exception e) {
		   System.out.println("first() mapper :" + e.getMessage());
	   }
	   
	   return str;
   }
   
   
   @RequestMapping(value = "insertComment.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
   @ResponseBody 
   public String insertComment(CommentVO commentVO) {
	   System.out.println("insertComment _ controller1");
	   //commentVO.setCnt(commentVO.getCnt()+1);
	  // System.out.println("commentVO.getCnt()="+commentVO.getCnt());
	   int retVal = searchService.insertComment(commentVO);
	   System.out.println("retVal = " + retVal);
	   
	   return String.valueOf(retVal);
   }
   
   @RequestMapping(value = "deleteComment.search", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
   @ResponseBody 
   public String deleteComment(CommentVO commentVO) {
	   int retVal;
	   System.out.println("deleteComment _ controller1");
	   retVal = searchService.deleteComment(commentVO);
	   
	   return String.valueOf(retVal);
   }
}
   