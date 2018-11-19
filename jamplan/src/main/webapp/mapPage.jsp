<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="com.spring.jamplan.model.MapVO"%> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Jam Planner</title>
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">
</script>
	
<script async defer
  type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXzorrjSQ61PxTjiyMHOydxJOq0iEOcaI&callback=initMap&libraries=places">
</script>

<spring:url value="./resources/map/js/map.js" var="mapJs"/>
<spring:url value="./resources/map/js/semantic.min.js" var="semanticMinJs" />
<spring:url value="./resources/map/css/semantic.min.css" var="semanticMinCss" />
<spring:url value="./resources/map/css/map.css" var="mapCss" />

<script src="${mapJs}"></script>
<script src="${semanticMinJs}"></script>
<link href="${semanticMinCss}" rel="stylesheet" />
<link href="${mapCss}" rel="stylesheet" />


<%
   request.setCharacterEncoding("utf-8");
   String id = (String)session.getAttribute("id");  
   int planNo = (int)session.getAttribute("planNo");
   int role = (int)session.getAttribute("role");
%>


<style> 
.map-total{
   /* position: relative;  */
   width: 1015px;
   /* left: 70px;
   top: 50px;  */
   margin: 10px 0 0 0;
     
} 

.block {
    display: block;
    width: 100%;
    border: none;
    background-color: #f0f5f5;
    color: #244b75;
    padding: 14px 28px;
    font-size: 13px;
    font-weight: bold;
    cursor: pointer;
    text-align: center;
}

.block:hover{
    background-color: #b3cccc;
    color: #244b75;
}

#resetBtn, #confirmBtn {
    width: 105px;
    display: inline-block;
    margin: 10px 5px 0px 0px;
}
.ui.button {
margin-bottom:5px;
}

table,td,th{
   border-spacing: 0px 0px;
}

.colorBtn{
    border: none;
    color: #fff;
    padding: 20px 45.5px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 15px;
    margin: 40px 2px 0 -5px;
    cursor: pointer;
}

#colorBtns{
	margin-top: 40px;
    margin-left: 6px;
}

#pickPlaceList{
	width: 1000px;
	margin-top: 20px;
} 

.ui.dropdown{
	height: 30px;
    width: 100px;
    font-size: large;
    font-weight: 500;
}
</style>

</head>
<body>
<input type="hidden" id="memberId" value=<%=id%> /> 
<input type="hidden" id="planNo" value=<%=planNo%> /> 
<input type="hidden" id="role" value=<%=role%> /> 

<input type="hidden" id="color"/>
<input type="hidden" id="pickCount" value=""/>
<input type="hidden" id="lat" value=""/>
<input type="hidden" id="lng" value=""/>

<input type="hidden" id="testInput"/>

<div class="map-total" >
<br>
	<!-- <div id="2Buttons"> -->
         <form id="pickPlaceList">
         	<%
				if(role == 0 || role == 1){
			%>		
					<button id ="resetBtn"  class="btn btn-light pull-right" onclick="reset()" type="button">취소</button>
          	  		<button id ="confirmBtn"  class="btn btn-primary pull-right" type="button">저장하기</button>
           			
            <% 
				}
			%>
         </form>
	<!-- </div> -->
	<br><br>
	
	<table id="mapTable">	
   	<tr>
	   <td style="text-align: left;" colspan="2">
	  	 <select class="ui dropdown"></select>
	   </td>
    </tr>
	      
 
	<tr>
	   <td id='test'>
	      <div><input  id="searchInput"  class="controls"  type = "text"  placeholder = "위치 입력" /></div>
	      <div id="map"></div>
	   </td>
	   <td>
	         <div id="vertical_buttons">
		     	<%
		             for(int i=0;i<20;i++){
		     	%>      
		               <button class="block" onclick="change1(this)"></button>
		               
		        <%      
		             }
		     	%>
	         </div> 
	    </td>
	</tr>   
	</table>

<div id="colorBtns">
   <% String[] colorArray = {"#ff4d4d", "#ffa64d", "#ffff4d", "#79ff4d", "#4dffff", "#4d79ff", "#d24dff", "#ff4dd2","#ffcccc","#bfbfbf"};
      for(int i=0;i<10;i++){
   
   %>
      <button class="colorBtn" style="background-color:<%=colorArray[i] %>" value="<%=colorArray[i] %>">♥</button>
   <%
      }
   %>            
</div>
</div>

<div id="infoContent" style="display:none">
   <table >               
      <tr><th align=left id="InfoPlaceName"></th></tr>   
      <tr><td  align=left id="address" ></td></tr>
      <tr>
         <td align=left>selectMember</td>
         <!--  <td><input id ="pickBtn" class="btn btn-outline-light text-dark" type="button" value="Pick"></td> --> 
          <td><button class="btn btn-outline-light text-dark" type="button" id ="pickBtn">Pick</button></td> 
          <td><button class="btn btn-outline-light text-dark" type="button" id="cancelBtn">Cancel</button></td>
      </tr>
   </table>
   <table id="output">
   </table>
</div>


</body>
</html>