package com.spring.jamplan.main;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jamplan.model.PlanVO;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

		
@Service   
public class MainDAOService {
		
	@Autowired(required=false)
	private SqlSession sqlSession;// mybatis(ibatis) 라이브러리가 제공하는 클래스
	
	@Autowired(required=false)
	TeamInfoVO teamVO;
	
	@Autowired(required=false)
	UserVO user;
		
	private ArrayList<TeamInfoVO> list;
		
	@Inject
	private JavaMailSender mailSender;
		
	
	@Transactional
	public void create(UserVO vo) throws Exception {
	MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);	
	mainMapper.insertUser(vo); //회원가입 
	String key = new TempKey().getKey(50, false); // 인증키 생성
	//키값 이메일값, vo 객체에 저장
	vo.setAuthCode(key);
	mainMapper.createAuthKey(vo); // 인증키 DB저장
	MailHandler sendMail = new MailHandler(mailSender);
	sendMail.setSubject("[JAM Planner 서비스 이메일 인증]");
	sendMail.setText(
			new StringBuffer().append("<h1>클릭하시면 이메일 인증이 완료됩니다.</h1>").append("<a href='http://localhost:8800/jamplan/emailConfirm.do?email=").append(vo.getEmail()).append("&authCode=").append(key).append("&id=").append(vo.getId()).append("' target='_blenk'>이메일 인증 확인</a>").toString());
	sendMail.setFrom("wodud6349@gmail.com", "jamplanner");
	sendMail.setTo(vo.getEmail());
	sendMail.send();
	}
	
	public UserVO getUserInfo(String id) {
		
		System.out.println("getUserInfo start");
		System.out.println(sqlSession);
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
		
		
		user = mainMapper.getUserInfo(id); 
		System.out.println("user = " + user);
		
		return user;
	}	
		
	public ArrayList<TeamInfoVO> getTeamInfo(String id) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class); 
		list = mainMapper.getTeamInfo(id);
		
		return list;
	}	
		
	public List<PlanVO> getPlanjson() {
		List<PlanVO> planList = null;
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		// System.out.println("planList11="+planList);
		planList = mainMapper.getPlanList();
		//System.out.println("planList(DB갓다오고나서)="+planList);
		return planList;
	}
	public void insertUser(UserVO vo) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		mainMapper.insertUser(vo);
	}
	
	public int idcheck(String id) {
		MainMapper mainMapper = sqlSession.getMapper(MainMapper.class);
		int count= mainMapper.idcheck(id);
		return count;
		
	}


	public void update(UserVO vo) {
		MainMapper mainMapper =sqlSession.getMapper(MainMapper.class);
		mainMapper.createAuthKey(vo);
		
	}
		
}
		