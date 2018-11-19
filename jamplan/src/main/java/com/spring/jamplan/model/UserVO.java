package com.spring.jamplan.model;

import org.springframework.stereotype.Component;

/**
 * @author wookim
 * @param 
 *	
 */

@Component
public class UserVO {
	String id;
	String email;
	String pass;
	String authCode;
	String image;	
	String signDate;
	int isAdmin;//1이 관리자 0이 유저
	
	
	
	public void printElement() {
		System.out.println(id);
		System.out.println(pass);
		System.out.println(email);
		System.out.println(image);
		System.out.println(isAdmin);
		System.out.println(signDate);

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

}
