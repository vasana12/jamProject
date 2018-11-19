package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class LikeVO {
	private int planNo;
	private int likeCheck;
	private String id;
	private String likeYn;
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public int getLikeCheck() {
		return likeCheck;
	}
	public void setLikeCheck(int likeCheck) {
		this.likeCheck = likeCheck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLikeYn() {
		return likeYn;
	}
	public void setLikeYn(String likeYn) {
		this.likeYn = likeYn;
	}
	
	
	
	
	
}
