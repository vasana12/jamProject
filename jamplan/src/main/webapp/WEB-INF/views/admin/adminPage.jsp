<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"crossorigin="anonymous"></script>
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		
		
		<spring:url value="/resources/admin/js/adminJs.js" var="adminJs" />
		<spring:url value="/resources/admin/css/adminCss.css" var="adminCss" />
	
		<script src="${adminJs }"></script>
		<link href="${adminCss}" rel="stylesheet" />
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/css/drawer.min.css">
		<!-- jquery & iScroll -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/iScroll/5.2.0/iscroll.min.js"></script>
		<!-- drawer.js -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/drawer/3.2.2/js/drawer.min.js"></script>
		
		<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
		
		<style>
		/*구글 한글폰트 불러오는 곳*/
		@import url(//fonts.googleapis.com/earlyaccess/jejumyeongjo.css);
    
		* {
			font-family: 'Jeju Myeongjo', serif;
			font-weight: 600;
		}
		/*구글 한글폰트 불러오는 부분 끝*/
		</style>
		<script>
			
		</script>
		
		<title>Jam Planner</title>
	</head>
	<body>
		<header>
	    	<!-- top bar 들어가는 부분 -->
			<div class="topnav">
			  <!-- Centered link -->
			  <div class="topnav-centered">
			    <h2><strong><a href="#home" class="active">Jam Planner</a></strong></h2>
			  </div>
			  <!-- Right-aligned links -->
			  <div class="topnav-right">
			    <a href="#search">Message</a>
			    <a href="#myInfo">My Info</a>
			    <a href="plan.search">Search</a>
			  </div>
			</div>
		</header>
		<section class="container-fluid">
			<div id="main-container" class="row content">
				<div class="col-sm-2 sidenav">
					
					<ul class="nav nav-pills flex-column">
				        <li class="nav-item active"><a class="nav-link" href="#section1">Main</a></li>
				        <li class="nav-item"><a class="nav-link" href="#section2">My Room</a></li>
				        <li class="nav-item"><a class="nav-link" href="#section3">Search</a></li>
				        <li class="nav-item"><a class="nav-link" href="#section3">Photos</a></li>
				    </ul><br>
				</div>
				<div id="hero" class="col-sm-10">
				  <div id="searchContainer" class="container">
				    <div class="searchwrapper">
				      <div class="searchbox">
				       
				        	<form id="selectionForm" class="row">
					          <div class="col-md-3">
					            <select id="adminSelection" class="form-control category">
					              <option id="team" class="option" value="team">팀명</option>
					              <option id="plan" class="option" value="plan">일정명</option>
					              <option id="user" class="option" value="user">ID</option>
					            </select>
					          </div>
					          <div class="col-md-5"><input type="text" id="searchItem" name="searchItem" class="form-control" placeholder="찾을 대상을 입력하세요."></div>
					          <div class="col-md-1"><input type="submit" id="searchButton" class="btn btn-primary" class="form-control" value="Search"></div>
				        	</form>
				      </div>
				    </div>
				    <div id="adminItemPrint"class="container">
				  	</div>
				  </div>
				  
				</div>
			</div>
		</section>
		
	</body>
</html>