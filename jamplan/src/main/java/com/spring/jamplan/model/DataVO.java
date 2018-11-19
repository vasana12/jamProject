package com.spring.jamplan.model;
import org.springframework.stereotype.Component;

@Component
public class DataVO {
   int page;
   int startrow;
   int endrow;
   String selectDate;
   String readCount;
   String goodCount;
   String planName;
   String topic; // 제목or날짜
   String keyword;
   public int getPage() {
      return page;
   }
   public void setPage(int page) {
      this.page = page;
   }
   public int getStartrow() {
      return startrow;
   }
   public void setStartrow(int startrow) {
      this.startrow = startrow;
   }
   public int getEndrow() {
      return endrow;
   }
   public void setEndrow(int endrow) {
      this.endrow = endrow;
   }
   public String getSelectDate() {
      return selectDate;
   }
   public void setSelectDate(String selectDate) {
      this.selectDate = selectDate;
   }
   public String getReadCount() {
      return readCount;
   }
   public void setReadCount(String readCount) {
      this.readCount = readCount;
   }
   public String getGoodCount() {
      return goodCount;
   }
   public void setGoodCount(String goodCount) {
      this.goodCount = goodCount;
   }
   public String getPlanName() {
      return planName;
   }
   public void setPlanName(String planName) {
      this.planName = planName;
   }
   public String getTopic() {
      return topic;
   }
   public void setTopic(String topic) {
      this.topic = topic;
   }
   public String getKeyword() {
      return keyword;
   }
   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }
   
   @Override
   public String toString() {
      return "ListDTO [page=" + page + ", startrow=" + startrow + ", endrow=" + endrow + ", selectDate=" + selectDate
            + ", readCount=" + readCount + ", goodCount=" + goodCount + ", planName=" + planName + ", topic="
            + topic + ", keyword=" + keyword + "]";
   }
}