<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%   String id= (String)session.getAttribute("id"); %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<title>Jam Planner</title>
		<meta name="description" content="">
    
   	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    
    <link href="https://fonts.googleapis.com/css?family=Crimson+Text:400,400i,600|Montserrat:200,300,400" rel="stylesheet">

	<link rel="stylesheet" href="./resources/mainPage/assets/css/bootstrap/bootstrap.css">
    
    <link rel="stylesheet" href="./resources/mainPage/assets/css/slick.css">
    <link rel="stylesheet" href="./resources/mainPage/assets/css/slick-theme.css">

    <link rel="stylesheet" href="./resources/mainPage/assets/css/helpers.css">
    <link rel="stylesheet" href="./resources/mainPage/assets/css/style.css">
    <link rel="stylesheet" href="./resources/mainPage/assets/css/landing-2.css">
    
    <spring:url value="./resources/mainPage/js/mainPageJs.js" var="mainPageJs" />
    <spring:url value="./resources/mainPage/css/mainPageCss.css" var="mainPageCss" />
    <script src="${mainPageJs}"></script>
    <link href="${mainPageCss}" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
    
   	
	</head>
	<body data-spy="scroll" data-target="#pb-navbar" data-offset="200">
	<!-- loader -->
    <div id="pb_loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#1d82ff"/></svg></div>
    
    <nav class="navbar navbar-expand-lg navbar-dark pb_navbar pb_scrolled-light" id="pb-navbar">
      <div class="container">
        <a class="navbar-brand" href="#">Jam Planner</a>
        <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#probootstrap-navbar" aria-controls="probootstrap-navbar" aria-expanded="false" aria-label="Toggle navigation">
          <span><i class="ion-navicon"></i></span>
        </button>
        <div class="collapse navbar-collapse" id="probootstrap-navbar">
          <ul class="navbar-nav ml-auto">
          
          	 <c:choose>
          	 	<c:when test="${id == null }">
		            <!-- <li class="nav-item"><a class="nav-link" href="#section-home">홈</a></li> -->
		            <li class="nav-item cta-btn ml-xl-2 ml-lg-2 ml-md-0 ml-sm-0 ml-0" data-toggle="modal" data-target="#modalJoin"><a class="nav-link" href="#" target="_blank">
		            	<span class="pb_rounded-4 px-4">Sign up</span></a>
		            </li>
          		</c:when>
          		<c:otherwise>
	            	<!-- <li class="nav-item"><a class="nav-link" href="#section-home">홈</a></li> -->
	            	<li class="nav-item dropdown cta-btn ml-xl-2 ml-lg-2 ml-md-0 ml-sm-0 ml-0">
	            		<a class="nav-link dropdown-toggle" href="#section-features" data-toggle="dropdown">
	            			<%=id%>
	            		</a>
	            		<div class="dropdown-menu">     
	                      <a class="dropdown-item infoBtn" href="#">My Info</a>
	                      <a class="dropdown-item myroomBtn" href="#section-reviews">My Room</a>
	                      <a class="dropdown-item logoutBtn" href="#">Logout</a>
	                 	</div>
	            	</li>
	            </c:otherwise>
            </c:choose>
          </ul>
        </div>
      </div>
    </nav>
    <!-- END nav -->

	<!-- cover-bg-indigo cover-bg-opacity -->
    <section class="pb_cover_v3 overflow-hidden text-left pb_gradient_v1" id="section-home">	<!--pb_gradient_v1 style.css에서 찾으면 됨 (색깔 넣기) // pb_slant-light가 페이지 기우뚱하게 만듦 -->
      
      <div class="container">
      
        <div class="row align-items-center justify-content-center">
          <div class="col-md-6">
            <h2 class="heading mb-3">Jam Planner</h2>
            <div class="sub-heading">
              <p>
              		계획한 순간, 이미 여행은 시작됩니다.<br/>
              		나와 비슷한 일정을 가진 사람들을 찾아보세요.
              </p>
              <p><a class="btn btn-success btn-lg pb_btn-pill smoothscroll" href="#section-pricing"><span class="pb_font-14 text-uppercase pb_letter-spacing-1">일정 찾아보기</span></a></p>
            </div>
          </div> 
          <div class="col-md-1">
          </div>
          <div class="col-md-5 relative align-self-center">
          <c:choose>
          	<c:when  test="${id == null }">
	          <form name="loginForm" action="/jamplan/login.do" method="POST" class="bg-white rounded pb_form_v1">
		          <h2 class="mb-4 mt-0 text-center">Login</h2>
		          <div class="form-group">
		            <input type="text" name="id" class="form-control pb_height-50 reverse" placeholder="ID">
		          </div>
		          <div class="form-group">
		            <input type="password" name="pass" class="form-control pb_height-50 reverse" placeholder="Password">
		          </div>
		          
		          <div class="form-group">
		            <input type="submit" class="btn btn-primary btn-lg btn-block pb_btn-pill btn-shadow-blue" value="Start">
		          </div>
		          <div class="form-group">
		          	<input type="button" class="btn btn-danger btn-lg btn-block pb_btn-pill btn-shadow-blue" value="ID/PW">
		          </div>
	          </form>
          	</c:when>
          	<c:otherwise>
	        	<div class="bg-white rounded pb_form_v1">
	         		<h2 class="mb-4 mt-0 text-center">Welcome &nbsp;<%=id%> !</h2>
		          	<div>
		          		<a class="text-center myroomBtn" href="#" onclick="return false;">
		          			<button type="button" class="btn btn-primary btn-lg btn-block">My Room</button>
		          		</a>
		          		<a class="text-center" href="/jamplan/main.search">
		          			<button type="button" class="btn btn-primary btn-lg btn-block">Search</button>
		          			                         
		          		</a>
		          		<a class="text-center logoutBtn" id="logoutBtn" href="#" onclick="return false;">
		          			<button type="button" class="btn btn-danger btn-lg btn-block">Logout</button>
		          		</a>
	             	</div>
	        	</div>
	        </c:otherwise>
	        
        	</c:choose>
          </div> 
        </div>
      </div>
    </section>
    
     <!-- 실험적으로 삽입한 application features -->
    <section class="pb_section">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 mb-5">
            <!-- <img src="./resources/mainPage/assets/images/phone_3.png" alt="Image placeholder" class="img-fluid"> -->
          </div>
          <div class="col-lg-8 pl-md-5 pl-sm-0">
            <!-- <div class="row">
              <div class="col-lg">
                <h2>Application Features</h2>
                <p class="pb_font-20">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia.</p>
              </div>
            </div> -->
            <div class="row">
              <div class="col-lg">
                
                <div class="media pb_feature-v2 text-left mb-1 mt-5">
                  <div class="pb_icon d-flex mr-3 align-self-start pb_w-15"><i class="ion-ios-bookmarks-outline pb_icon-gradient"></i></div>
                  <div class="media-body">
                    <h3 class="mt-2 mb-2 heading">나만의 여행</h3>
                    <p class="text-sans-serif pb_font-16">혼자라도 문제 없어요. 일단 기록하세요. 하나의 일정이 만들어집니다.</p>
                  </div>
                </div> 
                
                <div class="media pb_feature-v2 text-left mb-1 mt-5">
                  <div class="pb_icon d-flex mr-3 align-self-start pb_w-15"><i class="ion-ios-infinite-outline pb_icon-gradient"></i></div>
                  <div class="media-body">
                    <h3 class="mt-2 mb-2 heading">우리의 여행</h3>
                    <p class="text-sans-serif pb_font-16">여행 계획을 짜는 데서 우리의 여행은 시작됩니다.</p>
                  </div>
                </div> 

              </div>
              <div class="col-lg">
                
                <div class="media pb_feature-v2 text-left mb-1 mt-5">
                  <div class="pb_icon d-flex mr-3 align-self-start pb_w-15"><i class="ion-ios-speedometer-outline pb_icon-gradient"></i></div>
                  <div class="media-body">
                    <h3 class="mt-2 mb-2 heading">설렘 가득한 여행</h3>
                    <p class="text-sans-serif pb_font-16">낯선 곳에서의 만남은 여행을 더욱 설레게합니다.</p>
                  </div>
                </div>

                <div class="media pb_feature-v2 text-left mb-1 mt-5">
                  <div class="pb_icon d-flex mr-3 align-self-start pb_w-15"><i class="ion-ios-color-filter-outline  pb_icon-gradient"></i></div>
                  <div class="media-body">
                    <h3 class="mt-2 mb-2 heading">꿈꾸는 여행</h3>
                    <p class="text-sans-serif pb_font-16">사람들의 일정을 구경해보세요. </p>
                  </div>
                </div>    

              </div>
            </div>
            
          </div>
        </div>
      </div>
    </section>
    <div id="modalJoin" class="modal">
         <div class="modal-dialog">
 		 	<div class="modal-body in">  
                <div class="col-md-12 relative align-self-center">
            		<form id="joinForm" method="POST" action="join.do" class="bg-white rounded pb_form_v1">
            			<div>
              				<button id=closeJoin class="close mb-4 mt-0" type="button" data-dismiss="modal">&times;</button>
            			</div>
              			<h2 class="mb-4 mt-0 text-center">Sign Up</h2>
		                <div class="form-group">
	                    	<input type="text" class="form-control pb_height-50 reverse" id="email" name="email" placeholder="Email">
	                	</div> 
		         
		                <div class="form-group">
	                    	<input type="text" class="form-control pb_height-50 reverse" id="usr2" name="id" placeholder="ID">
	                	</div>
	                	
             			<div class="form-group"> 
		                    <input type="password" class="form-control pb_height-50 reverse" id="pwd2" name="pass" placeholder="Password">
	                	</div>
             			<div class="form-group">
	                    	<button type="button" id="idck" class="btn btn-danger btn-lg btn-block">아이디 중복체크</button>
	                    </div>
                 		<div class="form-group">
<!--                  			<img src="./resources/mainPage/assets/images/naver_login_complete_type.PNG">
 -->                  			<button type="button" onclick="location.href='${url}'" class="btn btn-primary btn-lg btn-block">네이버 아이디로 회원가입</button>
                   		</div>
                		<div class="form-group">
	                      <button type=submit id=joinB class="btn btn-primary btn-lg btn-block">회원가입</button>
	                	</div>                             
            		</form>
             	</div>
        	</div>
     	</div>
     </div>
	</body>
	<script src="./resources/mainPage/assets/js/jquery.min.js"></script>
    <script src="./resources/mainPage/assets/js/popper.min.js"></script>
    <script src="./resources/mainPage/assets/js/bootstrap.min.js"></script>
    <script src="./resources/mainPage/assets/js/slick.min.js"></script>
    <script src="./resources/mainPage/assets/js/jquery.waypoints.min.js"></script>
    <script src="./resources/mainPage/assets/js/jquery.easing.1.3.js"></script>
    <script src="./resources/mainPage/assets/js/main.js"></script>
</html>