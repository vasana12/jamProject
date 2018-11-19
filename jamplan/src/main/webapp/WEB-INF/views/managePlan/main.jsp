<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.spring.jamplan.model.TeamInfoVO"%>
<%@page import="com.spring.jamplan.model.PlanVO"%>
<%@page import="com.spring.jamplan.model.UserVO"%>

<%
   if ((String)session.getAttribute("id") == null) {
      response.sendRedirect("/jamplan/home.do");
   }
   
   String id = (String) session.getAttribute("id");
   String teamName = (String)session.getAttribute("teamName");

   String planName = (String)session.getAttribute("planName");

%>
<!DOCTYPE html>
<html lang="ko">
<head>

   <script src="https://code.jquery.com/jquery-3.3.1.js"
     integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous"></script>
        
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
   integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
   crossorigin="anonymous"></script>
   
   <script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

   <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300, 400,700" rel="stylesheet">
    <link rel="stylesheet" href="./resources/planMain/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/planMain/css/animate.css">
    <link rel="stylesheet" href="./resources/planMain/css/owl.carousel.min.css">
    <!-- Theme Style -->
    <link rel="stylesheet" href="./resources/planMain/css/style.css">
    
   <spring:url value="./resources/planMain/js/planMain.js" var="mainPageJs" />
   <spring:url value="./resources/planMain/css/planMain.css" var="mainPageCss" />
   
   <script src="${mainPageJs}" charset="utf-8"></script>
   <link href="${mainPageCss}" rel="stylesheet" />
   
   <link rel="stylesheet" href="./resources/planMain/fonts/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="./resources/planMain/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="./resources/planMain/fonts/flaticon/font/flaticon.css">
   

   <title>Jam Planner</title>
   <!-- ======================================== 여기까지가 살릴 부분 ===========================-->
   <script>
	
		$(document).ready(function () {
			
			// ============ websocket 부분에 대한 스크립트 ==================
			var log = function (s) {
            
	            let payLoad = s.split('/');
	            let name = payLoad[0];
	            let message = payLoad[1];
	            let str = name + '> ' + message;
	            let div = document.createElement('div');
	            div.setAttribute('class', 'msg_right');
	            div.innerText = str;
	            
	            if (name == '<%=id %>') {
	               
	               let str = message;
	               div.setAttribute('class', 'msg_right msg');
	               div.innerText = str;
	               
	               // 이 부분에 메시지 형식 넣어야함.
	               $("#chatTextarea").append(div);
	            }else {
	               let str = name + '> ' + message;
	               div.setAttribute('class', 'msg');
	               div.innerText = str;
	               $("#chatTextarea").append(div);
	            }
         	}
		
			var id = '<%=id%>';
			var teamName = '<%=teamName %>';
			var planName = '<%=planName%>';
		
			w = new WebSocket("ws://192.168.0.214:8800/jamplan/planMainChat?id="
					+id + "&teamName=" + teamName + "&planName=" + planName);
			// 서버에서 handshaking이 성공적으로 끝나면 자동으로 호출되는 메서드
			
			w.onopen = function () {
				//alert("WebSocket Connected!");
			}
			w.onmessage = function(e) {
				log(e.data.toString());
			}
			w.onclose = function(e) {
				log("WebSocket closed!!");
			}
			w.onerror = function(e) {
				log("WebSocket error!!");
			}
			
			
			document.getElementById("sendButton").onclick = function() {
				var input = document.getElementById('inputText').value;
				w.send(id + "/" + input);
				$('#inputText').val('');
			}
			
			// =============== websocket 부분에 대한 스크립트 끝 ===============
		})
	</script>
</head>
<body>
   
   <header role="banner">
      <div class="top-bar">
        <div class="container">
          <div class="row">
            <div class="col-4 social">
               <!-- empty -->
            </div>
            <div class="col-8 social">
               <a href="#" id="searchButton" style="float:right;"><span class="pb_rounded-4 px-2">Search</span></a>
               <a href="#" id="infoButton" style="float:right;" value="<%=id%>"><span class="pb_rounded-4 px-2">Info</span></a>
                 <span id="messageBut" class="pb_rounded-4 px-2" data-toggle="modal" data-target="#messageModal" >
                    <a href="#" style="float:right;"><span id="countLabel" class="label label-primary"></span></a>
                    <a href="#" id="messageBut" class="pb_rounded-4 px-2" style="float:right;">
                       Message 
                    </a>
                 </span>                  
            </div>
          </div>
        </div>
      </div>

      <div class="container logo-wrap">
        <div class="row pt-5">
          <div class="col-12 text-center">
            <a class="absolute-toggle d-block d-md-none" data-toggle="collapse" href="#navbarMenu" role="button" aria-expanded="false" aria-controls="navbarMenu"><span class="burger-lines"></span></a>
            <h1><a href="#<%=planName %>"><%=planName %></a></h1> 
          </div>
        </div>
      </div>
      
      <nav class="navbar navbar-expand-md  navbar-light bg-light">
        <div class="container">
          
         
          <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav mx-auto">
              <li class="nav-item">
                <a class="nav-link active" href="home.do">Home</a>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link" href="/jamplan/myroom.do" id="myroomBtn">My Room</a>
            
              </li>

              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="category.html" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Plans</a>
                <div id="dropdown-list" class="dropdown-menu" aria-labelledby="dropdown05">
                </div>

              </li>
             <!--  <li class="nav-item">
                <a class="nav-link" href="contact.html">Contact</a>
              </li> -->
            </ul>
            
          </div>
        </div>
      </nav>
    </header>
    
    <section class="site-section py-lg">
      <div class="container">
        
        <div class="row blog-entries">
           <div class="col-md-12 col-lg-8 main-content">
           
            <div id="planManage" class="col-md-9">
               <ul class="nav nav-tabs">
                  <li><a href="#calendarContent" data-toggle="tab" class="nav-link active moveLink" id="calendarTab">Calendar</a></li>
                  <li><a href="#mapContent" data-toggle="tab" class="nav-link moveLink" id="mapTab">Map</a></li>
                  <li><a href="#planTableContent" data-toggle="tab" class="nav-link moveLink" id="planTableTab">PlanTable</a></li>
                  <!-- <li><a href="#viewAllContent" data-toggle="tab" class="nav-link moveLink" id="viewAllTab">View all</a></li> -->
               </ul>
               <div class="tab-content divContent">
                   <div class="tab-pane container active" id="calendarContent"></div>
                  <div class="tab-pane container fade" id="mapContent"></div>
                  <div class="tab-pane container fade" id="planTableContent"></div>      
                  <!-- <div class="tab-pane container fade" id="viewAllContent"></div> -->
               </div>
            </div>
            </div>

          <!-- END main-content -->

             <div class='sidebar-box'>
                 <div id="chat" class="col-md-3">
                  <!-- 아바타 이미지 들어가는 곳 -->
                  <div id="profileImage"></div>
   
                  <button id="openBtn" class="open-button">Chat</button>
                  <div class="form-popup" id="myForm">
                     <div class="form-container">
                         <div class="form-control" id="chatTextarea"></div>
                         <input id="inputText" type="text" placeholder="대화를 해보세요" required>
                  
                         <button id="sendButton" class="btn">Enter</button>
                    </div>
                  </div>                  
               </div>
              </div>
           <!-- </div> -->
       </div>
    </section>
   
   <footer class="site-footer">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md-4">
            <h3>운영진 소개</h3>
            <p>
              <img src="./resources/myRoom/images/teamPic.jpg" alt="Image placeholder" class="img-fluid">
            </p>
            <p> 여름의 초입에서 이제 겨울을 맞습니다.<br/>우리가 최초는 아니지만 잘 만들고 싶었습니다. </p>
          </div>
          <div class="col-md-6 ml-auto">
            <div class="row">
              <div class="col-md-4">
                <div class="mb-5">
                  <h3>Quick Links</h3>
                  <ul class="list-unstyled">
                    <li><a href="#">Home</a></li>
                    <li><a href="#">My Room</a></li>
                    <li><a href="#">Search</a></li>
                    <li><a href="#">Info</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy; All rights reserved | This template is made in cooperation with <i class="fa fa-heart-o" aria-hidden="true"></i> <a href="#" onclick="return false;" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
          </div>
        </div>
      </div>
    </footer>
    <!-- END footer -->
    
    <!-- loader -->
    <div id="loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#f4b214"/></svg></div>
  
   <script src="./resources/planMain/js/jquery-3.2.1.min.js"></script>
    <script src="./resources/planMain/js/jquery-migrate-3.0.0.js"></script>  
    <script src="./resources/planMain/js/popper.min.js"></script>
    <script src="./resources/planMain/js/bootstrap.min.js"></script>
    <script src="./resources/planMain/js/owl.carousel.min.js"></script>
    <script src="./resources/planMain/js/jquery.waypoints.min.js"></script>
    
    
   
    <script src="./resources/planMain/js/jquery.stellar.min.js"></script> 

    
    <script src="./resources/planMain/js/main.js"></script>
    
</body>
</html>