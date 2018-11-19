package com.spring.jamplan.plan;

import java.util.ArrayList;
import java.util.Map;

import com.spring.jamplan.model.MapVO;
import com.spring.jamplan.model.PlanTableVO;
import com.spring.jamplan.model.PlanVO;



public interface PlanService {
	
   ArrayList<PlanTableVO> planTable(PlanTableVO planTableVO, MapVO mapVO);
   int savePlanTable (Map<String, Object> hm);
   int updateIsOpen(PlanVO planVO, PlanTableVO palnTableVO);
   
}