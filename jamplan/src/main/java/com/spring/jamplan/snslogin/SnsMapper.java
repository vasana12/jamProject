package com.spring.jamplan.snslogin;

import com.spring.jamplan.model.UserVO;

public interface SnsMapper {
	public void insertUser(UserVO vo);
	public UserVO getUserInfo(String email);
	
}