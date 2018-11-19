package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */
@Component
public class MessageVO {
	String sender; //session에서 아이디 체크
	String receiver;
	String teamName;
	int isRead; //1이면 안읽음
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	
	
}
