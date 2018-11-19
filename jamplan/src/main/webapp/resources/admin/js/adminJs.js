/**@author Taehyuk, Kim

 * 
 */


// 팀명으로 검색할 경우
function adminTeamSearch() {
	$('#adminItemPrint').empty();
	var item = $('#searchItem').val();
	console.log("adminTeamSearch 들어옴");
	var html = '<table id="teamSearchTable" class="table table-borderless table-hover text-center">'
		+ '<thead><tr><th>팀명</th><th>팀장</th>'
		+ '</tr></thead><tbody>';
	
	$.ajax({	
		url : 'adminTeamSearch.admin',
		type : 'POST',
		data : {
			'searchItem' : item
		},
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
			if (data != null) {
				
				$.each(data, function(index,item){
					html += '<tr><td class="teamName">' + item.teamName + '</td>'
						+ '<td>' + item.id + '</td>'
						+ '<td><button id="teamRemove' +index + '" class="btn btn-danger" value = '+item.teamName+'>삭제하기</button></td></tr>';
				})
				
				html += '</tbody></table>';
				$('#adminItemPrint').append(html);
			}else {
				console.log('null 들어옴');
			}
			
		},
		error : function() {
			console.log("에러");
		}
	})
}


// 일정명으로 검색할 경우
function adminPlanSearch() {
	$('#adminItemPrint').empty();
	var item = $('#searchItem').val();
	
	var html = '<table id="planSearchTable" class="table table-borderless table-hover text-center">'
		+ '<thead><tr><th>일정명</th><th>좋아요 수</th><td>조회수</td><td>들어가기</td>'
		+ '</tr></thead><tbody>';
	
	$.ajax({	
		url : 'adminPlanSearch.admin',
		type : 'POST',
		data : {
			'searchItem' : item
		},
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
			
			$.each(data,function(index,item){
				html += '<tr><td class="planName">' + item.planName + '</td>'
					+ '<td>' + item.goodCount + '</td>'
					+ '<td>' + item.readCount + '</td>'
					+ '<td><button id="toPlan' + index + '" class="btn btn-primary" value = '+item.planName+'>들어가기</button></td></tr>' 
		
			})	
			html += '</tbody></table>';
			$('#adminItemPrint').append(html); 
			
		}
	})
}


// 사용자 id로 검색할 경우
function adminUserSearch() {
	$('#adminItemPrint').empty();
	var item = $('#searchItem').val();
	
	var html = '<table id="userSearchTable" class="table table-borderless table-hover text-center">'
		+ '<thead><tr><th>아이디</th><th>이메일</th><td>비밀번호</td><td>지우기</td>'
		+ '</tr></thead><tbody>';
	
	$.ajax({	
		url : 'adminUserSearch.admin',
		type : 'POST',
		data : {
			'searchItem' : item
		},
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
			console.log(data);
			html += '<tr><td class="userSearch">' + data.id + '</td>'
				+ '<td>' + data.email + '</td>'
				+ '<td>' + data.pass + '</td>'
				+ '<td><button id="userRemove" class="btn btn-outline-danger btn-rounded"' 
				+ ' type="submit">지우기</button></td></tr></tbody></table>';
			
			
			/*if(data[nation] === null) {
				$('#userSearch:nth-child' + (i)).text('-');
			}else if(data[gender] === null) {
				
			}else if(data[image] === null) {
				
			}else if(data[age] == 0) {
				
			}*/
			
			
			/*// data의 각각의 value에 null이 포함됐는지 알아보는 부분
			var index = -1;
			var target = null;
			var nullFind = data.find(function(item, i) {
				if(item.nation === target){
					index = i;
					$('#userSearch:nth-child' + (i)).text('-');
					
				}else if(item.gender === target) {
					index = i;
					
					
				}else if(item.image === target) {
					index = i;
					$('#userSearch:nth-child' + (i)).text('-');
					
				}else if(item.age === target) {
					index = i;
					$('#userSearch:nth-child' + (i)).text('-');
					
				}
			})
			
			nullFind();*/
				
			$('#adminItemPrint').append(html); 
		},
		error : function() {
			console.log("error");
		}		
	})
}



// 일정을 클릭하면 해당 일정으로 들어가기. (수정은 불가능)
function watchPlanAsAdmin(planNo) {
	var planNO = planNo;
	
	var form = document.createElement("form");
	form.setAttribute("method","post");
	form.setAttribute("action","movePlanMainPage.do");
	document.body.appendChild(form);
	
	var inputPlanNo = document.createElement("input");
	input.setAttribute("type","hidden");
	input.setAttribute("name","planNo");
	input.setAttribute("value",planNo);
	form.appendChild(inputPlanNo);
	
	// 관리자의 아이디를 보내고 해당 일정페이지에서 아이디를 체크해서
	// 관리자임이 확인되면 
	var adminId = 'admin';
	var inputAdmin = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "id");
	input.setAttribute("value", adminId);
	form.appendChile(inputAdmin);
	
	form.submit();
}

$(document).ready(function() {
	
	//select값의 변화에 따라 controller에 따로 들어가게된다.
	$('#searchButton').on('click', function(e) {
		e.preventDefault();
		
		console.log($('.option')[0].selected);
		//각각의 option항목에 자신이 선택됐는지 확인하는 과정
		if($('.option')[0].selected == true) {
			adminTeamSearch();
			
		}else if($('.option')[1].selected == true) {
			adminPlanSearch();
		
		}else if($('.option')[2].selected == true) {
			adminUserSearch();
		
		}
	})
	
	// 팀 삭제하는 부분
	// $(document).on(a, b, c)는 화면이 로드된 후 추가된 b에 대해 a란 이벤트가 발생 시, c라는 함수를 호출한다는 의미
	$(document).on('click', '#teamSearchTable button', function(e){
		e.preventDefault();
		
		console.log("#teamSearchTable button까지 들어왔나");
		var teamName = $(this).parent().siblings('.teamName').text();
		console.log(teamName);
		$(this).empty();
		$.ajax({
			url : '/jamplan/removeTeam.admin',
			type : "post",
			data : {
				'teamName': teamName
			},
			contentType : 'application/x-www-form-urlencoded; charsert=utf-8',
			dataType : "text",
			success:function(result){
				if(result != '0') {
					location.reload();
				}else {
					alert('입력한 팀명에 해당하는 팀이 없습니다. ');
				}
			},	
			error:function(){
				console.log('팀 삭제 ajax 실패');
			}
		});
	})
	
	
	//  들어가기 버튼을 누를 경우 해당 일정으로 이동하는 부분
	// $(document).on(a, b, c)는 화면이 로드된 후 추가된 b에 대해 a란 이벤트가 발생 시, c라는 함수를 호출한다는 의미
	$(document).on('click', "#planSearchTable button", function(){
		console.log("#planSearchTable button까지 들어왔나");
		var planName = $(this).parent().siblings('.planName').text();
		console.log(teamName);
		
		var form = $('form');
		form.setAttribute("method", "post");
		form.setAttribute("action", "movePlanMainPage.do");
		$(body).append(form);
		
		var input = $('input');
		input.setAttribute("type", "hidden");
		input.setAttribute("name", "planName");
		input.setAttribute("value", planName);
		form.append(input)
		
		form.submit();
		
	})
	
	
	// 유저 삭제하는 부분
	// $(document).on(a, b, c)는 화면이 로드된 후 추가된 b에 대해 a란 이벤트가 발생 시, c라는 함수를 호출한다는 의미
	$(document).on('click', '#userSearchTable button', function(e){
		e.preventDefault();
		console.log("userSearchTable Button까지 들어왔나");
		var id = $(this).parent().siblings('.userSearch').text();
		console.log(id);
		
		$.ajax({
			url : '/jamplan/removeUser.admin',
			type : "post",
			data : {
				'id': id
			},
			contentType : 'application/x-www-form-urlencoded; charsert=utf-8',
			dataType : "text",
			success:function(result){
				if(result == '1') {
					location.reload();
				}else {
					alert('입력한 아디이에 해당하는 유저가 없습니다.');
				}
			},	
			error:function(){
				console.log('유저 삭제 ajax 실패');
			}
		});
	})
});





