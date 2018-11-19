package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class PlanVO {
   private int planNo;
   private int teamNo;
   private int goodCount;
   private int readCount;
   private int isOpen;
   private String boardName; /*쓰이는것찾기*/
   private String teamImage;
   private String planName; 
   private String selectDate;
   private String placeName;
   private String memo;
   
public int getPlanNo() {
   return planNo;
}
public void setPlanNo(int planNo) {
   this.planNo = planNo;
}
public int getTeamNo() {
   return teamNo;
}
public void setTeamNo(int teamNo) {
   this.teamNo = teamNo;
}
public int getGoodCount() {
   return goodCount;
}
public void setGoodCount(int goodCount) {
   this.goodCount = goodCount;
}
public int getReadCount() {
   return readCount;
}
public void setReadCount(int readCount) {
   this.readCount = readCount;
}
public int getIsOpen() {
   return isOpen;
}
public void setIsOpen(int isOpen) {
   this.isOpen = isOpen;
}
public String getBoardName() {
   return boardName;
}
public void setBoardName(String boardName) {
   this.boardName = boardName;
}
public String getTeamImage() {
   return teamImage;
}
public void setTeamImage(String teamImage) {
   this.teamImage = teamImage;
}
public String getPlanName() {
   return planName;
}
public void setPlanName(String planName) {
   this.planName = planName;
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