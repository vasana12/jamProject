<%@page import="com.spring.jamplan.model.CalendarVO"%>
<%-- <%@page import="org.springframework.boot.autoconfigure.web.ServerProperties.Session"%> --%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%-- <%@ page import ="com.spring.jamplan.model.PlanVO" %> --%>
<!DOCTYPE html>

<%@ page session="false"%>
<html>
<head>
<title>JAM Plan</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>		  
<spring:url value="./resources/calendar/js/calendarJs.js" var="calJs" />
<spring:url value="./resources/calendar/css/calendarCss.css" var="calCss" />

<script src="${calJs}"></script>
<link href="${calCss}" rel="stylesheet" />


<%
	/*session 객체 접근 불가 시 리퀘스트에서 session 객체 생성  */
	HttpSession session = request.getSession();
	String id = (String)session.getAttribute("id");

	int role = (int)session.getAttribute("role");
%>	



</head>

<body>
<br>
<div id ="divBox">
	<!-- <h1 id = "calendarHead">달력</h1> -->
	<br><br>
	<div id = "selectTableDiv">
		<table id ="selectTable">
			<tr>
				<td style="width:100px;">
					<div id="nowYear"></div>
				</td>
				<td>
					<div id="nowMonth"></div>
				</td>
				<td>
					<div id="calendarDiv"></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="3"  align="right" >
					<div>
					<%
						if(role==0 || role ==1){
					%>	
							<input class="btn btn-primary pull-right" type = "button" id = "save" style="width:105px"value="저장하기" />
					<% 
						}
					%>
					</div>
				
				</td>
			</tr>
		</table>
	</div>

	<br>
	
	<div id="calendar"></div>
</div>

</body>
</html>