package com.spring.jamplan.searchcontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jamplan.model.LikeVO;
import com.spring.jamplan.model.PlanTableVO;
import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.UserVO;
import com.spring.jamplan.model.CommentVO;
import com.spring.jamplan.model.DataVO;


@Service("searchService")
public class SearchServiceImpl implements SearchService{
   
   @Autowired
   private SqlSession sqlsession;
   
   @Override
   public List<PlanVO> getPlanList(DataVO dataVO) {
      List<PlanVO> planList = null;
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      System.out.println("Test1");
      planList = searchmapper.getPlanList(dataVO);
      System.out.println("Test2");
      return planList;
   }
   
   @Override
   public List<PlanTableVO> getPlanTablejson(PlanTableVO planTableVO) {
      List<PlanTableVO> planTableList = null;
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      planTableList = searchmapper.getPlanTableList(planTableVO);
      System.out.println("여기라우!!!!!!!!!!!!!!!!!");
      System.out.println(planTableList);
      
      return planTableList;
   }
   
 
   @Override
   public ArrayList<PlanVO> planSearch(PlanVO planVO) {
      //넘어오는값확인
      System.out.println("데이터확인 : " + planVO.getSelectDate());
      System.out.println("데이터확인 : " + planVO.getPlanName());
      
      ArrayList<PlanVO> planList = null;
      
      if (planVO.getSelectDate() != null) {
         System.out.println("타겟확인a");
         SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
         planList = searchmapper.planSearch(planVO);
         //System.out.println(planList.get(0).getPlanDate());
         System.out.println("타겟확인a");
      }
      else if (planVO.getPlanName() != null) {
         System.out.println("타겟확인b");
         SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
         planList = searchmapper.planSearch(planVO);
         System.out.println("타겟확인b");
         
         return planList;
      }
      return planList;
   }
   
   @Override
   public ArrayList<PlanVO> clickSearch(PlanVO planVO) {
      //넘어오는값확인
      System.out.println("데이터확인 : " + planVO.getSelectDate());
      System.out.println("데이터확인 : " + planVO.getReadCount());
      System.out.println("데이터확인 : " + planVO.getGoodCount());
      System.out.println("데이터확인 : " + planVO.getPlanName());
      
      ArrayList<PlanVO> planList = null;
   
      if(planVO.getSelectDate() != null) {
         System.out.println("클릭확인a");
         
         SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
         planList = searchmapper.clickSearch(planVO);
         System.out.println("클릭확인a");
         /*ArrayList<PlanVO> planList = null;
         return planList;*/
      
      }
      
      else if(planVO.getReadCount() == 1) {
         System.out.println("클릭확인1");
         
         SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
         planList = searchmapper.clickSearch(planVO);
         System.out.println("클릭확인1");
         
         return planList;
         }
      
      else if(planVO.getGoodCount() == 2) {
         System.out.println("클릭확인2");
         
         SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
         planList = searchmapper.clickSearch(planVO);
         System.out.println("클릭확인2");
         
         return planList;
         }
      
      else if(planVO.getPlanName() != null) {
         System.out.println("클릭확인b");
         
         SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
         planList = searchmapper.clickSearch(planVO);
         System.out.println("클릭확인b");
         return planList;

         }
         /*else {
            return planList;
         }*/
      return planList;
      
   
   }
   
   @Override
   public void moveSchedule() {
      System.out.println("move2");
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      searchmapper.moveSchedule();
      System.out.println("move3");
   
   }
   //로그인 테스트
   @Override
   public UserVO getUserId(String id) {
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      UserVO vo = searchmapper.getUserId(id);
      
      return vo;
   }
   
   
   //라이크체크
   @Override
   public String likeCheck(LikeVO likeVO) {
      System.out.println("like2");
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      System.out.println(likeVO.getId());
      //라이크정보 불러오기
      ArrayList<LikeVO> likeCount = searchmapper.getLikeCount(likeVO);
      System.out.println("9");
      System.out.println("likeList.get(0).getLikeCheck() : " + likeCount.get(0).getLikeCheck());
      
      int likeCheck = 0;
      String likeYn = "";
      //get(0) : 어레이리스트안으로가서 0번재 인덱스에있는..
      likeCheck = likeCount.get(0).getLikeCheck();
      
      if(likeCheck == 0) {
         System.out.println("0일때 들어왔다!");
         return "N";
      }
      else {
         System.out.println("0이 아닌게 들어왔다!");
         System.out.println(likeVO.getId());
         ArrayList<LikeVO> likeList = searchmapper.getLikeData(likeVO);
         likeYn = likeList.get(0).getLikeYn();
         System.out.println("likeYn!!!!!!!!!!" + likeYn);
         
         return likeYn;
      }
      
   }
   
   //라이크 업데이트
   @Override
   public String likeUpdate(LikeVO likeVO) {
      System.out.println("-------------------------------------- likeUpdate 시작  --------------------------------------");
      System.out.println("like2");
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      System.out.println(likeVO.getId());
      //라이크정보 불러오기
      ArrayList<LikeVO> likeCount = searchmapper.getLikeCount(likeVO);
      System.out.println("likeList.get(0).getLikeCheck() : " + likeCount.get(0).getLikeCheck());
      
      int likeCheck = 0;
      String likeYn = "";
      likeCheck = likeCount.get(0).getLikeCheck();
      System.out.println("0822");
      
      if(likeCheck == 0) {
         System.out.println("0일때 들어왔다!");
         //처음 좋아요를 누를때 row는 0인값, 즉 likeCheck == 0 으로 들어오게되고 눌렀을때 꽉찬하트로 표현해주기위해서 likeYn에 Y 값을 넣어주는 작업
         likeVO.setLikeYn("Y");
         System.out.println("like아이디@@@@"+likeVO.getId());
         System.out.println("like글번호@@@@"+likeVO.getPlanNo());
         searchmapper.insertLikeData(likeVO);
         
         
      }
      else {
         System.out.println("0이 아닌게 들어왔다!");
         searchmapper.updateLikeData(likeVO);
         System.out.println(likeVO.getId());
         System.out.println(likeVO.getPlanNo());
         System.out.println(likeVO.getLikeYn());
      }
      ArrayList<LikeVO> likeList = searchmapper.getLikeData(likeVO);
      likeYn = likeList.get(0).getLikeYn();
      System.out.println("likeYn!!!!!!!!!!" + likeYn);
      
      /*int planTotalCount = searchmapper.selectLikeCount(likeVO);*/
      
      
      searchmapper.updateTotalLike(likeVO);
      
      
      System.out.println("-------------------------------------- likeUpdate 종료  --------------------------------------");
      return likeYn;
      
   }
   
   
   public int updateReadCount(PlanVO planVO) {
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      int readCountRes = searchmapper.updateReadCount(planVO);
      
      return readCountRes;
   }
   
   
   
   // ---------- 모달 댓글 ------------ //
   @Override
   public ArrayList<CommentVO> getComments(int planNo){
	   System.out.println("getComments _ DAOS1");
	   SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
	   ArrayList<CommentVO> comments = searchmapper.getComments(planNo);
	   System.out.println("getComments _ DAOS2");
	   
	   return comments;
	   
   }
   
   @Override
   public int insertComment(CommentVO commentVO) {
	   System.out.println("insertComment _ DAOS1");
	   SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
	   int cnt = searchmapper.insertComment(commentVO);
	   System.out.println("insertComment _ DAOS2");
	   
	   return cnt;
   }
   
   @Override
   public int deleteComment(CommentVO commentVO) {
	   System.out.println("deleteComment _ DAOS1");
	   SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
	   int cnt = searchmapper.deleteComment(commentVO);
	   System.out.println("deleteComment _ DAOS2");
	   
	   return cnt;
   }
   
   //페이징 처리
   @Override
   public int getAllList(DataVO dataVO) {
      SearchMapper searchmapper = sqlsession.getMapper(SearchMapper.class);
      int res = searchmapper.getAllList(dataVO);
      System.out.println("all ok");
      return res;
   }

}