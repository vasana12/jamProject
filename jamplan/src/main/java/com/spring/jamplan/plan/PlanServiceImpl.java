package com.spring.jamplan.plan;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.MapVO;
import com.spring.jamplan.model.PlanTableVO;
import com.spring.jamplan.model.PlanVO;



@Service("planService")
public class PlanServiceImpl implements PlanService {
   
   @Autowired
   private SqlSession sqlSession;
   
   @Override
   public ArrayList<PlanTableVO> planTable(PlanTableVO planTableVO, MapVO mapVO){
      ArrayList<PlanTableVO> planTableList = null;
      ArrayList<PlanTableVO> allPlanTableList = null;
      ArrayList<MapVO> planMapList = null;
      int i,check = 0;      
      
      PlanMapper planMapper = sqlSession.getMapper(PlanMapper.class);
      planMapList = planMapper.getPlanMap(mapVO);	//planMap에서 확정한 데이터만 갖고오기
      int totalCnt = planMapList.size();
      System.out.println("planMapList사이즈=" + totalCnt);
      
      //맵이나 달력에서 확정데이터 변경후,필요없는 데이터 삭제 작업
	  check = planMapper.deletePlanTable(planTableVO);
	  System.out.println("PT-delete-check = " + check);
	  
      if(totalCnt != 0) {
    	  for (i=0; i < totalCnt; i++) {               
    		  String getCalendar = planMapList.get(i).getSelectDate();
              planTableVO.setSelectDate(getCalendar);
               
              String getMap = planMapList.get(i).getPlaceName();
              planTableVO.setPlaceName(getMap);
               
              planTableVO.setPlanSeq(i);
              System.out.println("planSeq(=i) = " + i);
              
              planTableList = planMapper.getPlanTable(planTableVO);
              System.out.println("planTableList = " + planTableList);
               
              if(planTableList.size() == 0) {					//planTable에 해당 데이터가 없을 때 insert함
            	  planMapper.insertPlanTable(planTableVO);
            	  System.out.println("insert완료");
              }
              else {	
            	  planMapper.updatePlanTable(planTableVO);		// 해당 데이터 존재하면 시퀀스만 변경
            	  System.out.println("sequence update완료");
              }
              
    	  }	//for문 끝
          allPlanTableList = planMapper.getAllPlanTable(planTableVO);
          
          return allPlanTableList;
      } 
      else {
         System.out.println("다른 경우일때 들어왓다@@@@@@");
         return null;
      }
      
   }
   
   
   @Override
   public int savePlanTable (Map<String, Object> hm) {
	  PlanMapper planMapper = sqlSession.getMapper(PlanMapper.class);
	  System.out.println("savePlanSIMPL1");	
	   
	  int check = 0; 
	  try {
		  check  = planMapper.savePlanTable(hm);
	      System.out.println("check = " + check);
	      System.out.println("savePlanSIMPL2");
	  }
	  catch(Exception e) {
		  System.out.println("Error"+ e);
	  }
	  return check;
   }
   
   
 //isOpen Update
   @Override
   public int updateIsOpen(PlanVO planVO, PlanTableVO planTableVO) {
      PlanMapper planMapper = sqlSession.getMapper(PlanMapper.class);
      System.out.println("updateIsOpen Service 들어옴");
      int isOpen = planMapper.updateIsOpen(planVO);
      planMapper.updateIsOpenPT(planTableVO);
      System.out.println("isOpen Update complete");
      
      return isOpen;
   }
 
}