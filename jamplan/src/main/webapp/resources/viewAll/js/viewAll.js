/**
 * 
 */
$(document).ready(function() {
		
		
		$.ajax({
			url : '/jamplan/moveViewAll.view',
			type : 'POST',
			dataType: "json",
			async: false,
	        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){
				/*$('#planput').empty();
				$('#saveput').empty();
				var put = '';
				var saveput = '';
					saveput += '<br><br>';
					saveput += '<a href="javascript:savePlanTable();"><button class="btn btn-primary pull-right" >저장</button></a>';
					$('#saveput').append(saveput);*/
					
				$.each(data, function(index, item){
					
					/* $('#planput').empty(); */
					
					var viewput = '';
					viewput += '<table class="table">';
					viewput += '<thead>';
					viewput += '<th>날짜 </th>';
					viewput += '<th>장소</th>';
					viewput += '<th>일정</th>';
					viewput += '</thead>';
					viewput += '<tbody>';
					viewput += '<tr>';
					viewput += '<td>' + item.selectDate + '</td>';
					viewput += '<td>' + item.placeName + '</td>';
					viewput += '<td class="form-control" name="memo" id="memo' + index + '" placeholder="여행계획을 작성해보세요!" rows="5" cols="30" >'+ item.memo +'</td>';
					/*viewput += '<td><input type="text" hidden="hidden" id="planSeq'+index+'" value="'+item.planSeq+'"></td>'*/
					viewput += '</tr>';
					viewput += '</tbody>';
					viewput += '</table>';
					
					console.log("viewput" + viewput);
					$('#viewput').append(viewput);
				});
			},
			error:function() {
				alert('ajax통신실패!!!?');
			}
		});
	

});

/*// savePlanTable : 저장
function savePlanTable(){
	var memoCnt = $('[name="memo"]');
	var params = {};
	for(var i=0; i<memoCnt.length; i++){
		params = {"memo" : $('#memo'+ i).val(), "planSeq" : $('#planSeq'+ i).val()};
		console.log(params);
		$.ajax({
			url : '/jamplan/savePlanTable.plan',
			type : 'POST',
			async: false,
			dataType: "json",
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			data : params,
			success:function(data) {
				
				 $.each(data, function(index, item){
						$('#updateput').empty();
						var updateput = '';
						updateput += '<table class="table">';
						updateput += '<thead>';
						updateput += '<th>날짜 </th>';
						updateput += '<th>장소</th>';
						updateput += '<th>일정</th>';
						updateput += '</thead>';
						updateput += '<tbody>';
						updateput += '<tr>';
						updateput += '<td>' + item.calendar + '</td>';
						updateput += '<td>' + item.map + '</td>';
						updateput += '<td><textarea class="form-control" id="memo" placeholder="여행계획을 작성해보세요!" rows="5" cols="30" >'+ item.memo +'</textarea></td>';
						updateput += '</tr>';
						updateput += '</tbody>';
						updateput += '</table>';
						
						
						console.log("updateput" + updateput);
						$('#updateput').append(updateput);
						
					}); 
			},
			error:function() {
				alert('ajax통신실패!!!');
			}
		});
	}
	alert('저장성공!');
	
}*/