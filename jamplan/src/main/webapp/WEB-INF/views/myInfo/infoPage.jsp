<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.spring.jamplan.model.TeamInfoVO" %>
<%@ page import="com.spring.jamplan.model.UserVO" %>

<%  
    
	if ((String)session.getAttribute("id") == null) {
		response.sendRedirect("/jamplan/home.do");
	}
	
	String id = (String) session.getAttribute("id");
	UserVO user = (UserVO) session.getAttribute("user");
	ArrayList<TeamInfoVO> teamListAs = (ArrayList<TeamInfoVO>)session.getAttribute("teamListAs");
	

 %>
<!DOCTYPE html>
<html>
	<head>
		
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		
		<script src="https://code.jquery.com/jquery-3.3.1.js"
		  	integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
			crossorigin="anonymous"></script>
		  
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
	
		<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300, 400,700" rel="stylesheet">
	    <link rel="stylesheet" href="./resources/myInfo/css/bootstrap.css">
	    <link rel="stylesheet" href="./resources/myInfo/css/animate.css">
	    <link rel="stylesheet" href="./resources/myInfo/css/owl.carousel.min.css">
	    <!-- Theme Style -->
	    <link rel="stylesheet" href="./resources/myInfo/css/style.css">
	    
		<spring:url value="./resources/myInfo/js/myInfoJs.js" var="myInfoJs" />
		<spring:url value="./resources/myInfo/css/myInfoCss.css" var="myInfoCss" />
	
		<script src="${myInfoJs }"></script>
		<link href="${myInfoCss}" rel="stylesheet" />
		
		<link rel="stylesheet" href="./resources/myInfo/fonts/ionicons/css/ionicons.min.css">
	    <link rel="stylesheet" href="./resources/myInfo/fonts/fontawesome/css/font-awesome.min.css">
	    <link rel="stylesheet" href="./resources/myInfo/fonts/flaticon/font/flaticon.css">
		
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/solid.css" integrity="sha384-VGP9aw4WtGH/uPAOseYxZ+Vz/vaTb1ehm1bwx92Fm8dTrE+3boLfF1SpAtB1z7HW" crossorigin="anonymous">
		
		<title>Jam Planner</title>
		
		<script>
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
					    	<a href="#" style="float:right;"><span class="pb_rounded-4 px-2">Search</span></a>
					    	<%-- <a href="#" id="infoButton" style="float:right;" value="<%=id%>"><span class="pb_rounded-4 px-2">Info</span></a> --%>
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
		      <h1>Info</h1> 
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
					</ul>           
				</div>
			</div>
		</nav>
    </header>
    	
		<section class="site-section py-lg">
			
    		<div class="container">	
		        <div class="row blog-entries">
		        	<div class="col-md-12 col-lg-8 main-content">
		        						
						<div class="row mb-5 mt-5">
					        <div class="col-md-12">
					        
	<!-- ==================== tab 영역 설정 버전 11111111 ======================= -->
					        <!-- <ul class="nav nav-tabs" id="myTab" role="tablist">
								<li class="nav-item">
									<a class="nav-link active" id="teamManage-tab" data-toggle="tab" href="#teamManage" role="tab" aria-controls="profile" aria-selected="false">
										팀 관리</a></li>	
								<li class="nav-item">
		                        	<a class="nav-link" id="info-tab" data-toggle="tab" href="#info" role="tab" aria-controls="home" aria-selected="true">
		                                          내 정보</a></li>
							</ul> -->
		<!-- ==================== tab 영역 설정 버전 22222222 ======================= -->
							<div id="planManage" class="col-md-9">
				               <ul class="nav nav-tabs">
				                  <li><a href="#teamManage" data-toggle="tab" class="nav-link active moveLink" id="teamManageTab">팀 관리</a></li>
				                  <li><a href="#personalInfo" data-toggle="tab" class="nav-link moveLink" id="personalInfoTab">내 정보</a></li>
				                  <!-- <li><a href="#viewAllContent" data-toggle="tab" class="nav-link moveLink" id="viewAllTab">View all</a></li> -->
				               </ul>
				               <div class="tab-content divContent">
				                   <div class="tab-pane container active" id="teamManage">
				                   <div style="height: 1000px;">
				                   	
				                   	
				                   	<c:forEach var="teamList" items="${teamListAs }" >
								        <div class="post-entry-horzontal">
									        <a style="border: 1px solid #e6e6e6;">
									        	<div class="image element-animate fadeIn element-animated" data-animate-effect="fadeIn">
									        		<img src="<spring:url value='/image/${teamList.teamImage }'/>" width="100%" height="200px">
									        	</div>			        	
										        <span class="row text teamPost" style="padding: 30px;">
										        <c:choose>
									                <c:when test="${teamList.role eq 0}">
										        	<div class="post-meta">
										                <span class="category">${teamList.teamName } 팀</span>
										                <h2 style="margin-bottom: 20px;">
									            			안녕하세요. ${teamList.teamName }의 리더 <%=id %>님 !
								            			</h2>
								            			<span style="float:right;"><button id="removeTeamAsLeader" class="btn btn-danger btn-sm">팀 삭제</button></span>
										                <span style="float: right; margin-right: 5px;"><button class="btn btn-primary btn-sm" data-toggle="modal"
															data-target="#imageUpload${teamList.teamNo }">사진 등록</button></span>
										            </div>	
										            
								            		</c:when>
											        <c:otherwise>
														<div class="post-meta">
										                	<span class="category">${teamList.teamName } 팀</span>
										                	<h2>
										            			안녕하세요. ${teamList.teamName }의 멤버 <%=id %>님 !
									            			</h2>
										                	<span style="float:right;"><button id="removeTeamAsMember" class="btn btn-danger btn-sm">팀 탈퇴</button></span>
										            	</div>	
													</c:otherwise>
												</c:choose>	
										        </span>
									        </a>						        
								        </div>
						        </c:forEach>
						        
						        <c:forEach var="teamList" items="${teamListAs }" >
						        <div class="modal fade myModal" id="imageUpload${teamList.teamNo }" tabindex="-1" role="document" aria-labelledby="imageUploadLabel${teamList.teamNo }" aria-hidden="true">
									<div class="modal-dialog modal-full-height" role="dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title w-100 text-center" id="imageUploadLabel${teamList.teamNo }">
													Upload Image</strong></h4>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form id="uploadImageForm${teamList.teamNo}" name="uploadImageForm">
													<div class="alert alert-info">
														<label for="teamImage">팀 이미지 : </label>
														<input type="file" class="btn" id="teamImage${teamList.teamNo }" data-no=${teamList.teamNo } name="teamImage" /><br/>
														<input type="hidden" class="teamName" value="${teamList.teamName }">
													</div>
													
													<div class="row modal-footer justify-content-center">
														<button type="button" class="btn btn-primary uploadImageButton">등록</button>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
						    </c:forEach>
				            </div>		<!-- 팀정보 --> 
				                   	
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
				                  <div class="tab-pane container fade" id="personalInfo">
				                 
                      <!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< -->
		                      		<div class="post-entry-horzontal">
									    <a>
		                                    <div class="image element-animate fadeIn element-animated" data-animate-effect="fadeIn">
									        	<%-- <img id="imagePreview" src="<spring:url value='/image/${user.image }'/>" width="100%" height="200px"> --%>
									        	<img id="imagePreview" src="./resources/myInfo/images/yosemite.jpg" style="width:200px; height:200px;">
									        </div>
									        <span class="row text teamPost" style="padding-left: 20px;">
									        	<div class="post-meta">
	                                    			<label for="imageSearch">사진 찾기</label>  
	                                    			<%-- <input type="file" class="btn" id="teamImage${teamList.teamNo }" data-no=${teamList.teamNo } name="teamImage" /><br/>  --%>                           
	                                    			<!-- <input id="image" type="file" name="file" accept="image/*" /> -->
	                                    			<input id="image" type="file" name="file" data-no="1" style="margin-bottom: 10px;"/>
	                                    			<button id="imageUpload"  class="btn btn-primary btn-rounded" type="button">프로필 사진 등록</button>
									            </div>	
										    </span>
										</a>
									</div>
       <!-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TEST <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< -->
                                    
                               		<div id="personalInfo" class="col-lg-8">
		                                <form autocomplete="off" id="infoForm" name="updateInfo">
		                                    <div style="width: 80%;margin-right:10%;margin-bottom: 5%;">
		                                        <p>아이디</p>
		                                        <input name="id" value="${id }" readonly autofocus>
		                                    </div>
		                                    <div style="width: 80%;margin-right:10%;margin-bottom: 5%;">
		                                        <p>이메일</p>
		                                        <input type="text" id="email" name="email" readOnly value="${user.email }">
		                                    </div>
		                                    <div style="width:30%;margin-right:17%;margin-bottom: 5%;">
		                                        <p>비밀번호 확인</p>
		                                        <input id="password" name="pass" type="password">
		                                    </div>                      
		                                    <div style="width:37%;margin-right:6%;margin-bottom: 5%;">
		                                        <p>비밀번호</p>
		                                        <input id="prePassword" type="password">
		                                    </div>                                    
		                                    <div style="width:37%;margin-right:10%;margin-bottom: 5%;">
		                                        <p>가입일</p>
		                                        <input type="text" readonly name="signDate" readOnly value="${user.signDate.split(' ')[0] }">
		                                    </div>
		                                    </br>
		                                    <div style="width:37%;margin-right:25%">
		                                    	<button class="btn success" type="submit">정보 수정</button>
		                                    </div>
		                                </form>
				                  	</div>
				                  
				                  </div>
				               </div>
				            </div>
							</div>
						</div>
		        	</div>
		          <!-- END main-content -->
		
		
		          	<div class="col-md-12 col-lg-4 sidebar">       
			          	<div class="sidebar-box">
			            	<div class="bio text-center">
				                <img src="./resources/myInfo/images/yosemite.jpg" alt="Image Placeholder" class="img-fluid">
				                <div class="bio-body">
				                  <h2><%=id %></h2>
				                  <p>
				                  	리더 &nbsp; ${leaderNum } <br/>
				                  	일반 &nbsp; ${memberNum }
				                  </p>
				                </div>
			            	</div>
			            </div>
		          
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
		            <img src="./resources/myInfo/images/teamPic.jpg" alt="Image placeholder" class="img-fluid">
		          </p>
		
		          <p> 여름의 초입에서 이제 겨울의 문턱까지 왔습니다. 우리가 최초는 아니겠지만 잘 만들고 싶었습니다. </p>
		        </div>
		        <div class="col-md-6 ml-auto">
		          <div class="row">
		            <div class="col-md-4">
		
		              <div class="mb-5">
		                <h3>Quick Links</h3>
		                <ul class="list-unstyled">
		                  <li><a href="#">About Us</a></li>
		                  <li><a href="#">Travel</a></li>
		                  <li><a href="#">Adventure</a></li>
		                  <li><a href="#">Courses</a></li>
		                  <li><a href="#">Categories</a></li>
		                </ul>
		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
		      <div class="row">
		        <div class="col-md-12">
		          <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
		Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made in cooperation with <a href="#" onclick="return false;" target="_blank">Colorlib</a>
		<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
		        </div>
		    </div>
		</div>
    </footer>
    <!-- END footer -->
	
	<!-- loader -->
    <div id="loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#f4b214"/></svg></div>
  
	<script src="./resources/myInfo/js/jquery-3.2.1.min.js"></script>
    <script src="./resources/myInfo/js/jquery-migrate-3.0.0.js"></script>
    <script src="./resources/myInfo/js/popper.min.js"></script>
    <script src="./resources/myInfo/js/bootstrap.min.js"></script>
    <script src="./resources/myInfo/js/owl.carousel.min.js"></script>
    <script src="./resources/myInfo/js/jquery.waypoints.min.js"></script>
    <script src="./resources/myInfo/js/jquery.stellar.min.js"></script>

    <script src="./resources/myInfo/js/main.js"></script>
	
	</body>
</html>
