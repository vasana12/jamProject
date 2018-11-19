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
	/* response.setHeader("Cache-Control","no-store");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);   
	if (request.getProtocol().equals("HTTP/1.1")) 
	        response.setHeader("Cache-Control", "no-cache"); */
	
	if ((String)session.getAttribute("id") == null)
		response.sendRedirect("/jamplan/home.do");
	
	String id = (String) session.getAttribute("id");
	UserVO user = (UserVO) session.getAttribute("userVO");
	ArrayList<TeamInfoVO> teamList = (ArrayList<TeamInfoVO>)session.getAttribute("teamList");
	
	if(session.getAttribute("planNo")!=null){
		System.out.print("===========================jsp 세션 초기화=======================");
		session.removeAttribute("planNo");
	}
	
	ArrayList<String> cookieList = (ArrayList<String>)session.getAttribute("cookieList");

%>
    
<!DOCTYPE html>
<html lang="ko">
<head>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.3.1.js"
		  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			  crossorigin="anonymous"></script>
	
	<meta charset="UTF-8">
	<meta name="description"
		content="A planner that helps you make more amused plan and share your own memory">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
		integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
		crossorigin="anonymous">
		
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300, 400,700" rel="stylesheet">
	    <link rel="stylesheet" href="./resources/myRoom/css/bootstrap.css">
	    <link rel="stylesheet" href="./resources/myRoom/css/animate.css">
	    <link rel="stylesheet" href="./resources/myRoom/css/owl.carousel.min.css">
	    <!-- Theme Style -->
	    <link rel="stylesheet" href="./resources/myRoom/css/style.css">
	    
	    <spring:url value="./resources/myRoom/js/myRoom.js" var="roomJs" />
		<spring:url value="./resources/myRoom/css/myRoom.css" var="roomCss" />
		<script src="${roomJs}"></script>
		<link href="${roomCss}" rel="stylesheet" />
		<title>Jam Planner</title>
		

		
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
            	<a href="#" id="logoutButton" style="float:right;"><span class="pb_rounded-4 px-2">Logout</span></a>
            	<a href="#" id="searchButton" style="float:right;"><span class="pb_rounded-4 px-2">Search</span></a>
            	<a href="#" id="infoButton" onclick="return false;" style="float:right;"><span class="pb_rounded-4 px-2">Info</span></a>
           		<span id="messageBut" class="pb_rounded-4 px-2" data-toggle="modal" data-target="#messageModal" >
           			<a href="#" style="float:right;"><span id="countLabel" class="label label-primary"></span></a>
           			<a href="#" id="messageBut" class="pb_rounded-4 px-2" style="float:right;">
           				Message 
           			</a>
           		</span>
            	<!-- </a> -->      
            </div>
          </div>
        </div>
      </div>

      <div class="container logo-wrap">
        <div class="row pt-5">
          <div class="col-12 text-center" style="margin-bottom: 10px;">
            <a class="absolute-toggle d-block d-md-none" data-toggle="collapse" href="#navbarMenu" role="button" aria-expanded="false" aria-controls="navbarMenu">
            	<span class="burger-lines"></span>
            </a>
            <h1 class="site-logo"><a href="home.do">Jam Planner</a></h1>
          </div>
        </div>
      </div>
      
      <nav class="navbar navbar-expand-md  navbar-light bg-light">
        <div class="container">
        </div>
      </nav>
    </header>
    <!-- END header -->

    <!-- <section class="site-section pt-5">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
              <div>
                <a href="blog-single.html" class="a-block d-flex align-items-center height-lg" style="background-image: url('./resources/myRoom/images/friend.jpg'); ">
                  <div class="text half-to-full">
                    <h3>설레는 여행은 <br/>계획에서 시작합니다.</h3>
                    <p> 사람들과 여행 계획을 만들고 공유하세요. </p>
                  </div>
                </a>
              </div>
          </div>
        </div>
        
      </div>
    </section> -->
    <!-- END section -->
    

    <section class="site-section py-sm">
      <div class="container" style="margin-top: 50px;">
        <div class="row">
          <div class="col-md-6">
            <h2 class="mb-4">Update</h2>
          </div>
        </div>
        <div class="row blog-entries">
          <div class="col-md-12 col-lg-8 main-content">
            <div id="planPost" class="row" style="height: 900px;">
            
            <!-- >>>>>>>>>>>>>>>>> one post <<<<<<<<<<<<<<<<<<<<< -->
            	<c:forEach items="${teamList }" var="team">
					<div class="col-md-6">
						<a href="blog-single.html" class="blog-entry element-animate" data-animate-effect="fadeIn">
						  <img src="<spring:url value='/image/${team.teamImage }'/>" width="350" height="232" alt="Image placeholder">
						  <div class="blog-content-body">
						    <div class="post-meta">
						      <span class="category">${team.teamName }</span>
						    </div>
						    <h2>일정을 확인해보세요.</h2>
						  </div>
						</a>
					</div>
              	</c:forEach>
              <!-- >>>>>>>>>>>>>>>>> one post <<<<<<<<<<<<<<<<<<<<< -->
             
            </div>

            <div class="row">
              <div class="col-md-12 text-center">
                <nav aria-label="Page navigation" class="text-center">
                  <ul class="pagination">
                    <li class="page-item  active"><a class="page-link" href="#">Prev</a></li>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                  </ul>
                </nav>
              </div>
            </div>
          </div>

          <!-- END main-content -->
          <div class="col-md-12 col-lg-4 sidebar">
            
            <!-- END sidebar-box -->
            <div class="sidebar-box">
              <div class="bio text-center">
              	<img id="imagePreview" src="./resources/myInfo/images/yosemite.jpg" style="width:100px; height:100px;">
               <%-- <% if(user.getImage() == null) {%> --%>
              		<%-- <img src="<spring:url value='/image/${user.image }'/>" style="width:200px; height:200px;" alt="Image Placeholder" class="img-fluid"> --%>
               <%-- <%}else { %>
                	<img src="<spring:url value='/image/${userVO.image }'/>" width="200" height="200" alt="Image Placeholder" class="img-fluid">
                <%} %>  --%>
                <div class="bio-body">
                  <h2  id="userId">${userVO.id }</h2>
                  <p>Team List</p>
                  <div id="teamList">
                  
                  </div>
                  <p><a href="#" class="btn btn-primary btn-sm" data-toggle="modal"
							data-target="#fullHeightModal">+Team</a>
					 <div class="modal fade myModal" id="fullHeightModal" tabindex="-1"
							role="document" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-full-height" role="dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title w-100" id="myModalLabel">
											Add Team</strong></h4>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<form id="makeTeamForm" name="makeTeamForm">
											<input type="hidden" name="id" id="id" value="<%=id%>">
											<div class="alert alert-info">
												<label for="teamName" >팀명 : </label>
												<input type="text" name="teamName" id="makeTeamName" >
												<button id="validationCheck" type="button"
													class="btn btn-xs btn-secondary">중복 확인</button>
												
												<div class="row">
													<label for="teamImage">팀 이미지 : </label>
													<input type="file" id="teamImage" name="teamImage" class="btn"/><br/>
												</div>
											</div>
											
											<div class="row modal-footer justify-content-center">
												<button type="button" id="inputForm" class="btn btn-primary">추가</button>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
                 	 <a href="#" id="addPlanModalBtn" class="btn btn-primary btn-sm" data-toggle="modal" 
                 	 	data-target="#addPlan">+ Plan</a>
						
						<div id="addPlan" class="modal">
				         <div class="modal-dialog">
				         	<div id="modal-content" class="modal-content">
				         		<div>
					            	<button id=closeJoin class="close mb-4 mt-0" type="button" data-dismiss="modal">&times;</button>
					            </div>
					            <h2 id="planModalSubject" class="mb-4 mt-0 text-center">Add plan</h2>
					 		 	<div id="teamInPlanModal" class="modal-body"></div>
					                <div id="addPlanModal" class="col-md-12 ">
					            		<form id="addPlanForm" class="rounded pb_form_v1">	<!-- bg-white -->
					              			<div class="form-group">
						                    	<input id="teamNameByTable" type="hidden" name="teamName" value="">
						                	</div>
							                <div class="form-group">
						                    	<input type="text" class="form-control pb_height-50 reverse" id="planName" name="planName" placeholder="planName">
						                	</div>
					                		<div class="form-group">
						                      <button type="submit" onclick="return false;" id="addPlanSubmit" class="btn btn-primary btn-lg btn-block pb_btn-pill btn-shadow-blue">일정 만들기</button>
						                	</div>                             
					            		</form>
					             	</div>
					        	
					        </div>
				     	</div>
				     </div>
							
                </div>
              </div>
            </div>
            <!-- END sidebar-box -->  
            <div class="sidebar-box">
            
              <h3 class="heading">최근 방문</h3>
              	<ul class="categories">
              		<c:forEach items='${cookieList}' var="item">
		                <li><h4><a href="#" onclick="return false;">${item} </a></h4></li>
              		</c:forEach>
              	</ul>
            </div>
            <!-- END sidebar-box -->
            
          </div>
          <!-- END sidebar -->
        </div>
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
                    <!-- <li><a href="#">Home</a></li> -->
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

	<!-- messageModal -->
	<div id="messageModal" class="modal"  style="margin-top:120px;">
	    <div class="modal-dialog">
			<div class="modal-content">
         <div class="modal-body">  
                 <!-- bg-white와 rounded는 bootstrap.css에 들어있으므로 이미 적용되고있음. -->
                    <table class="table table-hover">
                       <thead>
                          <th>팀</th>
                          <th>ID</th>
                          <th style="text-align: center;">수락여부</th>
                       </thead>
                    <tbody id="messageContent" class="table table-hover">
                    </tbody>   
                 </table>
            </div>
      </div>
		</div>
	</div>

	<!-- <script src="./resources/myRoom/js/jquery-3.2.1.min.js"></script> -->
    <script src="./resources/myRoom/js/jquery-migrate-3.0.0.js"></script>
    <script src="./resources/myRoom/js/popper.min.js"></script>
	<script src="./resources/myRoom/js/bootstrap.min.js"></script>
    <script src="./resources/myRoom/js/owl.carousel.min.js"></script>
    <script src="./resources/myRoom/js/jquery.waypoints.min.js"></script>
    <script src="./resources/myRoom/js/jquery.stellar.min.js"></script>
    <script src="./resources/myRoom/js/main.js"></script>
  </body>
</html>