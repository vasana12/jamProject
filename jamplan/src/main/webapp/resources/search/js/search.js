/**
 * 
 */
var userId;

function selectData(pageNum){
	$.ajax({ 
		url:'/jamplan/getPlanList.search',
		type:'POST',
		data: {
			"page":pageNum,
			"topic" : $('.dropdown-toggle').html(),
			"keyword" : $('.form-control').val(),
			"selectDate" : $('input[name=selectDate]').val(), //jsp input 히든값들
			"readCount" : $('input[name=readCount]').val(),
			"goodCount" : $('input[name=goodCount]').val(),
			"planName" : $('input[name=planName]').val()     
			},
		dataType:"json",
		contentType:'application/x-www-form-urlencoded; charset=utf-8',
		success:function(data){ 
			$('#output').empty();
			$('#pageList').empty();
	        var pageput = '';
	        //데이터 뿌리기
	         $.each(data.list, function(index, item){ 
	            var output = '';
	            output += '<div style="width:530px; margin: 0 40px;">';
	            output += '<div class="card cardMargin" style="width : 100%" >';
	           /* output += `<div id="planNo"><img src='image/${item.teamImage}' style="width:100%; height:60vh;"/></div>`;*/
	            output += '<div><img src="./resources/search/images/' + item.planNo + '.jpg" style="width:100%; height:60vh;"  />' + '</a></div>';
	            output += '<div class="card-body">';
	            output += '<span>' + item.selectDate + '</span>' + '&nbsp' + '&nbsp';
	            output += '<span>' + item.planName + '</span>' + '&nbsp' + '&nbsp';
	         	output += '<button class="btn">' + item.readCount + '</button>' + '&nbsp';
	         	output += '<span class="goodCount"><h5><i class="fas fa-heart " style= "color : #E75450;"  >'+'&nbsp' + item.goodCount + '</i><h5></span>' + '&nbsp' + '&nbsp';
	         	output += '<div class="container">'
	         	output += '<button id="getPlanTable'+item.planNo+'" class="btn btn-primary getPlanTable" type="button" data-toggle="modal" data-target="#myModal" onclick="planTable(this)" value="'+ item.planNo +'">일정보기'+'</button>';
	         	output += '</div>';
	         	output += '</div>';
	         	output += '</div>';
	         	output += '</div>';
	            $('#output').append(output);
	         });
	         
	         //페이징처리
	         var pageVO = data.paging;
	         console.table(pageVO);
	         pageput += "<div class='text-center'>";
	         pageput += "<ul class='pagination'>";
	         
	         if(pageVO.startPage <=1){
	            pageput += "<li><a href='#'>«</a></li>&nbsp;";
	         }else{
	            pageput += "<li><a class='"+ (pageVO.startPage-1) + "' >«</a></li>&nbsp;";
	         }
	         
	         for(var i=pageVO.startPage; i<=pageVO.endPage; i++){
	            if(i == pageVO.page){
	               pageput +="<strong><li class='acitve'><a>" + i + "</a></li></strong>";
	            }else{
	               pageput +="<li><a href='#'>" + i + "</a></li>&nbsp;";
	            }
	         }
	         
	         if(pageVO.endPage >= pageVO.maxPage){
	            pageput += "<li><a href='#'>»</a></li>";
	         }else{
	            pageput += "<li><a class='" + (pageVO.endPage+1) +"' >»</a></li>";   
	         }
	         
	         pageput += "</ul></div>";
	         
	         $('#pageList').append(pageput);
		},
		error:function(){
			alert("찾으시는 페이지가 없습니다!");
		}
	});
}

function planTable(obj){
	var thisPlan = $(obj);
	var planNo = $(obj).val();   
	$('.sendPlanNo').attr('id',planNo);
	   
	// 동적으로 여러 태그가 생성된 경우라면 이런식으로 클릭된 객체를 this 키워드를 이용해서 잡아올 수 있다.
	$.ajax({
		url:'/jamplan/getPlanTable.search',
		type:'POST',
		dataType: "json",
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		data : {'planNo' : planNo},
		success:function(data) {
			var output = '';
			$('#commentTable').remove();
			$('#modalTB').remove();
			
			output += '<div id="modalTB">'           
			output += '<table class="table table-hover" style="border-bottom: 1px solid #e9ecef; table-layout:fixed; text-align:center;">'            		
			output +='</table></div>'
			$('.modal-body').append(output);	
				
			$('#cmt>table').html('');
			$('#cmt>table').css('margin-bottom','0px')
			$('#cmt>table').append('<tr><td style="text-align:right;"><a href="#" onclick="post('+planNo+')">댓글보기</a><tr><td>')
			
			output = '<thead><tr><th style="width:120px">Date</th><th>PlaceName</th></tr></thead>'
			output += '<tbody id=modalOutput></tbody>'	

			$.each(data, function(index, item){ 
				output += '<tr>';
				output += '<td>'+item.selectDate+'</td>';
				output += '<td>'+item.placeName+'</td>';
				output += '</tr>';
				$('#modalTB>table').html(output);
			});
		},
		error:function(){
			console.log("planTable _ ajax통신 실패!!!");
		}
	});   
	
	//좋아요 했는지안했는지 체크기능
    likeGet(planNo);
    
    //조회수
    $.ajax({
    	url:'/jamplan/schedule.search',
    	async: false,
    	type:'POST',
    	dataType: "text",
    	contentType : 'application/x-www-form-urlencoded; charset=utf-8',
    	data : {'planNo' : planNo},
    	success: function(data) {
			if (data == '1') {
				$(thisPlan).empty();
			}
    	},
    	error:function(){
    		console.log("조회수 변경 실패!");
    	}
    });
    selectData($("#pageList li.acitve").text());
}      

//좋아요체크기능!~!! 모달창 접근시 유저가 좋아요를 체크한지 안한지 체크하는 기능
function likeGet(planNo) {
   /* 유저가 하트를 체크했는지 안했는지 체크해주는 코드 써주기 : if~ */
   var likeput = '';
   var id = $('#likeId').val();
   $.ajax({
      url : '/jamplan/heartCheck.search',
      type : 'POST',
      dataType : "json",
      contentType : 'application/x-www-form-urlencoded; charset=utf-8',
      data : {'planNo' : planNo,
              "id" : id},
      success:function(data){
         if(data.likeYn == 'N'){
            likeput = '<h3><a href="javascript:likeFunc('+planNo+');"><i class="far fa-heart" id="noneHeart" style= "color : #E75450;" ></i></a></h3>';
            $('#likeStatus').val('N');
            
         }
         else{
            likeput = '<h3><a href="javascript:likeFunc('+planNo+');"><i class="fas fa-heart " id="fullHeart" style= "color : #E75450;"></i></a></h3>';
            $('#likeStatus').val('Y');
            
         }   
            $('#likeput').html(likeput);
            
      },
      error:function(){
         console.log("likeGet _ ajax통신 실패!!!");
      }
   });
   
}


//클릭시 좋아요 선택,취소
function likeFunc(planNo) {
   var id = $('#likeId').val();
   var likeStatus = $('#likeStatus').val();
   var likeput = '';
   var updateStatus ='';
   if(likeStatus=='Y'){
      updateStatus='N'
   }else{
      updateStatus='Y'
      
   }
   $.ajax({
      url : '/jamplan/likeUpdate.search',
      type : 'POST',
      dataType: "json",
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
      data : {
         "id" : id,
         "planNo" : planNo,
         "likeYn" : updateStatus
      },
      success:function(data){
         
         if('N' == data.likeYn){
            likeput = '<h3><a href="javascript:likeFunc('+planNo+');"><i class="far fa-heart" id="noneHeart" style= "color : #E75450;" ></i></a></h3>';
            $('#likeStatus').val('N');
         }
         else{
            likeput = '<h3><a href="javascript:likeFunc('+planNo+');"><i class="fas fa-heart " id="fullHeart" style= "color : #E75450;"  ></i></a></h3>';
            $('#likeStatus').val('Y');
         }
            console.log("likeput:" + likeput);
            $('#likeput').html(likeput);
            //사람이많을때 문제??
            selectData($("#pageList li.acitve").text());
      },
      error:function(){
         console.log("likeFunc _ ajax통신 실패!!!");
      }
   });
   
}   


//모달창 댓글 리스트 보여주기
function post(planNo){
	$('#modalTB table').html('')
	
	$.ajax({
		url : '/jamplan/getComments.search',
		type : 'POST',
		data : {'planNo': planNo},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType : "json",
		success : function (data) {
			var html = ''
			
			$('#commentTable').remove();
			$('#modalTB').remove()
			$.each(data, function(index, item){ 
				if(item.content == null){
					item.content='';
				}
				
				html += '<tr id="delTD" style="height:30px;">'
				html +=	'<td style="word-break:break-all;"><strong>' + item.id + "</strong>  " + item.content + '<a href="#" style="margin-left: 15px;" id="del" onclick="deleteComment(' + planNo + ',' + item.cnt + ')">x</a></td>'	
				
			});
			$('.modal-body').append('<div id="commentTable" ><table style="text-align: left;"></table></div>');
			$('#commentTable>table').css('table-layout','fixed')
			$('#commentTable>table').append(html);
			
			if(data.length == 0)
				$('#commentTable>table').append('<p>댓글이 없습니다</p>');
			
			$('#cmt>table').html('')	
			$('#cmt>table').css('margin-bottom','10px')
			html = '<tbody><tr><td colspan="2"><a href=# class="seePlan" onclick="planTable(this)">일정보기</a></td></tr>'
			html += '<tr style="width: 100%;">'
			html +=	'<td style="width: 100%;"><input type="text" id="text_id" placeholder="댓글 달기.."></td>'
			html += '<td><button type="button" id="commentBtn" class="btn btn-primary btn-block" onclick="makeComment('+planNo+')">댓글 달기</button></td>'
			html +=	'</tr></tbody>'	
			
			$('#cmt>table').html(html)
			$('.seePlan').val(planNo);
			
			$('#text_id').val('') 
		},
		error:function(){
			   console.log("post _ ajax통신 실패!!!");
		}
	});
}	

// 댓글 달기(insert)
function makeComment(planNo){
	var content = $('#text_id').val(); 
	userId = $('#likeId').val();
		
	if(userId == 'null' || userId == ''){
		alert('로그인 해주세요');
		return null;
	}
	
	$.ajax({
		url : '/jamplan/insertComment.search',
		type : 'POST',
		data : {'planNo': planNo,
				'id': userId,
				'content': content},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType : "text",
		success : function(retVal){  
			if(retVal == '1')
				post(planNo);
	   			
		},
		error:function(){
			console.log("makeComment _ ajax통신 실패!!!");
		}
	});
}
   
// 댓글 삭제(delete)
function deleteComment(planNo, cnt){
	
	$.ajax({
		url : '/jamplan/deleteComment.search',
		type : 'POST',
		data : {'planNo': planNo,
				'cnt': cnt,
				'id' : userId},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType : "text",
		success : function(retVal){  
			if(retVal == '0')
				return null;
			
			if(retVal == '1'){
				alert('댓글이 삭제되었습니다.')
				post(planNo);
			}
			
		},
		error:function(){
			console.log("deleteComment _ ajax통신 실패!!!");
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

   
   
$(document).ready(function(){ 		/* (document).ready는 html문서가 로딩이완료되면 자동으로 실행 */
   //목록
   selectData(1);
  
   //페이지번호를 클릭했을때의 값을 pageNum으로 설정하는 기능
   $("#pageList").on("click", ".pagination li a", function(event){
      event.preventDefault(); 
      var pageNum = $(this).text();
      if(pageNum == "«" || pageNum == "»"){
         pageNum = $(this).attr('class');
      }
      selectData(pageNum);
   });
   
   $(document).on('click', '#myRoomButton', function(){
      let form = makeFormElement({
         method: 'post',
         action: '/jamplan/myroom.do',
         name: info
      });
      form.submit();
   });
 
   
	$(document).on('click', '#infoButton', function(){   
      let form = makeFormElement({
         method: 'post',
         action: '/jamplan/myroom.do',
         name: info
      });
      form.submit();
   });
   
	
	$(document).on('click', '#logoutButton', function(){  
      let form = makeFormElement({
         method: 'post',
         action: '/jamplan/logout.do',
      });
      form.submit();
   });
   
	//검색기능
    $('.tag').click(function (){ 
       if ($('.form-control').val() != null || $('.form-control').val() != ""){
          selectData(1);
       }
    });
   
      
     //클릭검색
    $('.clk').click(function(event){
         var clickevent = '';
          var params = {};
          var clickevent = $(this).attr('id');
            switch(clickevent) {
              case 'newDateClick':
                 $('input[name=selectDate]').val("a");
                 $('input[name=readCount]').val("b");
              $('input[name=goodCount]').val("b");
              $('input[name=planName]').val("b");
                  break;
              case 'readCountClick':
                 $('input[name=readCount]').val("a");
                 $('input[name=selectDate]').val("b");
              $('input[name=goodCount]').val("b");
              $('input[name=planName]').val("b");
                  break;
              case 'goodCountClick':
                 $('input[name=goodCount]').val("a");
                 $('input[name=selectDate]').val("b");
                 $('input[name=readCount]').val("b");
              $('input[name=planName]').val("b");
                  break;
              case 'dateClick':
                 $('input[name=planName]').val("a");
                 $('input[name=selectDate]').val("b");
                 $('input[name=readCount]').val("b");
              $('input[name=goodCount]').val("b");
                  break;
            }
            selectData(1);
        });
	
   $('.li1').click(function(){
       var name = $(this).html();
       console.log(name);
       $('.dropdown-toggle').html(name);
       
       if (name == '날짜') {
          $('#dateCheck').css('display', 'block');
       }
       else {
          $('#dateCheck').css('display', 'none');
       }
   });
    
   
   $(document).on("click", ".sendPlanNo", function(){
	   
      var sendPlanNo = $('.sendPlanNo').attr('id');
      alert(sendPlanNo);
	      if(userId == 'null' || userId == ''){
	  		alert('로그인 해주세요');
	  		return null;
	  	  }
      
      $.ajax({
         url : '/jamplan/receivePlanNo.do',
         type : 'POST',
         data : {'planNo' : sendPlanNo},
         dataType : 'json',
         contentType: 'application/x-www-form-urlencoded;charset=utf-8',
         success : function(data){
        	 alert("동행 신청 메세지를 보냈습니다.")
         },
         error: function(data){
        	 console.table(data.res);
         }
      });
   });
     
}); 	//document.ready끝 


