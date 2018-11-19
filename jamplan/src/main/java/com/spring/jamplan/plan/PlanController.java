package com.spring.jamplan.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.MapVO;
import com.spring.jamplan.model.PlanTableVO;
import com.spring.jamplan.model.PlanVO;

@Controller
public class PlanController {

   
   @Autowired
   private PlanService planService;

   
  //get플랜테이블
   @RequestMapping(value = "planTable.plan", method = {RequestMethod.GET, RequestMethod.POST}, produces="application/json;charset=utf-8")
   @ResponseBody
   public String planTable(HttpSession session, PlanTableVO planTableVO, MapVO mapVO) {
	  ArrayList<PlanTableVO> pt = null; 
	  ObjectMapper mapper = new ObjectMapper();
	  String str="";
	  
      int planNo = (int)session.getAttribute("planNo");
      System.out.println("planNo확인=" + planNo);
      
      planTableVO.setPlanNo(planNo);
      mapVO.setPlanNo(planNo);
      
      try {
	      System.out.println("ptController1");
	      pt = planService.planTable(planTableVO, mapVO);	//planTable데이터(지도에서 확정한 장소에 대해서만) 가져오기
	      str = mapper.writeValueAsString(pt);
	      System.out.println("ptController2");
      }catch(Exception e) {
			System.out.println("PTcontroller1 에러: " + e.getMessage());
	  }
      System.out.println("planTable out: " + str);
	  
      return str;
      
   }
   
   //savePlanTable : 저장
   @RequestMapping(value = "savePlanTable.plan", method = RequestMethod.POST, produces="application/json;charset=utf-8")
   @ResponseBody
   public Map<String, Object> savePlanTable (HttpSession session, String[] memoList, int[] planSeqList) {
      System.out.println("memoList의값  = " + memoList + ", planSeqList의값  = " + planSeqList);
      System.out.println("savePlanTable Controller1");
     
      Map<String, Object> hm = new HashMap<String, Object>(); 
      Map<String, Object> retVal = new HashMap<String, Object>();
      
      int planNo = (int)session.getAttribute("planNo");
      int check = 0;
      
      try {
    	  for(int i=0; i<memoList.length;i++) {
    		  hm.clear();
    		  System.out.println("memoList[i] = " + memoList[i]);
    		  hm.put("memo", memoList[i]);
    		  hm.put("planSeq", planSeqList[i]);
    		  hm.put("planNo", planNo);
    		  
    		  System.out.println("hm = " + hm);
    		  
    		  check = planService.savePlanTable(hm);
        	  System.out.println("planTableController _ save _ check = " + check);
    	  }
    
    	  retVal.put("res", "OK");
      }
      catch (Exception e)
      {
         retVal.put("res", "FAIL");
         retVal.put("message", "Failure");
         e.printStackTrace();
      }
      
      return retVal; 
   }
   
 //isOepn update 기능
   @RequestMapping(value = "updateIsOpen.plan", method = RequestMethod.POST, produces="application/json; charset=utf-8")
   @ResponseBody
   public Map<String, Object> updateIsOpen (HttpSession session, PlanVO planVO, PlanTableVO planTableVO) {
      System.out.println("isOpenController 들어옴");
      System.out.println("isOpen값은???"+planVO.getIsOpen());
      int planNo = (int)session.getAttribute("planNo");
      System.out.println("planNo확인=" + planNo);
      planVO.setPlanNo(planNo);
      planTableVO.setPlanNo(planNo);
      
      int isOpen = 0;
      Map<String, Object> retVal = new HashMap<String, Object>();
      
      try {
         isOpen = planService.updateIsOpen(planVO, planTableVO);
         
         retVal.put("isOpen", isOpen);
         System.out.println(retVal);
      }
      catch (Exception e){
         e.printStackTrace();
         
      }
      return retVal;
   }
}