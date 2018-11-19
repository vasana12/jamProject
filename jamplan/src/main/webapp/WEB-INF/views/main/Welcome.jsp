<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<% 	
	response.setHeader("Cache-Control","no-store");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);   
	if (request.getProtocol().equals("HTTP/1.1")) 
		response.setHeader("Cache-Control", "no-cache");

	if((String)session.getAttribute("id") == null) {
		response.sendRedirect("/jamplan/home.do");
	}else {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jamplan/home.do");
		dispatcher.forward(request, response);
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>