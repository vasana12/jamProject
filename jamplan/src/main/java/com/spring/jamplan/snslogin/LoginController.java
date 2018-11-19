package com.spring.jamplan.snslogin;

import java.io.IOException;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;

import com.spring.jamplan.model.TeamInfoVO;
import com.spring.jamplan.model.UserVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	@Autowired(required = true)
	UserVO userVO;
	
	@Autowired
	SnsDAOService sDAOS;
	
	@Autowired
	TeamInfoVO teamVo;
    /* NaverLoginBO */
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;
    
    @Autowired
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }

    //로그인 첫 화면 요청 메소드
    @RequestMapping(value = "/home.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String login(Model model, HttpSession session) {
        
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);
        
        //네이버
        session.setAttribute("url", naverAuthUrl);
        model.addAttribute("url", naverAuthUrl);

        /* 생성한 인증 URL을 View로 전달 */
        return "main/JamHome";
    }
    
    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/callback.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(UserVO vo, Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
            throws IOException {
        System.out.println("여기는 callback");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
        model.addAttribute("result", apiResult);
        System.out.println("result"+apiResult);
        /* 네이버 로그인 성공 페이지 View 호출 */
        JsonStringParse jsonparse=new JsonStringParse();
        JSONObject jsonobj = jsonparse.stringToJson(apiResult, "response");
        String email = jsonparse.JsonToString(jsonobj, "email");
        String name = jsonparse.JsonToString(jsonobj, "name");
        System.out.println("email="+email);
        System.out.println("name="+name);

        vo = sDAOS.getUserInfo(email);
        
        if(!(vo==null))
        {
        	session.setAttribute("id",vo.getId());
        	System.out.println("vo.getId()="+vo.getId());
        }
        else
        {
        	System.out.println("SetId 하기 전 vo.getId()="+null);
        	UserVO userVO = new UserVO();
        	userVO.setId(email);
        	System.out.println("SetId 하고 난 후 vo.getId()="+userVO.getId());
        	sDAOS.insertUser(userVO);
        	System.out.println("회원가입까지 해줌");
        }
//      UserVO vo = new UserVO();
//      vo.setUser_snsId(snsId);
//      vo.setUser_name(name);
//
//      System.out.println(name);
//      try {
//          vo = service.naverLogin(vo);
//      } catch (Exception e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }


//      return new ModelAndView("user/loginPost", "result", vo);
        
        return "main/callback";
    }
}