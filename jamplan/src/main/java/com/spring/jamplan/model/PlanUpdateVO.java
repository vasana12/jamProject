package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class PlanUpdateVO {
	private int planNo;
	private String confirmIndicator;
	private String dateCheck;
	
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public String getConfirmIndicator() {
		return confirmIndicator;
	}
	public void setConfirmIndicator(String confirmIndicator) {
		this.confirmIndicator = confirmIndicator;
	}
	public String getDateCheck() {
		return dateCheck;
	}
	public void setDateCheck(String dateCheck) {
		this.dateCheck = dateCheck;
	}
	
	
	
}
