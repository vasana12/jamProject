package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class PlanTableVO {
   private int planNo;
   private int planSeq;
   private int isOpen;
   private String selectDate;
   private String placeName;
   private String memo;
   
public int getPlanNo() {
	return planNo;
}
public void setPlanNo(int planNo) {
	this.planNo = planNo;
}
public int getPlanSeq() {
	return planSeq;
}
public void setPlanSeq(int planSeq) {
	this.planSeq = planSeq;
}
public int getIsOpen() {
	return isOpen;
}
public void setIsOpen(int isOpen) {
	this.isOpen = isOpen;
}
public String getSelectDate() {
	return selectDate;
}
public void setSelectDate(String selectDate) {
	this.selectDate = selectDate;
}
public String getPlaceName() {
	return placeName;
}
public void setPlaceName(String placeName) {
	this.placeName = placeName;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
   
}