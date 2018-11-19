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
      
      console.table(planList);
      
      if (dropdownFlag) {
         planList.map(function (planElement) { 		// planList[0].map(function (planElement) {           
            html = document.createElement('a');
            html.setAttribute('class', 'dropdown-item');
            html.setAttribute("href", "#");
            innerText = document.createTextNode(planElement);
            html.appendChild(innerText);
            thisElement.nextElementSibling.appendChild(html);
         });
         
         if (planList.length == 0) {
       	  html = document.createElement('a');
             html.setAttribute('class', 'dropdown-item');
             html.setAttribute("href", "#");
             html.setAttribute("onclick", "return false;");
             innerText = document.createTextNode("다른 팀을 만들어보세요");
             html.appendChild(innerText);
             thisElement.nextElementSibling.appendChild(html);
         }
         
         dropdownFlag = false;
      }
      
      // plan 클릭시, 페이지 이동.
      $('.dropdown-item').on('click', function () {
         const thisElement = this.innerHTML;
         console.log('dropdown-itme 들어옴 => ' + thisElement);
         planClickEvent(thisElement);
      })
      
      
   }, function () {})
   
   

   //var myFolder = new Folder(window.location.pathname);
   //console.log(folder.exits);
   //console.log(window.location.pathname);
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
      form.setAttribute('action', 'myInfoPage.info');
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
   
   document.getElementById('searchButton').onclick = function (id) {
      let form = document.createElement('form');
      form.setAttribute('method', 'post');
      form.setAttribute('action', 'main.search');
      form.setAttribute('name', 'infoForm');
      document.body.appendChild(form);
      
      let inputForm = document.createElement('input');
      inputForm.setAttribute('type', 'hidden');
      inputForm.setAttribute('value', id);
      form.appendChild(inputForm);
      
      form.submit();
   }


// ============================================= 수정 부분 시작 ============================
   
})

function ajaxGetPlanList() {
   
	let planName = document.querySelector('.logo-wrap h1 a').innerHTML;
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
         console.log(data);
         if(data != null) {
            data.filter((planElement) => {
            	if (planElement.planName != planName) {
            		planList.push(planElement.planName);
            	}
            })
         }
         console.table(planList);
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

   