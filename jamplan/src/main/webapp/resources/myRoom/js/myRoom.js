/**
 * 
 */
var teamName = "";
var planName = "";
var teamList = [];
var planList = [];
// accordion 기능의 중복을 막기 위한 flag용 배열
var accordionFlag = [];
               

$(document).ready(function() {
   
   ajaxGetTeamList();
   ajaxGetPlanList();
   noticeMessage();

   
   // Bootstrap4의 accordion(panel속성과 연동) 기능을 쓰기 위한 클릭 이벤트
   $('.accordion').on('click', function () {
      let thisElement = this;
      let clickTeamName = $(thisElement).attr('id');
      console.log('clickteamName = ' + clickTeamName);
      console.log('accordionFlag = ' + accordionFlag);
      let element;
      let child;
      let innerText;
      let index = 0;
      
      let arrayIndex = teamNameArray.indexOf(clickTeamName);
      
      if (accordionFlag.indexOf(clickTeamName) == -1) {
         planList[arrayIndex].map(planElement => {
            
            element = makeUsualElement({
               tagName: 'p',
               className: 'move',
               value: planElement
            });
            
            if (planElement == null) {
               element = makeUsualElement({
                  tagName: 'p',
               });
               innerText = document.createTextNode("플랜을 만들어보세요.");
               element.appendChild(innerText);            
               thisElement.nextElementSibling.appendChild(element);
            }else {
               element = makeUsualElement({
                  tagName: 'p',
                  className: 'move',
                  value: planElement
               });
               child = document.createElement('a');
               child.setAttribute('id', planElement);
               child.setAttribute("href", "#");
               child.setAttribute("onclick", "return false");
               
               innerText = document.createTextNode(planElement);
               child.appendChild(innerText);            
               element.appendChild(child);
               thisElement.nextElementSibling.appendChild(element);
            }
         })
         
         accordionFlag.push(clickTeamName);
      }
      
      $(".move").on("click", function(e) {
         let planName = $(this).attr('value');
         
         if (planName != null) {
            planClickEvent(planName);
         }
      })
   });
   
      
      $(document).on("click","button.acceptTeamBut",function(){
            //alert("팀원초대 이벤트");
            //console.log($(this));
            var tr_index = $(this).attr('id').split('m')[1];
            var teamName = $(this).parent().siblings('.teamName').text();
            var sender = $(this).parent().siblings('.sender').text();
            
            $.ajax({
               url : '/jamplan/acceptToMember.do',
               type : 'POST',dataType : 'json',
               contentType : 'application/x-www-form-urlencoded;charset=utf-8',
               data :{'sender':sender,
                     'teamName':teamName},
               success : function(data) {
                  console.log(tr_index);
                  $('#tr'+tr_index).remove();
               },
               erorr : function(data){
                  alert("팀원초대 실패");
               }
            })
         })
   
      $(document).on("click", "button.rejectTeamBut", function(){
         var tr_index = $(this).attr('id').split('m')[1];
         var teamName = $(this).parent().siblings('.teamName').text();
         var sender = $(this).parent().siblings('.sender').text();
         deleteMessageTR(teamName,sender,tr_index)
         });
      
      
   $(document).on("click", "button.applyButCan", function(){
      var teamName = $(this).attr("value");
      //var sender = $(this).data("sender");
      deleteMessage(teamName)
         });
   
   
      $(document).on("click", "button.deleteMessageBut", function(){
            
         var tr_index = $(this).attr('id').split('m')[1];
         var teamName = $(this).attr("value");
         var sender = $(this).parent().siblings('.acceptSender').text();
         //var sender = $(this).data("sender");
         deleteMessage(teamName,sender, tr_index);
      });
         
      
   //메세지 클릭 이벤트
      $("#messageBut").on('click', function(){
            console.log("메세지 클릭 이벤트");
            //server에서 id값으로 메세지 테이블 값 가져오기
            $("#messageContent").empty();
            $("#countLabel").empty();
            $.ajax({
               url : '/jamplan/getMessageById.do',
               type : 'POST',
               dataType : 'json',
               contentType : 'application/x-www-form-urlencoded;charset=utf-8',
               async: false,
               success : function(data) {
                 
                  var notReadMessageCount = "";
                  var messageHtml = '';
                  $.each(data,function(index,item) {
                     
                     if(item.sender === null) {
                        
                        messageHtml += '';
                        return false;
                     }
                     //console.log(index);
                     //console.log(item.sender);
                     //console.log(item.teamName);
                     //메세지 분류하기
                     if(item.isRead == 0){ //팀장이 안 읽은 "신청" 메세지
                        messageHtml +='<tr id= tr'+index+'>';
                        messageHtml +='<td class = "teamName">'+item.teamName +'</td>';
                        messageHtml +='<td class = "sender">'+item.sender +'</td>';
                        messageHtml +='<td style="text-align:center;"><button id = acceptTeam'+index+' class = "btn btn-sm btn-light acceptTeamBut">수락</button>'
                        messageHtml +='<button id = rejectTeam'+index+' class = "btn btn-sm btn-light rejectTeamBut">거절</button>'
                        messageHtml +='</td>';
                        messageHtml +='</tr>';
//                        messageHtml += '<tr id = tr'+ index+'><td>'+item.sender + '님이 ' + item.teamName + '에 신청을 하셨습니다.' 
//                                 + '<span class="label label-primary messageNew"></span>'
//                                 + "<button id = acceptTeam"+index+" class = 'btn btn-sm btn-primary acceptTeamBut'>수락</button>"    
//                                 + "<button id = rejectTeamBut"+index+" class = 'btn btn-sm btn-primary rejectTeamBut' value = "+item.teamName+">거절</button></td></tr>";
//                        
//                        $("#messageTable").append(messageHtml);
//                        
                        
                        //$(".table"+item.teamName).data("teamName")

                     }else if(item.isRead == 1){//팀장이 이미 읽은 "신청" 메세지

                        messageHtml +='<tr id= tr'+index+'>';
                        messageHtml +='<td class = "teamName">'+item.teamName +'</td>';
                        messageHtml +='<td class = "sender">'+item.sender +'</td>';
                        messageHtml +='<td style="text-align:center;"><button id = acceptTeam'+index+' class = "btn btn-sm btn-light acceptTeamBut">수락</button>'
                        messageHtml +='<button id = rejectTeam'+index+' class = "btn btn-sm btn-light rejectTeamBut">거절</button>'
                        messageHtml +='</td>';
                        messageHtml +='</tr>';
//                        
//                        messageHtml += '<tr id = tr'+ index+'><td>'+item.sender + '님이 ' + item.teamName + '에 신청을 하셨습니다.'
//                                 +"<button id = acceptTeam"+index+" class = 'btn btn-sm btn-primary acceptTeamBut'>수락</button>" 
//                                 +"<button id = rejectTeamBut"+index+" class = 'btn btn-s btn-primary rejectTeamBut' value = "+item.teamName+">거절</button></td></tr>";
//                        $("#messageTable").append(messageHtml);

                        
//                        rejectTeamBut i    canTeam i

                     }else if(item.isRead == 2){//팀원에 신청을 한 유저가  "팀에 초대 수락 알림"을 받고 안 읽음
                        
                        messageHtml += '<tr id = tr'+ index+'>';
                        messageHtml += '<td class = "accetpTeamName">'+item.teamName+'</td>';
                        messageHtml += '<td class= "acceptSender">'+item.sender+'</td>';
                        messageHtml += '<td style="text-align:center;">수락됨<button id = acceptedTeam'+index+' class = "btn btn-sm btn-light deleteMessageBut" value = '+item.teamName+'><i class="fas fa-times"></i></button>'
                        messageHtml += '</td>';
                        messageHtml += '</tr>';
                     }else if(item.isRead == 3){//팀원에 신청을 한 유저가  "팀에 초대 수락 알림"을 받고 읽음
                        
                        messageHtml += '<tr id = tr'+ index+'>';
                        messageHtml += '<td class = "accetpTeamName">'+item.teamName+'</td>';
                        messageHtml += '<td class= "acceptSender">'+item.sender+'</td>';
                        messageHtml += '<td style="text-align:center;">수락됨<button id = acceptedTeam'+index+' class = "btn btn-sm btn-light deleteMessageBut" value = '+item.teamName+'><i class="fas fa-times"></i></button>'
                        messageHtml += '</td>';
                        messageHtml += '</tr>';
                        /*$("#canTeam"+index).data("sender",item.receiver);
                        $("#canTeam"+index).data("teamName",item.teamName);
                        $("#acceptTeam"+index).data("trIndex" ,index);
                        $("#rejectTeamBut"+index).data("trIndex",index);*/
                     }else if(item.isRead == 4){//팀원에 신청을 한 유저가  "팀에 초대 거절 알림" 을 받고  안 읽음
                        
                        messageHtml += '<tr id = tr'+ index+'>';
                        messageHtml += '<td class = "accetpTeamName">'+item.teamName+'</td>';
                        messageHtml += '<td class= "acceptSender">'+item.sender+'</td>';
                        messageHtml += '<td style="text-align:center;">거절됨<button id = rejectedTeam'+index+' class= "btn bnt-sm btn-light deleteMessageBut" value = '+item.teamName+'><i class="fas fa-times"></i></button>'
                        messageHtml += '</td>'; 
                        messageHtml += '</tr>';         
                        /*$("#canTeam"+index).data("sender",item.receiver);
                        $("#canTeam"+index).data("teamName",item.teamName);
                        $("#acceptTeam"+index).data("trIndex" ,index);
                        $("#rejectTeamBut"+index).data("trIndex",index);*/
                     }else if(item.isRead == 5){//팀원에 신청을 한 유저가  "팀에 초대 거절 알림" 을 받고 읽음
                        
                        messageHtml += '<tr id = tr'+ index+'>';
                        messageHtml += '<td class = "accetpTeamName">'+item.teamName+'</td>';
                        messageHtml += '<td class= "acceptSender">'+item.sender+'</td>';
                        messageHtml += '<td style="text-align:center;">거절됨<button id = rejectedTeam'+index+' class= "btn bnt-sm btn-light deleteMessageBut" value = '+item.teamName+'><i class="fas fa-times"></i></button>'
                        messageHtml += '</td>'; 
                        messageHtml += '</tr>';         
                        /*$("#canTeam"+index).data("sender",item.receiver);
                        $("#canTeam"+index).data("teamName",item.teamName);
                        $("#acceptTeam"+index).data("trIndex" ,index);
                        $("#rejectTeamBut"+index).data("trIndex",index);*/
                     }
                  })
                  $("#messageContent").append(messageHtml);
                  $("#countLabel").append(notReadMessageCount);
                  /*$(".messageNew").append("new");*/
               },
               error : function() {
                  alert("메세지 리스트 가져오기 실패, 메세지 클릭 이벤트 발생");
               }
            });
            
//            $.ajax({
//               url : '/jamplan/updateMessage.do',
//               async: false,
//               type : 'POST',
//               dataType : 'text',
//               contentType : 'application/x-www-form-urlencoded;charset=utf-8',
//               success : function(data) {
//                  
//                  alert("메세지 읽음 처리 완료")
//               },
//               erorr : function(data){
//                  alert("메세지 읽음 처리 실패")
//               }
//            });
            //
         })
   
   //요기
   
   $(document).on("click", "button.applyBut", function(){
      //alert("신청 버튼 이벤트 ")
      var teamName = $(this).attr("value");
      alert(teamName+"에 지원했습니다.");
      $.ajax({
         url : '/jamplan/applyToTeam.do',
         type : 'POST',
         data : {
               'teamName' : teamName,
               'isRead' : 0
               },
         dataType : 'json',
         contentType : 'application/x-www-form-urlencoded;charset=utf-8',
         success : function(data) {
            //alert(data.res);
         },
         error: function(data){
            alert(data.res);
         }
      });
   })

   
   $('#inputForm').on('click', function(event) {
      
      let teamName = document.getElementById('makeTeamName').value;
      let teamImage = document.getElementById('teamImage').files;
      let teamImageFile = teamImage[0];
      
      if(teamNameArray.length > 5 || teamName == '' || teamImageFile == null) {
         alert('다시 한번 확인해주세요.');
         
      }else {
            
         let formData = new FormData();
         
         formData.append("file", teamImageFile);
         formData.append("teamName", teamName);
         
         $.ajax({
               url : '/jamplan/makeTeam.do',
               type : 'POST',
               data : formData,
               enctype: 'multipart/form-data',
               processData: false,
               cache: false,
               async: false,
               contentType: false,
               dataType : "json",
               success : function(check) {
                  alert('이제 팀으로 들어가보세요.');
                  if (check == "SUCCESS") {
                     ajaxGetTeamList();
                     // 초기화
                     $('#teamName').val('');
                  } else {
                     alert("팀 만들기에 문제가 발생했습니다.");
                  }
               },
               error : function() {
                  alert("에러 발생!!");
               }
            });
         }
         
         // team을 만들었던 못만들었던 값은 초기화 시켜준다.
         document.getElementById('makeTeamName').value = '';
         document.getElementById('teamImage').files = null;
      });

   
   // 팀 이름에 대해 유효성 체크하는 부분
   $('#validationCheck').click(function() {
      validationCheck();
   })
   
   // Add plan 버튼에 대한 클릭 이벤트 부분
   $('#addPlanModalBtn').on('click', function() {
      $('#teamInPlanModal').empty();
      //일정 추가 버튼 클릭 시 추가 버튼 비활성화 > 팀을 선택한 이후 일정을 추가할 수 있도록
      $("#addPlanSubmit").attr('disabled', 'true');
      //console.log('테이블 생성하는 부분까지는 들어옴');
      var html = '<table id="teamNameTable" class="table table-hover teamNameTable text-center">'
            + '<thead><tr><th><h2>No.</h2></th><th><h2>가입된 팀</h2></th>'
            + '</tr></thead><tbody>';
      //console.log('테이블 생성하기 직전');
      for (var index = 0; index < teamNameArray.length; index++) {
         html += '<tr><td>' + (index + 1)
               + '</td><td class="teamNameBox"><a href="" onclick="return false;">' + teamNameArray[index] + '</a></td></tr>';
      }
   
      html += '</tbody></table>';
      //console.log('테이블 생성 태그는 모두 완성');
      $('#teamInPlanModal').append(html);
      //console.log('append했지만 과연??!!');
      
      planAddbut();
      
      $("#addPlanSubmit").click(function(){
         
         planName = $("#planName").val();
         //console.log("plan add teamname"+teamName);
         //console.log("plan add planname"+planName);
         $.ajax({
            url : '/jamplan/insertPlan.do',
            type : 'POST',
            dataType : 'json', 
            contentType : 'application/x-www-form-urlencoded;charset=utf-8',
            data : { 'teamName' : teamName,
                   'planName' : planName},
            success : function(data){
               //console.log(str.res);
               teamName ="";
               planName="";
               
               if(data !== null) {
                  // 받아온 배열에서 팀명이 바뀌면 새로운 배열에 넣기 위해 팀명 변화 시, 증가하는 변수.
                  let index = 0;
                  var teamNameArrayCopy = Array.from(teamNameArray);
                  
                  console.log(data);
                  
                  data.reverse().filter(team => {
                     if (teamNameArrayCopy.indexOf(team.teamName) != -1) {
                        let newArr = new Array();
                        planList.push(newArr);
                        planList[index].push(team.planName);
                        index += 1;
                        teamNameArrayCopy.shift(team.teamName);
                     }else {
                        planList[index-1].push(team.planName);
                     }
                  })   
                  console.table(planList);
               }
            },
            error:function(data){
               //console.log(str.res);
               alert("플랜 추가 : 데이터 전송 실패");
               teamName ="";
               planName="";
            }
         });
      });

   });
   
   
   // My Info로 가는 버튼
   document.getElementById('infoButton').onclick = function () {
      
      let form = makeFormElement({
         method: 'post',
         action: '/jamplan/myInfoPage.info', 
         name: 'infoForm'
      });
      
      let input = makeInputElement({
         type: 'text',
         name: 'id',
         value: document.getElementById('userId').innerHTML
      });
      
      form.appendChild(input);
      form.submit();
   }
   
   
   document.getElementById('searchButton').onclick = function (id) {
      
      let form = makeFormElement({
         method: 'post',
         action: '/jamplan/main.search',
         name: 'infoForm'
      });
      
      let input = makeInputElement({
         type: 'hidden',
         value: id
      });
      
      document.body.appendChild(form);
      form.appendChild(input);
      form.submit();
   }
   
   
   
   document.getElementById('logoutButton').onclick = function () {
      let form = makeFormElement({
         method: 'post',
         action: '/jamplan/logout.do',
         name: null
      })
      document.body.appendChild(form);
      form.submit();
   }

});


// onready end===========================================================================

function deleteMessageTR(teamName, sender,tr_index) {
    var teamName = teamName;
    var sender = sender;
    var index2 = tr_index;
    $.ajax({
       url : "/jamplan/deleteMessageTrToTeam.do",
       type : 'POST',
       data : {
             'teamName' : teamName,
             'sender' : sender
             },
       dataType : 'json',
       contentType : 'application/x-www-form-urlencoded;charset=utf-8',
       success : function(data) {
          $('#tr'+index2).remove();
       },
       error: function(data){ 
          alert(data.res);
       }
    });
 }


function deleteMessage(teamName,sender,tr_index){
    //alert("취소 버튼 이벤트 ")
    var teamName = teamName;
    var sender = sender; 
    var tr_index = tr_index;
    //alert("팀이름 : " + teamName);
    $.ajax({
       url : "/jamplan/deleteMessageToTeam.do",
       type : 'POST',
       data : {
             'teamName' : teamName,
             'sender' : sender},
       dataType : 'json',
       //contentType : 'application/x-www-form-urlencoded;charset=utf-8',
       success : function(data) {
          $('#tr'+tr_index).remove();
       },
       error: function(data){ 
          alert(data.res);
       }
    });
 }      


//message 창 안읽은 메세지 표시
function noticeMessage(){
   //alert("메세지 안읽은것 표시하기 이벤트");
   var notReadMessageCount = 0;
   $.ajax({
      url : '/jamplan/getMessageById.do',
      type : 'POST',
      dataType : 'json',
      contentType : 'application/x-www-form-urlencoded;charset=utf-8',
      success : function(data) {
         /*alert("메세지 리스트 가져오기 성공");*/
         
         var messageHtml = "";
         $.each(data,function(index,item) {
            if(item.receiver === null) {
               return false;
            }
            
            //안읽은 메세지 수 세기
            if(item.isRead == 0 || item.isRead == 2 || item.isRead == 4){
               notReadMessageCount++;
            }   
         })
         
         $("#countLabel").append(notReadMessageCount);
         
         
         /*if(notReadMessageCount!=0){
            const iconDiv = document.getElementById('envelope');
            console.log(iconDiv);
            iconDiv.children[0].removeClass('fa-envelope-open');
            iconDiv.children[0].addClass('fa-envelope');
         }*/
      },
      error : function() {
         alert("메세지 리스트 가져오기 실패 - 페이지 로드 시");
      }
   });
}


//팀 모달창 나온 이후 팀을 선택해야만 선택 창을 클릭 할 수 있도록 설정 
function planAddbut(){
   $(".teamNameBox").on('click', function(){
      teamName = $(this).children().html();
      //console.log("팀네임 이벤트 : " +teamName );
      $("#addPlanSubmit").removeAttr("disabled");
   })
}

// 배열을 생성하고 팀 이름들을 저장한다.
//btn btn-primary, btn btn-danger  
var teamNameArray = [];

function ajaxGetTeamList() {      
   $('#teamList').empty();   
   $.ajax({

         url : '/jamplan/ajaxPrintTeamList.do',
         type : 'POST',
         contentType : 'application/x-www-form-urlencoded;charset=utf-8',
         async: false,
         dataType : 'json',
         //async: false,
         success : function(data){ 
               $.each(data,function(index, item) {
                  var internalTeamList = '';
                  internalTeamList += '<div><button class="btn btn-outline-light text-dark accordion" id="'
                        +item.teamName + '">'+ item.teamName + '</button>';
                  internalTeamList += '<div class ="panel">';                  
                  internalTeamList += '</div></div>';
                  $('#teamList').append(internalTeamList);
      
                  // 팀명을 배열에 담고 Add plan 버튼에서의 테이블 생성에
                  // 이용한다.
                  teamNameArray[index] = item.teamName;
               });
               
               var nodeList = document.querySelectorAll(".accordion");
               var selector = Array.from(nodeList);
               var indexNo;
               var panelList = [];
               
               selector.map(nodeItem => {
                  let panel = nodeItem.nextElementSibling;
                  panelList.push(panel);
               });
               
               selector.map(selectorItem => {
                  selectorItem.addEventListener('click', function () {
                     let thisNode = this.classList.toggle('active');
                     var panel = this.nextElementSibling;
                       
                     if(panel.style.display === 'block') {
                        panel.style.display = 'none';
                     }else {
                        panel.style.display = 'block';
                     }

                  })
               })
               
               
            }, 
         
         error : function(e) {
            console.error(e);
         }
   });
}; 



function ajaxGetPlanList() {
   console.log('ajaxGetPlanList IN');
   
   $.ajax({   
      url : '/jamplan/getPlanListById.do',
      type : 'POST',
      contentType : 'application/x-www-form-urlencoded;charset=utf-8',
      async: false,
      dataType : 'json',
      success : function(data) {
         console.log(data);
         if(data !== null) {
            // 받아온 배열에서 팀명이 바뀌면 새로운 배열에 넣기 위해 팀명 변화 시, 증가하는 변수.
            let index = 0;
            var teamNameArrayCopy = Array.from(teamNameArray.sort());
            
            console.table(teamNameArrayCopy);
            
            data.sort().map(team => {
               if (teamNameArrayCopy.includes(team.teamName)) {
                  let newArr = new Array();
                  planList.push(newArr);
                  planList[index].push(team.planName);
                  index += 1;
                  const nameIndex = teamNameArrayCopy.indexOf(team.teamName);
                  teamNameArrayCopy.splice(nameIndex, 1);
               }else {
                  planList[index-1].push(team.planName);
               }
            })   
            console.table(planList);
         }      
      }
   });
}


//플랜 클릭시 페이지 이동 이벤트  form으로 데이터 전달 및 페이지 이동
function planClickEvent(selectedPlan){

   const planName = selectedPlan;
   console.log("플랜 이벤트 플랜명  : " + planName);
   
   let form = makeFormElement({
      method: 'post',
      action: '/jamplan/movePlanMainPage.do',
   });
   
   let input = makeInputElement({
      type: 'hidden',
      value: planName,
      name: 'planName'
   });
   form.appendChild(input);

   document.body.appendChild(form);
   
   form.submit();
}

function planListAdd(teamName, indexI){
   var RetrunList ="";
   var teamN = teamName;
   var i = indexI;
   $.ajax({   
      url : '/jamplan/getPlanListById.do',
      type : 'POST',
      contentType : 'application/x-www-form-urlencoded;charset=utf-8',
      async: false,
      dataType : 'json',
      success : function(data) {
         //console.log("ajax get teamName"+teamName);
         $.each(data,function(index,item){
            //console.log(item.teamName);
            if(teamN == item.teamName && item.planNo != 0){
               
               //console.log("if문 플랜 정보 "+item.planNo);
               //console.log("플랜이름"+item.planName);

               RetrunList = '<div id="planSelectList' + item.planNo + ' class ="movePlan" value = "'+item.planNo+'">' + item.planName + '</div>';
               $('#myTeam'+i).append(RetrunList);   

            }
         })
      }
   })
}

function validationCheck() {
   var params = $('#makeTeamName').val();
   if(params == ''){
	   return null;
   }
   jQuery.ajax({
      url : '/jamplan/validationCheck.do',
      type : 'GET',
      data : {
         teamName : params
      },
      contentType : 'application/x-www-form-urlencoded;charset=utf-8',
      dataType : 'text',
      // check가 responsebody에서 오는 데이터를 받는다.
      success : function(check) {
         if (check == 'SUCCESS') {
        	alert("사용 가능한 팀 이름입니다.")
            $('#validationCheck').val('check');
            $('#validationCheck').css('color', 'powderblue');
            // wookim edit
            $('#inputForm').attr('enable', 'true');			//팀 추가 버튼

         } else {
        	alert("이미 존재하는 팀입니다.")
            $('#makeTeamName').val('');
            $('#inputForm').attr('disabled', 'true');

            if ($('#makeTeamName').focus()) {
               $('#validationCheck').attr('enable', 'true');		// 팀이름 중복 체크 버튼
            } else {
               //alert("focus out");
            }
         }
      },
      error : function() {
         alert("error!!");
      }
   });

}

// form태그를 만들어내는 함수
function makeFormElement({method, action, name}) {
   let form = document.createElement('form');
   form.setAttribute('method', method);
   form.setAttribute('action', action);
   form.setAttribute('name', name);
   document.body.appendChild(form);
   
   return form;
}

// input태그를 만들어내는 함수
function makeInputElement({type, id, value, name}) {
   let input = document.createElement('input');
   input.setAttribute('type', type);
   input.setAttribute('id', id);
   input.setAttribute('value', value);
   input.setAttribute('name', name);
   
   return input;
} 


// 일반 태그를 만들어내는 함수
// tagName은 필수로 입력해야한다. (타입은 문자열)
function makeUsualElement({tagName, className, id, value, name}) {
   let element;
   try {
      element = document.createElement(tagName);
      element.setAttribute('class', className);
      element.setAttribute('id', id);
      element.setAttribute('value', value);
      element.setAttribute('name', name);
      
   } catch (error) {
      console.error(error);
   }
   
   return element;
};


/* 메세지 팝업창을 위한 스크립트 */
$(document).on('click', '.panel-heading span.icon_minim', function(e) {
   var $this = $(this);
   if (!$this.hasClass('panel-collapse')) {
      $this.parents('.panel').find('.panel-body').slideUp();
      $this.addClass('panel-collapse');
      $this.removeClass('glyphicon-minus').addClass('glyphicon-plus');
   } else {
      $this.parents('.panel').find('.panel-body').slideDown();
      $this.removeClass('panel-collapse');
      $this.removeClass('glyphicon-plus').addClass('glyphicon-minus');
   }
});
/* 메세지 팝업창을 위한 스크립트 끝 */