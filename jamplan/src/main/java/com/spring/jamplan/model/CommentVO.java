package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class CommentVO {
	private int planNo;
	private int cnt;
	private String id;
	private String content;
	
	
	
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
