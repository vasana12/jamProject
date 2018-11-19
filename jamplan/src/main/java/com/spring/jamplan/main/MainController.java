package com.spring.jamplan.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;
import com.spring.jamplan.myroom.MyRoomDAOService;

@Controller
public class MainController {
	@Autowired(required = true)
	UserVO userVO;

	@Autowired(required = true)
	MainDAOService mDAOS;

	@Autowired(required = true)
	TeamInfoVO teamVo;

	@Autowired(required = true)
	MyRoomDAOService myRoomDAO;

	@RequestMapping("/home.do")
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return "main/JamHome";
	}

	@RequestMapping(value = "/emailConfirm.do", method = RequestMethod.GET)
	public String emailConfirm(UserVO vo, Model model, HttpServletResponse response) throws Exception { // 이메일인증
		System.out.println("emailConfirm.do 컨트롤러 진입");
		System.out.println("vo.getId()=" + vo.getId() + "의 이메일 인증 ");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		String str = "";
		UserVO uservo = mDAOS.getUserInfo(vo.getId());
		if (!(uservo == null)) {
			System.out.println("uservo.getAuthCode()=" + uservo.getAuthCode());
			System.out.println("vo.getAuthCode()=" + vo.getAuthCode());
			if (uservo.getAuthCode().equals(vo.getAuthCode())) {
				System.out.println("db코드와 비교");
				vo.setAuthCode("Y");
				mDAOS.update(vo);
				model.addAttribute("email", uservo.getEmail());
				System.out.println("uservo.getAuthcode()=" + uservo.getAuthCode());
				str = "main/JamHome";
			} else {
				out.println("<script>alert('잘못된 요청입니다1');</script>");
				out.flush();
				str = "main/JamHome";
			}
		} else {
			out.println("<script>alert('잘못된 요청입니다2');</script>");
			out.flush();
			str = "main/JamHome";
		}
		return str;
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginMain(HttpSession session, UserVO userVO, Model model, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String str = "";
		System.out.println("ID : " + userVO.getId() + " PW : " + userVO.getPass());
		UserVO vo = mDAOS.getUserInfo(userVO.getId());

		if (vo != null) {
			// id가 있고 pass워드가 같을 때
			if (vo.getPass().equals(userVO.getPass())) {
				if (vo.getAuthCode().equals("Y")) {
					session.setAttribute("id", vo.getId());
					model.addAttribute("id", vo.getId());
				} else {
					System.out.println("이메일 인증안된 아이디=" + vo.getId());
					System.out.println("이메일 인증을 해주세요");
				}
				str = "main/JamHome";
				// 아이디는 있으나 패스워드 틀렸을 때
			} else {
				System.out.println("비밀번호를 확인해주세요.");
				str = "main/JamHome";
			}
		}

		else {
			System.out.println("아이디를 다시 확인해주세요.");			
			str = "main/JamHome";
		}
		return str;
	}

	@RequestMapping(value="/join.do", method = RequestMethod.POST)
	public String JoinMain(UserVO vo, Model model, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("vo.getId()=" + vo.getId());
		String str = "";

		int count = mDAOS.idcheck(vo.getId());
		System.out.println("count=" + count);
		if (count == 0) {
			vo.setIsAdmin(0);
			vo.setImage("35485f3dd9b941498cc9127f8efa95c3.jpg");
			mDAOS.create(vo);
			out.println("<script>alert('회원가입 성공');</script>");
			out.println("<script>alert('이메일 인증을 완료하시면 로그인이 가능합니다');</script>");
			out.flush();
			str = "main/JamHome";
		} else {
			out.println("<script>alert('아이디 중복체크를 해주세요');</script>");
			out.flush();
			str = "main/JamHome";
		}
		return str;
	}

	@RequestMapping(value="/idcheck.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String idcheck(@RequestBody String userid) {
		System.out.println("idcheck METHOD IN");
		int count = 0;
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		System.out.println(userid);
		count = mDAOS.idcheck(userid);
		map.put("cnt", count);
		
		ObjectMapper mapper = new ObjectMapper();
		String mapStr = null;
		try {
			mapStr = mapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("firest() mapper :" + e.getMessage());
		}
		System.out.println("mapStr = " + mapStr);
		
		return mapStr;
	}

	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println(" >>>>>>>>>>>>>>>> LOGOUT METHOD IN <<<<<<<<<<<<<<<<<");
		request.getSession().removeAttribute("id");
		request.getSession().invalidate();
		
		response.sendRedirect("/jamplan/home.do");
	}

	@RequestMapping("/myroom.do")
	public ModelAndView myroom(HttpSession session, HttpServletRequest request, 
			HttpServletResponse respone, UserVO vo, ModelAndView mav, 
			@CookieValue(value="PLAN", required=false)Cookie rCookie) throws ServletException, IOException{

		ArrayList<String> cookieList = null;

		try {
			if(rCookie != null) {
				cookieList = new ArrayList<String>();
				cookieList.add(rCookie.getValue());
				mav.addObject("cookieList", cookieList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			vo = mDAOS.getUserInfo(id);
			ArrayList<TeamInfoVO> teamList = myRoomDAO.getTeamList(id);
			
			mav.addObject("teamList", teamList);
			mav.addObject("userVO", vo);
			mav.setViewName("myRoom/myRoomPage");
			System.out.println("userVO속성에 vo객체 담았음");
		} else {
			respone.sendRedirect("/jamplan/home.do");
		}
		return mav;
	}

	@RequestMapping("/searchplan.do")
	public ModelAndView searchplan(HttpSession session, UserVO vo, ModelAndView mav) {
		System.out.println("searchplan이동성공");
		if (session.getAttribute("id") != null) {
			String id = (String) session.getAttribute("id");
			vo = mDAOS.getUserInfo(id);
			mav.addObject("userVO", vo);
			mav.setViewName("search/searchPlanPage");
			System.out.println("userVO속성에 vo객체 담았음");
		} else {
			mav.setViewName("search/searchPlanPage");
		}
		return mav;
	}

}
