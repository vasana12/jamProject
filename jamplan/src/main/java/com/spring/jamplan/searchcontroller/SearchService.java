package com.spring.jamplan.searchcontroller;


import java.util.ArrayList;
import java.util.List;

import com.spring.jamplan.model.LikeVO;
import com.spring.jamplan.model.PlanTableVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.UserVO;
import com.spring.jamplan.model.CommentVO;
import com.spring.jamplan.model.DataVO;


public interface SearchService {
	List<PlanVO> getPlanList(DataVO dataVO);
	ArrayList<PlanVO> planSearch(PlanVO planVO);
	ArrayList<PlanVO> clickSearch(PlanVO planVO);
	void moveSchedule();
	UserVO getUserId(String id);
	String likeCheck(LikeVO likeVO);
	String likeUpdate(LikeVO likeVO);
	int updateReadCount(PlanVO planVO);
	List<PlanTableVO> getPlanTablejson(PlanTableVO planTableVO);
	int getAllList(DataVO dataVO);
	
	int insertComment(CommentVO commentVO);
	ArrayList<CommentVO> getComments(int planNo);
	int deleteComment(CommentVO commentVO);

}
