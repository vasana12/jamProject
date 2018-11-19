/* @author wookim
 */

/*$(document).ready(function(){
   
   function selectData(){ 
      //table내부 내용을 제거(초기화), 동적으로 제거
       $(.imgClick).click(function) {
         
      } 
      //alert("hahaha");
      $.ajax({ //jquery에서 ajax호출할때 사용하는 방식 / jQuery.ajax=$.ajax : jquery 표현하는방식 2가지 
         url:'/jamplan/imgJson.do',
         type:'GET',
         dataType:"json",
         contentType:'application/x-www-form-urlencoded; charset=utf-8',
         success:function(data){ // 응답(getPeopleJSON.do)이 존재하면 함수 실행된다. ,  getPeopleJSON.do에서 실행한 값이 data에 저장된다
            $.each(data, function(index, item){ // data 는 PeopleVO의 list들이 저장되어 item에 값(항목들)이 주어진다. / each는 data가 가지고있는 값만큼 반복수행하게해준다.(각각의데이터접근시)
               var output = '';
               output += '<div class="col-sm-4">';
               output += '<div class="thumbnail">';
               //output += '<div class="imgClick"><a href = "schedule.search">' + item.image + '</a></div>';
               //output += 
               //output += '<div>' + item.image + '/<div>'
               
               output += '<div><a href = "schedule.search"><img src="/jamplan/image/' + item.image + '" style="width:400px; height:400px;"  />' + '</a></div>';
               output += '<span>' + item.planDate + '</span>' + '&nbsp' + '&nbsp';
               output += '<span>' + item.planName + '</span>' + '&nbsp' + '&nbsp';
               output += '<button class="btn goodCount">' + item.goodCount + '</button>' + '&nbsp' + '&nbsp';
               output += '<button class="btn">' + item.readCount + '</button>' + '&nbsp' + '&nbsp';
               output += '</div>';
               output += '</div>';
               //console.log("output:" + output);
               $('#search').append(output);
            
            });
         },
         error:function(){
            alert("ajax통신 실패!!!");
         }
      });
   }

   selectData();
});*/


//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
$(document).ready(function(){
   
   $(".myroomBtn").click(function(){
      
      location.href="myroom.do";
   });
   
   $(".infoBtn").click(() => {
	  let form = makeFormElement({
		  method: 'post',
		  action:"/jamplan/myInfoPage.info",
	  });
	  form.submit();
   });
   
   $(".logoutBtn").click(() => {
	   let form = makeFormElement({
			method: 'post',
			action: '/jamplan/logout.do', 
		});
		
		form.submit();
   });
   
   
   
   
   
   var idck = 0;

	 //idck 버튼을 클릭했을 때 
	 $("#idck").click(function(e) {
	     
	     //userid 를 param.
	     var userid =  $("#usr2").val(); 
	     
	     $.ajax({
	    	 url : "/jamplan/idcheck.do",
	         type : 'POST',
	         data : userid,
	         dataType : "json",
	         contentType: "application/json; charset=UTF-8",
	         success : function(data) {
	             if (data.cnt !== 0) {
	                 
	                 alert("아이디가 존재합니다. 다른 아이디를 입력해주세요.");
	                 //아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
	                 $("#usr2").addClass("has-error")
	                 $("#usr2").removeClass("has-success")
	                 $("#usr2").focus();
	                 
	             
	             } else {
	                 alert("사용가능한 아이디입니다.");
	                 //아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
	                 $("#usr2").addClass("has-success")
	                 $("#usr2").removeClass("has-error")
	                 $("#usr2").focus();
	                 //아이디가 중복하지 않으면  idck = 1 
	                 idck = 1;
	                 
	             }
	         },
	         error : function(error) {
	             
	             alert("error : " + error);
	         }
	     });
	 });
   
   
});


//form태그를 만들어내는 함수
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


$(function() {
   
   
   
   $("#joinB").click(function() {
      
      var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
      var getIdCheck = RegExp(/^[a-zA-Z0-9_]+$/);
      var getPassCheck= RegExp(/^[a-zA-Z0-9]{6,12}$/);
    
      var userid = $("#usr2").val();
      var userpwd = $("#pwd2").val();
      var email = $("#email").val();
       
      if(email.length == 0){
          alert("이메일을 입력해주세요");
      $("#email").focus();
      return false;
      }
      if(!getMail.test(email))
      {
          alert("이메일형식에 맞게 입력해주세요")
          $("#email").val("");
          $("#email").focus();
          return false;
       }
      
      if(userid.length == 0){
          alert("아이디를 입력해 주세요"); 
          $("#usr2").focus();
          return false;
      }
      
      if(!getIdCheck.test(userid)){
           alert("아이디는 오직 문자와 숫자, _ 기호만 입력가능");
           $("#usr2").val("");
           $("#usr2").focus();
           return false;
         }
      
      if(userpwd.length == 0){
          alert("비밀번호를 입력해 주세요"); 
          $("#pwd2").focus();
          return false;
      }
      
      if(!getPassCheck.test(userpwd))
      {
          alert("비밀번호는 영어,숫자 조합으로 해주세요.(글자수는 6~12)")
          $("#pwd2").val("");
         $("#pwd2").focus();
         return false;
         }
      
      if(confirm("회원가입을 하시겠습니까?"))
         {
             if(idck==0){
                 alert('아이디 중복체크를 해주세요');
                 return false;
             }
             else
             {	
            	/*var joinForm = $("#joinForm").serialize();
                $.ajax({
                	url: "/jamplan/join.do",
                	data: joinForm,
                	type: "POST",
                	dataType: "json",
                	contentType: "application/json; charset=UTF-8",
                	success: function() {
                		
                	},
                	error: function(err) {
                		alert(err);
                	}
                });*/
            	 
                /*location.href="join.do?id="+userid+"&pass="+userpwd+"&email="+email;*/
              }
          }
      });
   });