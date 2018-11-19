package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

/**
 * @author jieun
 *   
 */
@Component
public class MapVO {
   private int planNo;
   private String selectDate;
   private String id;
   private int pickCount;
   private String userColor;
   private String placeName;
   private double lat;
   private double lng;
   private String address;
   private int confirm;
   
   public int getPlanNo() {
      return planNo;
   }
   public void setPlanNo(int planNo) {
      this.planNo = planNo;
   }
   public String getSelectDate() {
      return selectDate;
   }
   public void setSelectDate(String planDate) {
      this.selectDate = planDate;
   }
   public int getPickCount() {
      return pickCount;
   }
   public void setPickCount(int pickCount) {
      this.pickCount = pickCount;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getUserColor() {
      return userColor;
   }
   public void setUserColor(String userColor) {
      this.userColor = userColor;
   }
   
   public String getPlaceName() {
      return placeName;
   }
   public void setPlaceName(String placeName) {
      this.placeName = placeName;
   }
   public double getLat() {
      return lat;
   }
   public void setLat(double lat) {
      this.lat = lat;
   }
   public double getLng() {
      return lng;
   }
   public void setLng(double lng) {
      this.lng = lng;
   }
   public String getAddress() {
      return address;
   }
   public void setAddress(String address) {
      this.address = address;
   }
   public int getConfirm() {
      return confirm;
   }
   public void setConfirm(int confirm) {
      this.confirm = confirm;
   }
   
}