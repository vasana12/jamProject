package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

@Component
public class TeamInfoVO {

	private int teamNo;
	private String teamName;
	private String id ; 
    private int  role;
    private int planNo;
    private String planName ;
    private String joinDate;
    private String teamImage;
    
	public int getTeamNo() {
		return teamNo;
	}
	public void setTeamNo(int teamNo) {
		this.teamNo = teamNo;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getPlanNo() {
		return planNo;
	}
	public void setPlanNo(int planNo) {
		this.planNo = planNo;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
   
	public String getTeamImage() {
		return teamImage;
	}
	public void setTeamImage(String teamImage) {
		this.teamImage = teamImage;
	}
	
	
}
