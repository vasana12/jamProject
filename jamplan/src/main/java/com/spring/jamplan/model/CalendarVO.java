package com.spring.jamplan.model;

import org.springframework.stereotype.Component;


/**
 * @author wookim
 * @param
 * 
 */
@Component
public class CalendarVO {
	String id;
	int planNo;
	String selectDate;
	String selectMonth;
	
	int dateCount;
	int confirmIndicator;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public String getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}
	public String getSelectMonth() {
		return selectMonth;
	}
	public void setSelectMonth(String selectMonth) {
		this.selectMonth = selectMonth;
	}
	public int getDateCount() {
		return dateCount;
	}
	public void setDateCount(int dateCount) {
		this.dateCount = dateCount;
	}
	public int getConfirmIndicator() {
		return confirmIndicator;
	}
	public void setConfirmIndicator(int confirmIndicator) {
		this.confirmIndicator = confirmIndicator;
	}	
	

}
