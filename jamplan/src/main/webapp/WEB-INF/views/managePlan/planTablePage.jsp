<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Jam Planner</title>

<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous">
</script> 

<spring:url value="./resources/planTable/js/planTable.js" var="planTableJs" />
<spring:url value="./resources/planTable/css/planTable.css" var="planTableCss" />
<!-- <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script> -->
<!-- <script type = "text / javascript"src = "// ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"> -->
<%-- tableDND--%>
<spring:url value="./resources/planTable/js/jquery.tablednd.js" var="tabledndJS" />
 	
<script src="${planTableJs}"></script>
<link href="${planTableCss}" rel="stylesheet" /> 
<script src="${tabledndJS}"></script>

<style>
table {
    width: 100%;
    margin: 10px 0 5px 0;
}
th , td{
text-align: center;
}

#saveput{
	width: 980px;
	margin: 40px 0 0 0; 
	height: 15px;
}

#planput {
    width: 1000px;
    margin: 10px 0 0 -15px;
}

#saveBtn{
	width: 105px;
}
#paging a{
color:black;
}

</style>



</head>
<body>
	<br>123123
	<div id="saveput"><button class="btn btn-primary pull-right" id="saveBtn">저장하기</button></div>
	   
	<br>
	
	<div class="container" id="planput">
		<table class="table" style="width: 1000px;height: 800px;">
			<thead>
				<th id="date" style="width: 100px;">날짜 </th>
				<th id="place" style="width: 290px;">장소</th>
				<th id="plan" style="width: 610px; hight: 130px">일정</th>
			</thead>
			<tbody id='ptbody'>
				<tr>
				<td>yy/mm/dd</td>
				<td>선택된 장소</td>
				<td><textarea class="form-control" placeholder="여행계획을 작성해보세요!" rows="7" cols="30" ></textarea></td>
				<td style="visibility:hidden;"><input type="text" hidden="hidden"></td>
				</tr>
			</tbody>
		</table>
		<div id="paging" style="text-align: center; margin-top: 20px"></div>
	</div>
	
	<input type="hidden" id="currentPage" value="1"/>
	
	
<!-- 	
<table id="table-1" cellspacing="0" cellpadding="2">
    <tr id="1"><td>1</td><td>One</td><td>some text</td></tr>
    <tr id="2"><td>2</td><td>Two</td><td>some text</td></tr>
    <tr id="3"><td>3</td><td>Three</td><td>some text</td></tr>
    <tr id="4"><td>4</td><td>Four</td><td>some text</td></tr>
    <tr id="5"><td>5</td><td>Five</td><td>some text</td></tr>
    <tr id="6"><td>6</td><td>Six</td><td>some text</td></tr>
</table> -->
</body>
</html>