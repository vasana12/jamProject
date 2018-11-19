/**@author Taehyuk, Kim
 * 
 */

var planList = [];
var dropdownFlag = true;

//문서 전체 출력 후
$(document).ready(function() {
	
	ajaxGetPlanList();
	
	$('#dropdown05').hover(function() {
		
		let thisElement = this;
		let html;
		let innerText;
		
		if (dropdownFlag) {
			planList[0].map(function (planElement) {				
				html = document.createElement('a');
				html.setAttribute('class', 'dropdown-item');
				html.setAttribute("href", "#");
				innerText = document.createTextNode(planElement);
				html.appendChild(innerText);
				thisElement.nextElementSibling.appendChild(html);
			});
			dropdownFlag = false;
		}
	
		
		// plan 클릭시, 페이지 이동.
		$('.dropdown-item').on('click', function () {
			const thisElement = this.innerHTML;
			console.log('dropdown-itme 들어옴 => ' + thisElement);
			planClickEvent(thisElement);
		})
		
		
	}, function () {})
	
	

	$("#calendarContent").load("calendarPage.jsp");
   
   $(document).on("click", "#mapTab", function() {
         $("#mapContent").load("mapPage.jsp");
   });
      
   $(document).on("click", "#planTableTab", function() {
      $("#planTableContent").load("planTablePage.jsp");
   });
 
  
		
	$('#openBtn').on('click', function() {
		openForm();
	})
	
	$('#cancelButton').on('click', function () {
		closeForm();
	})

	
	// My Info로 가는 버튼
	$('#infoButton').on('click', function () {
		var id = $(this).attr('value');
		var form = document.createElement('form');
		form.setAttribute('method', 'post');
		form.setAttribute('action', '/jamplan/myInfoPage.info');
		form.setAttribute('name', 'infoForm');
		document.body.appendChild(form);
		
		var inputForm = document.createElement('input');
		inputForm.setAttribute('type', 'hidden');
		inputForm.setAttribute('name', 'id')
		inputForm.setAttribute('value', id);
		form.appendChild(inputForm);
		console.log(form);
		form.submit();
	});
	


// ============================================= 수정 부분 시작 ============================
	
})

function ajaxGetPlanList() {
	
	let logoWrap = document.querySelector('.logo-wrap h1 a');
	let planName = logoWrap.toString().split('#')[1];
	console.log('planName : ' + planName);
	
	$.ajax({	
		url : '/jamplan/getOthers.mp',
		type : 'POST',
		data : {
			'planName': planName
		},
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		async: false,
		dataType : 'json',
		success : function(data) {
			//console.log("ajax get teamName"+teamName);
//			console.log(data);
			if(data != null) {
				// 받아온 배열에서 팀명이 바뀌면 새로운 배열에 넣기 위해 팀명 변화 시, 증가하는 변수.
				let teamChange = 0
				
				for(let i=0; i < data.length; i++) {
//					console.log(data[i]);
					while(data.length-1 > i ) {
						// 팀명이 바뀌는 순간!
						if (data[i]['teamName'] != data[i+1]['teamName']) {
							// 팀명이 바뀌기 전이므로 teamChange 증가되기 전에 넣어준다.(앞배열에 넣어준다.)
							planList[teamChange].push(data[i]['planName']);
							
							if (i == data.length-2) {
								teamChange += 1;
								let newArr = new Array();								
								planList.push(newArr);
								// 마지막에서 두번째와 첫번째가 서로 다를 경우와 같을 경우로 나눠야 한다.
								// 지금은 서로 다른 경우.
								planList[teamChange].push(data[i+1]['planName']);
								break;
							}
							
							// 새로운 배열을 만들기위해 index 역할을 하는 teamChange를 증가시켜준다.
							teamChange += 1;
							let newArr = new Array();
							
							// teamList에 새로운 배열을 추가시키고 그 배열 안에 넣는다.
							planList.push(newArr);
							break;
							
						}else {
							if(i == 0) {
								let newArr = new Array();
								planList.push(newArr);
								planList[teamChange].push(data[i]['planName']);
								break;
							}else {
//								alert('data.length : ' + data.length + ' / i : ' + i);
								planList[teamChange].push(data[i]['planName']);
								
								if (i == data.length-2) {
									// 마지막에서 두번째와 첫번째가 서로 다를 경우와 같을 경우로 나눠야 한다.
									// 지금은 서로 같은 경우.
									planList[teamChange].push(data[i+1]['planName']);
									break;
								}
								
								break;
							}
						}
						
					}
				};
			}
			$.each(planList, function (index, item) {
				console.log(item);
			})
		}
	});
}


function planClickEvent(selectedPlan){

	const planName = selectedPlan;
	console.log("플랜 이벤트 플랜명  : " + planName);
	
	var form = document.createElement("form");
	form.setAttribute("method","post");
	form.setAttribute("action","/jamplan/movePlanMainPage.do");
	document.body.appendChild(form);
	
	var input = document.createElement("input");
	input.setAttribute("type", "hidden");
	input.setAttribute("name", "planName");
	input.setAttribute("value", planName);
	form.appendChild(input);
	
	form.submit();
	
}


//chat창에 대한 스크립트
function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

	
