package com.spring.jamplan.plan;

import java.util.ArrayList;
import java.util.Map;

import com.spring.jamplan.model.MapVO;
import com.spring.jamplan.model.PlanTableVO;
import com.spring.jamplan.model.PlanVO;

public interface PlanMapper {
    ArrayList<PlanTableVO> getPlanTable(PlanTableVO planTableVO);
	int deletePlanTable(PlanTableVO planTableVO);	
	ArrayList<MapVO> getPlanMap(MapVO mapVO);
	void insertPlanTable(PlanTableVO planTableVO);
	void updatePlanTable(PlanTableVO planTableVO);
	int savePlanTable (Map<String, Object> hm);
	ArrayList<PlanTableVO> getAllPlanTable(PlanTableVO planTableVO);
	int updateIsOpen(PlanVO planVO);
	void updateIsOpenPT(PlanTableVO planTableVO);
}