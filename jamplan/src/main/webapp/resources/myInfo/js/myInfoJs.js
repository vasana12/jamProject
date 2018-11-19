/**@author Taehyuk, Kim


 * 
 */

//문서 전체 출력 후
/*function autocomplete(inp, arr) {
  두개의 파라미터를 입력한다.
   * 하나는 내가 실제 입력하는 값, 하나는 autocomplete을 위한 값들의 배열이다.
  var currentFocus;
  입력칸에 무언가 입력되면 이벤트가 발생하게된다.
  inp.addEventListener("input", function(e) {
      var a, b, i, val = this.value;

      closeAllLists();
      if (!val) { return false;}
      currentFocus = -1;
      아이템들을 담게될 div 태그를 만드는 부분 (values):
      a = document.createElement("DIV");
      a.setAttribute("id", this.id + "autocomplete-list");
      a.setAttribute("class", "autocomplete-items");
      autocomplete container의 자손으로 div태그를 추가한다.
      this.parentNode.appendChild(a);
     
      for (i = 0; i < arr.length; i++) {
        입력칸에 입력된 글자와 같은 글인지 체크하는 부분
        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
          매칭되는 태그마다 div태그를 추가한다.
          b = document.createElement("DIV");
          매칭되는 글자에 bold효과를 준다.
          b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
          b.innerHTML += arr[i].substr(val.length);
          item값들을 담을 array를 input의 value값으로 설정한다.
          b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";

          b.addEventListener("click", function(e) {
              
              inp.value = this.getElementsByTagName("input")[0].value;
              
              closeAllLists();
          });
          a.appendChild(b);
        }
      }
  });
  
  키보드에서 키를 누르면 함수를 호출한다ㅏ.
  inp.addEventListener("keydown", function(e) {
      var x = document.getElementById(this.id + "autocomplete-list");
      if (x) x = x.getElementsByTagName("div");
      if (e.keyCode == 40) {
        아래쪽 방향키를 누르면 포커스가 이동한다.
        currentFocus++;

        addActive(x);
      } else if (e.keyCode == 38) { //위쪽 방향키
        위쪽 방향키를 누르면 포커스가 이동한다.
        currentFocus--;

        addActive(x);
      } else if (e.keyCode == 13) {
        엔터키에 의한 submit 방지를 위한 부분
        e.preventDefault();
        if (currentFocus > -1) {
          active item에 대한 시뮬레이션
          if (x) x[currentFocus].click();
        }
      }
  });
  
  function addActive(x) {
    active클래스만 고른다.
    if (!x) return false;
    active 클래스 지운다.
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);
    "autocomplete-active"클래스 더하는 부분
    x[currentFocus].classList.add("autocomplete-active");
  }
  
  function removeActive(x) {
    active 상태 없애는 부분
    for (var i = 0; i < x.length; i++) {
      x[i].classList.remove("autocomplete-active");
    }
  }
  
  function closeAllLists(elmnt) {
    하나 선택되면 창 닫는다.
    var x = document.getElementsByClassName("autocomplete-items");
    for (var i = 0; i < x.length; i++) {
      if (elmnt != x[i] && elmnt != inp) {
      x[i].parentNode.removeChild(x[i]);
    }
  }
}
  
 다른 영역을 클릭하면 리스트가 닫힌다. 
document.addEventListener("click", function (e) {
    closeAllLists(e.target);
});
}*/

var countries = ["Afghanistan","Albania","Algeria","Andorra","Angola","Anguilla",
				"Antigua &amp; Barbuda","Argentina","Armenia","Aruba","Australia",
				"Austria","Azerbaijan","Bahamas","Bahrain","Bangladesh","Barbados",
				"Belarus","Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia",
				"Bosnia &amp; Herzegovina","Botswana","Brazil","British Virgin Islands",
				"Brunei","Bulgaria","Burkina Faso","Burundi","Cambodia","Cameroon",
				"Canada","Cape Verde","Cayman Islands","Central Arfrican Republic",
				"Chad","Chile","China","Colombia","Congo","Cook Islands","Costa Rica",
				"Cote D Ivoire","Croatia","Cuba","Curacao","Cyprus","Czech Republic",
				"Denmark","Djibouti","Dominica","Dominican Republic","Ecuador","Egypt",
				"El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia",
				"Falkland Islands","Faroe Islands","Fiji","Finland","France",
				"French Polynesia","French West Indies","Gabon","Gambia","Georgia",
				"Germany","Ghana","Gibraltar","Greece","Greenland","Grenada","Guam",
				"Guatemala","Guernsey","Guinea","Guinea Bissau","Guyana","Haiti",
				"Honduras","Hong Kong","Hungary","Iceland","India","Indonesia","Iran",
				"Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica","Japan",
				"Jersey","Jordan","Kazakhstan","Kenya","Kiribati","Kosovo","Kuwait",
				"Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya",
				"Liechtenstein","Lithuania","Luxembourg","Macau","Macedonia","Madagascar",
				"Malawi","Malaysia","Maldives","Mali","Malta","Marshall Islands",
				"Mauritania","Mauritius","Mexico","Micronesia","Moldova","Monaco",
				"Mongolia","Montenegro","Montserrat","Morocco","Mozambique","Myanmar",
				"Namibia","Nauro","Nepal","Netherlands","Netherlands Antilles",
				"New Caledonia","New Zealand","Nicaragua","Niger","Nigeria",
				"North Korea","Norway","Oman","Pakistan","Palau","Palestine","Panama",
				"Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal",
				"Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda",
				"Saint Pierre &amp; Miquelon","Samoa","San Marino","Sao Tome and Principe",
				"Saudi Arabia","Senegal","Serbia","Seychelles","Sierra Leone",
				"Singapore","Slovakia","Slovenia","Solomon Islands","Somalia",
				"South Africa","South Korea","South Sudan","Spain","Sri Lanka",
				"St Kitts &amp; Nevis","St Lucia","St Vincent","Sudan","Suriname",
				"Swaziland","Sweden","Switzerland","Syria","Taiwan","Tajikistan",
				"Tanzania","Thailand","Timor L'Este","Togo","Tonga",
				"Trinidad &amp; Tobago","Tunisia","Turkey","Turkmenistan",
				"Turks &amp; Caicos","Tuvalu","Uganda","Ukraine","United Arab Emirates",
				"United Kingdom","United States of America","Uruguay","Uzbekistan",
				"Vanuatu","Vatican City","Venezuela","Vietnam","Virgin Islands (US)",
				"Yemen","Zambia","Zimbabwe"];


// 프로필 사진을 등록하기 전에 올린 사진 미리보기 기능
function readURL(input) {
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		
		reader.onload = function (e) {
			$('#imagePreview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}


// 유효성 체크
function joinCheck()
{
//	   var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
//	   var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
//	   
//	   // 1살부터 99살까지 가입가능.
//	   var ageCheck = RegExp(/^[1-9]|[0-9]/);
//	   
//	   var prePassword = $('#prePassword').val();
//	   var password = $('#password').val();
	   
	
		/*//아이디 체크 ---->    
		if ($('.join [name="id"]').val()=="")   
		{ 
		   alert("아이디를 입력하세요");
		   $('.join [name="id"]').focus();
		//   return false;
		   }
		if(!getCheck.test($('.join [name="id"]').val())){
		  alert("아이디 형식에 맞게 입력해주세요");
		  $('.join [name="id"]').val("");
		  $('.join [name="id"]').focus();
		//  return false;
		}*/
		 
		// 패스워드 체크 <------
		if ($('#password').val()=="")
		{
		   alert("패스워드를 입력하세요");
		   $('#password').focus();
		//   return false;
		   }
		
		if(!getCheck.test($('#password').val()))
		{
	    alert("비밀번호는 영어,숫자,특수문자 조합으로 해주세요.(글자수는 6~12)")
	    $('#password').focus();
	//    return false;
		}
	
	
		if(password !== prePassword)
		{
			alert("비밀번호가 일치되지 않습니다.");
			$('#password').focus();
			
		}
	
	
//		if(!ageCheck.test($('#age').val())) {
//			alert('나이를 정확히 입력해주세요.');
//			$('#age').focus();
//		}

}


$(document).ready(function() {
	
	// 팀 삭제시 팀명 바로 없애주기 위한 ajax
	$("#removeTeamAsLeader").click(function(){
		var teamName = $(this).parent().parent().children('.category').text();
		console.log("teamName = ", teamName);
		
		$.ajax({
			url : '/jamplan/removeTeam.info',
			type : "post",
			data : {
				'teamName': teamName
			},
			contentType : 'application/x-www-form-urlencoded; charsert=utf-8',
			dataType : "text",
			success:function(result){
				$(this).parent().empty();
				
				if(result != '0') {
					location.reload();
				}else {
					alert('팀 삭제에 실패했습니다.');
				}
			},	
			error:function(){
				console.log('팀 삭제 ajax 실패');
			}
		});
	})
	
	// 팀 탈퇴시 바로 팀명 없애주기 위한 ajax
	$("#removeTeamAsMember").click(function(){
		var teamName = $(this).parent().siblings().text();
		console.log(teamName);
		$.ajax({
			url : '/jamplan/signOutTeam.info',
			type : "post",
			data : {
				'id' : $('[name="id"]').val(),
				'teamName': teamName
			},
			contentType : 'application/x-www-form-urlencoded; charsert=utf-8',
			dataType : "text",
			success:function(result){
				if(result != '0') {
					location.reload();
				}else {
					alert('팀 탈퇴에 실패했습니다.');
				}
			},	
			error:function(){
				console.log('팀 탈퇴 ajax 실패');
			}
		});
	})
	
	//팀이미지 등록(모달창에서의 '등록'버튼)
	$('.uploadImageButton').on('click', function(event) {
		var teamImagesId = $(this).parent().siblings().children(".btn").attr("id");
		var teamImages = document.getElementById(teamImagesId).files;
		var teamImageFile = teamImages[0];
		
		var teamName = $(this).parent().siblings().children(".teamName").val();
		var formData = new FormData();

		console.table(teamImageFile);
		formData.append("file", teamImageFile);
		formData.append("team", teamName);
		
		imageUpload(formData);
		/*$.ajax({
			url: '/jamplan/imageUpload.info',
			type: 'POST',
			data: formData,
			enctype: 'multipart/form-data',
			processData: false,
			cache: false,
			async: false,
			contentType: false,
			dataType: "text",
			success: function(result) {
				if(result == 'success') {
					alert('팀이미지를 업로드했습니다.');
				}else {
					alert('팀이미지 등록에 실패했습니다.');
				}
			},
			error: function () {
				console.log('팀이미지 등록 중에 에러가 발생했습니다.');
			}
		});*/
	})
	
	
	// 사용자 이미지 업로드
	// 이미지 파일을 비동기적으로 업로드(정확하게는 파일의 경로만 저장)
	$('#imageUpload').on('click', function(event){
		var teamImages = $('#image').files;
		var teamName  = "none";
		//var teamImageFile = teamImages[0];
		//console.table(teamImageFile);
		var formData = new FormData();
	
		formData.append("file", teamImages);
		formData.append("team", teamName);
		
		imageUpload(formData);
		/*var image = $('#imagePreview').val();
		console.log(image);
		$.ajax({
			url : '/jamplan/imageUpload.info',
			type : "POST",
			data : {
				'image': image
			},
			enctype: 'multipart/form-data',
			dataType : "text",
			success:function(result){
				if(result != '0') {
					alert('프로필 사진이 등록됐습니다.');
				}else {
					alert('다시 시도해주세요.');
				}
			},	
			error:function(){
				console.log('프로필 사진 등록 ajax 실패');
			}
		});*/
	});
	
	function imageUpload(formData){	
		$.ajax({
			url: '/jamplan/imageUpload.info',
			type: 'POST',
			data: formData,
			enctype: 'multipart/form-data',
			processData: false,
			cache: false,
			async: false,
			contentType: false,
			dataType: "text",
			success: function(result) {
				if(result == 'success') {
					alert('사진을 업로드했습니다.');
				}else {
					alert('사진 등록에 실패했습니다.');
				}
			},
			error: function () {
				console.log('팀이미지 등록 중에 에러가 발생했습니다.');
			}
		});
	}


	
	
//	autocomplete(document.getElementById("myNation"), countries);
	
	// 이미지를 찾으면 readURL함수를 통해 프리뷰 이미지를 생성한다.
	$(document).on('change', '#image', function () {
		readURL(this);
	})
	
});

