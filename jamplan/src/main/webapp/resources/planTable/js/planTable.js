var currentPage;
var data =[];
var totalCnt;
var dataPerPage = 4;	// 한 페이지에 나타낼 데이터 수
var pageCount = 5;   	// 한 화면에 나타낼 페이지 수
var pageGroup;			
var totalPage;
var last;
var first;
var next;
var prev;
var html;

$(document).ready(function() {
	
	 getPlanTable();
	
	//테이블에 메모(여행계획) 저장눌렀을 때
	$(document).on("click","#saveBtn", function(){
		var memoCnt = $('[name="memo"]');
		var memoList = [];
		var planSeqList = [];
        
		
		if(memoCnt.length == 0){
			return null;
		}
		
		for(var i=4*(currentPage-1); i< 4*(currentPage-1)+4; i++){
			memoList.push($('#memo'+ i).val());
			planSeqList.push($('#planSeq'+ i).val());
		}
		
		$.ajax({
			url : 'savePlanTable.plan',
			type : 'POST',
			dataType: "json",
			data : {
				"memoList" : memoList,
				"planSeqList" : planSeqList
			},
			traditional: true,	// 배열(placeList)을 넘겨주기 위해서 필요..@@
			async: false,
	        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){
				
				
				if(data.res == "OK"){
					alert("여행계획이 저장되었습니다.")
				
				}
				else{
					alert("여행계획 저장 실패..")
				}
				
			},
			error:function() {
				alert('ajax통신실패!!!');
			}
			
		});		//ajax 끝
		getPlanTable();
	});
	
	
	//플랜테이블 데이터 가져오기
	function getPlanTable(){
		$.ajax({
			url : 'planTable.plan',
			type : 'POST',
			dataType: "json",
			async: false,
	        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){
				var isOpenput = '';
				
				$('#isOpenput').empty();
				
				if (data[0].isOpen == 1) {
	               isOpenput += '<button type="button" class="btn btn-danger isOpenButton" id="open">공개</button>';
	               isOpenput += '<button type="button" class="btn btn-light isOpenButton" id="notOpen">비공개</button>';
	               $('#isOpenput').append(isOpenput);
	            }
	            else {
	               isOpenput += '<button type="button" class="btn btn-light isOpenButton" id="open">공개</button>';
	               isOpenput += '<button type="button" class="btn btn-danger isOpenButton" id="notOpen">비공개</button>';
	               $('#isOpenput').append(isOpenput);
	            }
				
				if(data == null){
					return null;
				}
				$('#ptbody > tr').remove();
				
				//페이징 처리
				totalCnt = data.length;    		// 총 데이터 수
				   		   		
				
				currentPage = parseInt($('#currentPage').val());
				pgNum(currentPage);      
			
				$("#paging a").click(function(){
			        var $item = $(this);
			        var $id = $item.attr("id");
			        var selectedPage;

			        if($id == "next" ){							// '>'눌렀을 때
			        	if(currentPage != last){				// 현재 페이지가 마지막 페이지가 아닐 때
			        		selectedPage = currentPage + 1;     // 페이지  	
			        		
			        	}else if(currentPage < totalPage){		//현재 페이지가 마지막 페이지이지만 전체의 마지막페이지가 아닐때
			        		selectedPage = currentPage + 1;
			        		$('#currentPage').val(selectedPage);
			        		currentPage = parseInt($('#currentPage').val());
			        		getPlanTable();
			        		$("#"+currentPage).click();
			        	}
			        	else{
			        		return null;
			        	}
			        	
			        }else if ($id == "prev"){  					// '<'눌렀을 때
			        	if(currentPage != first){				// 현재 페이지가 첫번째 페이지가 아닐 때
			        		selectedPage = currentPage - 1;
			        		
			        	}else if(currentPage > 1){				//현재 페이지가 첫번째 페이지이지만 전체의 첫번째 페이지가 아닐때
			        		selectedPage = currentPage - 1;
			        		$('#currentPage').val(selectedPage);
			        		currentPage = parseInt($('#currentPage').val());
			        		getPlanTable();
			        		$("#"+currentPage).click();
			        	}
			        	else{
			        		return null;
			        	}
			        }
			        else{
			        	selectedPage = $item.text();
			        	
			        }
			        $('#currentPage').val(selectedPage);
		        	currentPage = parseInt($('#currentPage').val());
		        	$("#paging a").css("color", "black");
		        	$('[id="' + parseInt(selectedPage) +'"]').css({"text-decoration":"none", "color":"#337ab7"});    // 현재 페이지 표시
			        
			        paging(totalCnt, dataPerPage, pageCount, selectedPage);
			        
			    });		//$("#paging a").click(function() 끝
				$("#"+currentPage).click();
				
				function paging(totalCnt, dataPerpage, pageCount, currentPage){ 	//불러온 데이터 뿌려주기
			    	//1pg : 0~3 
					//2pg : 4~7      i=currentPage, 데이터 뽑을 seq범위 : 4(i-1)~4(i-1)+3 
					//3pg : 8~11
					//4pg : 12~15
			    	$('#ptbody').empty();
			        for(var i=4*(currentPage-1); i< 4*(currentPage-1)+4; i++){
			        	
			        	if( i > totalCnt-1){
			        		return null;
			        	}
			        	
						if (data[i].memo == null || data[i].memo.length == 0){
							data[i].memo = "";
						}
						
						var planput = '';
						planput += '<tr style="height: 200px;">';
						planput += '<td>' + data[i].selectDate + '</td>';
						planput += '<td>' + data[i].placeName + '</td>';
						planput += '<td><textarea class="form-control" name="memo" id="memo' + data[i].planSeq + '" placeholder="여행계획을 작성해보세요!" rows="7" cols="30" >'+ data[i].memo +'</textarea></td>';
						planput += '<td style="visibility:hidden;"><input type="text" hidden="hidden" id="planSeq'+data[i].planSeq+'" value="'+data[i].planSeq+'"></td>'
						planput += '</tr>';
						$('#ptbody').append(planput);
						
					}
			    }
			},
			error:function() {
				//alert('ajax통신실패??!!');		
				
			}
		});
	}	//getPlanTable() 끝
});	  // $(document).ready 함수 끝
	


function pgNum(currentPage){
    totalPage = Math.ceil(totalCnt/dataPerPage);     //총 페이지 수   11/4=2.xx =>3
      pageGroup = Math.ceil(currentPage/pageCount);    //페이지 그룹  6/5=1.xx =>2
    
      
      last = pageGroup * pageCount;    // 화면에 보여질 마지막 페이지 번호, 2*5 = 10
      if(last > totalPage){           // 10 > 3 => last = 3
          last = totalPage;           
          //first = last - pageCount;
      }
      first = last - (pageCount-1);    // 화면에 보여질 첫번째 페이지 번호, 3 - 4 = -1
      
      if (first < 1){
         first = 1;
      }
      next = last+1;                
      prev = first-1;
     
      if(prev < 1){
         prev = 1;
      }
      if(next > totalPage){
         next = last;
      }
      html = "";
      html += "<a href=# id='prev'><</a>";
      
      for(var p=first; p <= last; p++){
          html += "<a href='#' id=" + p + ">&nbsp;" + p + "&nbsp;</a>";
      }
      
      html += "<a href=# id='next'>></a>";
      $("#paging").html(html);    // 페이지 목록 생성
      $("#paging a").css("font-size", "23px");
      $('[id="' + parseInt(currentPage) +'"]').css({"text-decoration":"none", "color":"#337ab7"});
 }


//isOpen Update
$(document).on("click", ".isOpenButton", function(){   
   console.log(document.getElementById('open').innerHTML);
   console.log($('#notOpen').html());
   /* 중복체크 기능만들기.
    * alert('test');
   console.log(this.innerHTML.length);*/
   
   var params;
   if (this.innerHTML == '공개') {
      params = 1;
   }
   else {
      params = 0;
   }
   var isOpenStatus = params;
   /*alert(isOpenStatus);*/
   
   $.ajax({
      url : '/jamplan/updateIsOpen.plan',
      type : 'POST',
      dataType : "json",
      contentType : 'application/x-www-form-urlencoded; charset=utf-8',
      data : {"isOpen" : params},
      
      success:function(data){
         $('#isOpenput').empty();
         var output = ""
         console.log(data);
         if (isOpenStatus == 1) {
          
            output += '<button type="button" class="btn btn-danger isOpenButton" id="open">공개</button>';
            output += '<button type="button" class="btn btn-light isOpenButton" id="notOpen">비공개</button>';
            $('#isOpenput').append(output);
            alert('공개 설정 완료!');
         }
         else {
            output += '<button type="button" class="btn btn-light isOpenButton" id="open">공개</button>';
            output += '<button type="button" class="btn btn-danger isOpenButton" id="notOpen">비공개</button>';
            $('#isOpenput').append(output);
            alert('비공개 설정 완료!');
         }
      
      },
      error:function() {
         alert('ajax 통신실패!');
      }
      
   });
});
    
