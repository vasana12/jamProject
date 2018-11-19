<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Jam Planner</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"
     integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
        crossorigin="anonymous">
</script>

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
<link rel="stylesheet" href="./resources/search/css/bootstrap.css">
<link rel="stylesheet" href="./resources/search/css/animate.css">
<link rel="stylesheet" href="./resources/search/css/owl.carousel.min.css">
<!-- Theme Style -->
<link rel="stylesheet" href="./resources/search/css/style.css">

<spring:url value="./resources/search/js/search.js" var="searchJs" />
<spring:url value="./resources/search/css/search.css" var="searchCss" />

<link href="${searchCss}" rel="stylesheet" />
<script src="${searchJs}"></script>
</head>

<style>
#cmt{
	position: absolute;
    bottom: 0;
    width: 466px;
}

#commentBtn{
	display:inline-flex;
	width:auto;
	float:right;
	margin-left: 15px;
}

#text_id{
display: inline-flex;
float: left;
width: 100%; 
height:38px
}


#modalOutput tr td{
 word-break: break-all
}

.modal-body{
    padding-bottom: 5px;
    padding-right: 16px;
    padding-top: 0px;
}

#modalTB{
    height: 450px;
    overflow-y: auto;
    position: absolute;
    margin-right: 10px;
}

#commentTable{
	height: 400px;
	width: 465px;
	overflow-y: auto;
	overflow-x: hidden;
	position: absolute;
	padding: 10px;
	border-bottom: 1px solid #dee2e6;
    border-top: 1px solid #dee2e6;
}
</style>

<body>

<!-- header -->
<header role="banner">
 	<div class="top-bar">
    <div class="container">
    	<div class="row">
        <div class="col-4 social" >
           <a href="/jamplan/home.do" id="homeButton" style="float:left;"><h2>Jam Planner</h2></a>
        </div>
        <div class="col-8 social">
        	<c:choose> 
            	<c:when test="${id != null }">
                	<a href="#" id="logoutButton" onclick="return false;" style="float:right;"><span class="pb_rounded-4 px-2">Log Out</span></a>
                   	<a href="/jamplan/myInfoPage.info" id="infoButton" style="float:right;"><span class="pb_rounded-4 px-2">Info</span></a>
                 	<a href="/jamplan/myroom.do" id="myRoomButton" style="float:right;"><span class="pb_rounded-4 px-2">My Room</span></a>
                   	<!-- <a href="/jamplan/home.do" id="homeButton" style="float:right;"><span class="pb_rounded-4 px-2">Home</span></a> -->
                </c:when>
                <c:otherwise>
                   	<a href="#" id="infoButton" onclick="return false;" style="float:right;"><span class="pb_rounded-4 px-2">Log In</span></a>
                   	<a href="/jamplan/home.do" id="homeButton" style="float:right;"><span class="pb_rounded-4 px-2">Home</span></a>
            	</c:otherwise>
        	</c:choose>
        </div>
    	</div>
    </div>
	</div>
  
	<nav class="navbar navbar-expand-md  navbar-light bg-light">
  		<div class="container"></div>
	</nav>
</header>
<!-- END header -->

<!-- section -->
<section class="site-section pt-5">
	<div class="container-fluid">
    <div class="row">
    	<div class="col-md-12">

        <div class="owl-carousel owl-theme home-slider">
        <!-- ============== 하나의 캐러셀 =============== -->
	        <div>
          		<a href="blog-single.html" class="a-block d-flex align-items-center height-lg" style="background-image: url('./resources/search/images/NewYork.jpg'); ">
            		<div class="text half-to-full">
              			<h3>New York</h3>
              			<p></p>
            		</div>
          		</a>
	        </div>
	        <div>
		        <a href="blog-single.html" class="a-block d-flex align-items-center height-lg" style="background-image: url('./resources/search/images/LondonStreet.jpg'); ">
		         	<div class="text half-to-full">
		           		<h3>London</h3>
		           		<p></p>
		         	</div>
		        </a>
	        </div>
	        <div>
		       	<a href="blog-single.html" class="a-block d-flex align-items-center height-lg" style="background-image: url('./resources/search/images/JapanStreet.jpg'); ">
		        	<div class="text half-to-full">
			          	<h3>Tokyo</h3>
			          	<p></p>
		        	</div>
		       	</a>
	        </div>
        </div>
        <!-- ============== 하나의 캐러셀 =============== -->
    	</div>
    </div>
	</div>
</section>
<!-- END section -->


<!-- 검색  -->
<div class="container-fluid justify-content-center">
<div class="row" style="margin-top: 30px;">
	<div class="col-md-4 moveSearch">
    <div class="input-group"> 
    	<input type="text" class="form-control" placeholder="원하시는 일정을 검색해보세요!" />
    	<button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">선택</button>
    	
    	<div class="dropdown-menu">
	        <a class="dropdown-item li1" href="#">제목</a>
	        <a class="dropdown-item li1" href="#">날짜</a>
    	</div>
    	
    	<div class="input-group-append">
	       	<button class="btn btn-dark tag">
	        	<i class="fas fa-search"></i>검색
	       	</button>
    	</div>
    </div>
	</div>
</div>
</div>
<!-- END 검색  -->

<br>
<br>
   
<!-- 순서 나열 -->   
<div class="col-sm-5" style="margin-left: 23px;">
	<div class="btn-group">
	    <button type="button" class="btn btn-light btn-lg clk" id="newDateClick">최신순</button>
	    <button type="button" class="btn btn-light btn-lg clk" id="readCountClick">조회순</button>
	    <button type="button" class="btn btn-light btn-lg clk" id="goodCountClick">추천순</button>
	    <button type="button" class="btn btn-light btn-lg clk" id="dateClick" style="width:102px;">제목</button>
	    <input type="hidden" name="selectDate" value="${listDTO.selectDate}"/>
        <input type="hidden" name="readCount" value="${listDTO.readCount}"/>
        <input type="hidden" name="goodCount" value="${listDTO.goodCount}"/>
        <input type="hidden" name="planName" value="${listDTO.planName}"/>
	</div>
</div>

<br><br>

<div class="container-fluid text-center conPadding" style="margin:50px 0;">
	<div id="output" class="row">
	 	<!-- >>>>>>>>>>>>>>>>> one post <<<<<<<<<<<<<<<<<<<<< -->
		<!-- >>>>>>>>>>>>>>>>> one post <<<<<<<<<<<<<<<<<<<<< -->
	</div>
</div>
<!-- END 순서 나열 -->
   
<!-- 모달창 -->
<div id="myModal" class="modal">
	<!--모달의 크기 결정-->
    <!--modal-sm, modal-lg-->
	<div class="modal-dialog">
	
	    <!-- 모달의 본 컨텐츠-->
	    <div class="modal-content" style="height:620px;">
	    
			<div class='modal-header' style="border-bottom:unset;" > 
				<div id="likeput"></div>
                <input type="text" id="likeStatus" hidden="hidden">
                <input type="hidden" id="likeId" value="${id}"/>
                
				<button class="close" type="button" data-dismiss="modal">&times;</button>
			</div>
			
			<div class="modal-body" style="padding-bottom: 5px;">
				<!-- planList 보여주는 부분 --> 
            	<!-- 모달창 댓글 달기 -->
	            <input type='hidden' id='userId' value='${id}' />
	            <div id='cmt' >
	            	<table style=" width: 100%;"></table>
     			</div>
            </div> 
                	
            <!--modal-footer 에는 row를 쓸 필요가 없다 -->                                  
            <div class="modal-footer" style="border-top:unset; padding-top:0px;">
            	<button id="default" class="sendPlanNo btn btn-primary btn-block" style="height:40px">동행 신청</button>
            </div>
                 
       </div>
	</div>
</div>   
<!-- END 모달창 -->     
  
<!-- 페이징처리 -->
<section id="pageList"></section>
      
<!-- Footer -->
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
<p>JAM Theme Made By <a href="https://www.jamplan.com" data-toggle="tooltip" title="Visit w3schools">www.jamplan.com</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
      </div>
    </div>
  </div>
</footer>
<!-- END footer -->
   
<!-- loader -->
<div id="loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#f4b214"/></svg></div>

<script src="./resources/search/js/jquery-migrate-3.0.0.js"></script>
<script src="./resources/search/js/popper.min.js"></script>
<!-- <script src="./resources/search/js/bootstrap.min.js"></script> -->
<script src="./resources/search/js/owl.carousel.min.js"></script>
<script src="./resources/search/js/jquery.waypoints.min.js"></script>
<script src="./resources/search/js/jquery.stellar.min.js"></script>
<script src="./resources/search/js/main.js"></script>

</body>
</html>