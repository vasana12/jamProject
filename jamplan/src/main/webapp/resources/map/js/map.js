// 마크, 동선을 그리고 난 후 해당 위치를 array에 저장합니다.
var coordinates= [];
var infoArray = [];
var map = null;
var marker;
var lng;
var lat;
var latlng;
var color;
var z= "0";
var placeName;
var address;
var newPickCount;
var infowindow;
var placeList=[];
var colBTN;
var index;
var bounds;
var confirmPlaceList=[];
var selectDate;
var memberId = $('#memberId').val();
var	planNo = $('#planNo').val();
var selectDateList=[];

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {      
        center: {lat: -33.871, lng: 151.197},      
        zoom: 14
	}); 
	
	color = "000000";
	
	getSelectDate();
}
	
function getSelectDate(){
	$.ajax({
		url:'getSelectDate.mp',
        type:'POST',
        dataType : "json",
        async: false, 	//디폴트: true(비동기통신), 동기 통신을 하고자할때 사용(=Ajax를 통해 서버측에 데이터를 요청하고, 이 데이터의 결과를 모두 수신 받은 다음 단계로 진행하도록 하고 싶을 때 사용)!
        contentType : 'application/x-www-form-urlencoded; charset=utf-8',
        success:function(data){
            
        	$('.ui.dropdown').html("");
                
            $.each(data, function(index, item){
            	if(item.selectDate != null){
            		console.log(item.selectDate);
                    $('.ui.dropdown').append('<option>'+item.selectDate+'</option>');
                    selectDateList.push(item.selectDate);
                }
            });
            selectDate = $('.ui.dropdown > option:first').text();
                                
            getAllPick(selectDate);
        },
        error:function(){ 
           alert("ajax통신실패!!!");
        }
	});
}

   
function getAllPick(selectDate){			//index의 값이 매번 바뀜..@@@
	markersArray = []; 
	infoArray = [];
	placeList=[];
	confirmPlaceList=[];
	coordinates=[];
	$('#placeList').remove();
 
	$(".block").css('background-color', '#f0f5f5');
  
	$.ajax({
		url:'getAllPickList.mp',
		type: 'POST',
		async: false,
		dataType : "json",
		data:{
			"planNo": planNo,
			"selectDate":selectDate},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		success:function(data){   //성공적응답 받아오면 이 함수 자동실행 (data)가 컨트롤러에서 반환된 데이터가 들어있다.
			var input = document.getElementById('searchInput');
			
			map = new google.maps.Map(document.getElementById('map'), {      
    	        center: {lat: -33.871, lng: 151.197},      
    	        zoom: 14
			});  
               
            marker = new google.maps.Marker({
                icon: {
                   url: "http://chart.apis.google.com/chart?chst=d_map_spin&chld=0.7|0|"+ color + "|13|_|" + z ,   //마커크기|기울기|마커색|글자크기|글자기본(_)또는굵게(b)|내용
                     anchor: new google.maps.Point(13,58),
                     labelOrigin: { x: 16, y: 16 }
                },   
                map: map
             });
            
            if(input==null){
               $('#test').append('<input  id="searchInput"  class="controls"  type = "text"  placeholder = "위치 입력" />');
               input = document.getElementById('searchInput');
            }
             
            map.controls[google.maps.ControlPosition.TOP_LEFT].push(input); 
            var autocomplete = new google.maps.places.Autocomplete(input);         //자동 완성 검색 기능
            var geocoder = new google.maps.Geocoder;
            var clickHandler = new ClickEventHandler(map, marker);
            
            autocomplete.addListener('place_changed', function(place){ 
             
            var place = autocomplete.getPlace(); 
                if (!place.geometry) {
                   window.alert("Autocomplete's returned place contains no geometry");
                   return;
                }        
                   
                if (place.geometry.viewport) {
                   map.fitBounds(place.geometry.viewport);
                } else {
                   map.setCenter(place.geometry.location);
                   map.setZoom(14);  
                }
            });
            
            $('.block').empty();
            $('#placeList').empty();
      
            for(var i=0;i<data.length; i++){
               newPickCount = data[i].pickCount;
               color = data[i].userColor;
               
               marker = new google.maps.Marker({
                   icon: {
                      url: "http://chart.apis.google.com/chart?chst=d_map_spin&chld=0.7|0|"+ color + "|13|_|" + newPickCount ,   //마커크기|기울기|마커색|글자크기|글자기본(_)또는굵게(b)|내용
                        anchor: new google.maps.Point(13,58),
                        labelOrigin: { x: 16, y: 16 }
                   },
                   position:{  
                         lat : data[i].lat, 
                         lng : data[i].lng
                    },
                    map: map
                }); 
               
               infowindow = new google.maps.InfoWindow();
               placeName = data[i].placeName;
               
               $('#InfoPlaceName').html(placeName);
               $('#address').html(data[i].address);
               pickList(placeName);                     //각 장소를 pick한 멤버리스트 구하는 함수로 이동
               
               infowindow.setContent($('#infoContent').html());
               
               colBTN=$(".block:nth-child(" + (i+1) + ")");
               if(i>12){
                  $('#vertical_buttons').append('<button class="block" onclick="change1(this)"></button>');
               }
               colBTN.text(placeName);                  //만들어 놓은 버튼(13개)에 장소명 대입
               
               latlng = new google.maps.LatLng(data[i].lat, data[i].lng);    //latlng타입 object    
               colBTN.val(latlng);       //좌표는 숫잔데 value값으로 들어가면 문자
               
               coordinates.push(latlng);
               
               if(data[i].confirm == 1){
                  colBTN.css('background-color', '#b3cccc');
                  placeList.push(placeName);      //확정한 장소 리스트
                  $('#testInput').val(placeList);
               }
               
               google.maps.event.addListener(marker, 'click', makeClickListener(map, marker, infowindow));
            }//for문끝
            
            if(coordinates.length==1){
               map.panTo(coordinates[0]);
               map.setZoom(14);
            }
            else if(coordinates.length>1){
               fitBounds(map,coordinates);
            }
            else{
            	return null;
            }
            
            function makeClickListener(map, marker, infowindow){
               return function(){                  
                  infowindow.open(map, marker);                  
               };
            }
         },
         error:function(){ //응답실패시
               alert("ajax통신실패!!!");
         }
	});
}
   
function fitBounds(map,coordinates){
	bounds = new google.maps.LatLngBounds();
  
	for (var i=0; i < coordinates.length; i++) {
		bounds.extend(coordinates[i]);
	}
	map.fitBounds(bounds);
}   

$(document).ready(function() {
	
	$(document).on("change", ".ui.dropdown", function() {
		selectDate=$(this).find("option:selected").text();
		getAllPick(selectDate);
	});

	$(document).on("click", ".colorBtn", function() {
		color=$(this).val().substring(1,7);
	      
		$.ajax({
			url:'changeColor.mp',
			type:'POST',
			async: false,
			dataType : "json",
			data:{
				"planNo": planNo,
				"id": memberId,
				"userColor": color},
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			success:function(data){
				if(data.res == "OK"){ 
					getAllPick(selectDate);         
				}
				else
				{
					alert("changeColor Fail!!!");
				}
			},
			error:function(){ //응답실패시
				alert("ajax통신실패!!!");
	        }
		});
	});
	
	$(document).on("click","#pickBtn", function(){
		if(color=='000000'){
			$('#color').val(color);
			alert("원하는 마커의 색을 선택해주세요~^^*")
			return null;
		}
		$.ajax({
			url:'insertMember.mp',
			type: 'POST',
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			async: false,
			dataType :"json",
			data:{ 
		        "selectDate":selectDate,
		        "planNo": planNo,
		        "id": memberId,
		        "pickCount":0,
		        "userColor": color,
		        "placeName":$('#InfoPlaceName').html(),
		        "lat": $('#lat').val(),
		        "lng": $('#lng').val(),
		        "address":$('#address').html(),
		        "confirm":0
	        },
	        success:function(retVal){    
	        	if(retVal.res == "OK"){                  
	        		$('#pickCount').val(retVal.pickNum);               
	        		getAllPick(selectDate);
	        	}
	        	else
	        	{
	        		alert("Insert Fail!!!");
	        	}
	        },
	        error:function(){
	        	alert("ajax통신실패!!!");
	        }
		});
	});
	   
	$(document).on("click","#cancelBtn", function(){      
		$.ajax({
			url: 'deleteMember.mp',
			type: 'POST',
			async: false,
			data:{
				"planNo": planNo,
				"selectDate":selectDate,
				"id": memberId,
				"placeName": $('#InfoPlaceName').html()},
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			dataType : "json",
			success:function(retVal){
				if(retVal.res == "OK"){  
					getAllPick(selectDate);
				}
				else
				{
					alert("Delete Fail!!!");
				}
			},
			error:function(){
	         //alert("ajax통신실패!!!");
			}
		});
	});
	   
	$(document).on("click","#confirmBtn", function(){
		if(placeList.length>0){
			//$('#placeList').remove();
			for(var i=0;i<placeList.length;i++){		//왜하는 거더라..
				$('#pickPlaceList').append('<input type="hidden" id="placeList" name="placeList" value="' + placeList[i] + '"/>');      //""해줘야 공백도 다가져옴
				$('#testInput').val(placeList);
			}
		}      
	/*	if(confirmPlaceList.length==0 || $('#testInput').val()==""){		//지금 리스트버튼을 누르지않았거나, 확정된 리스트가 없을 때
			return null;
		}else if(placeList.length==0){
			location.href="resetPlace.mp";
		}*/
		
		if(placeList.length==0){					//확정된 장소가 없음
			if(confirmPlaceList.length==0){			//확정된 장소도 없고 오른쪽 리스트를 누르지도 않았으면 아무일도 없어
				return null;
			}
			else{
//				location.href="resetPlace.mp";		//지금 오른쪽 리스트에서 눌러서 확정된 장소가 없게 만듦->reset버튼눌렀을 때와 같은 상황
				reset();
				return null;
			}
		}
		$.ajax({
			url: 'confirmPlace.mp',
			type: 'POST',
			traditional: true,  // 배열(placeList)을 넘겨주기 위해서 필요..@@
			async: false,		
			data:{
				"planNo": planNo,
				"selectDate":selectDate,
				"placeList": placeList},
			contentType : 'application/x-www-form-urlencoded; charset=utf-8',
			dataType : "json",
			success:function(retVal){
				if(retVal.res == "OK"){ 
					getAllPick(selectDate);
	            }
	            else
	            {
	            	alert("confirmPlace Fail!!!");
	            }
			},
			error:function(){
				alert("ajax통신실패!!!");
			}    
		});
	});
	
	
	$(document).on("click","#resetBtn", function(){    
		reset()
	});
	
	
});//$.ready() 끝

//인포윈도우(마커말풍선)에 pick한 멤버 리스트 보여주는 함수
function pickList(placeName){   
   var output = '';
   $.ajax({
      url:'getPickList.mp',
      async:false, 
      type: 'POST',
      dataType : "json",
      data:{
         "planNo": planNo,
         "selectDate":selectDate,
         "placeName":placeName},
      contentType : 'application/x-www-form-urlencoded; charset=utf-8',
           //false: 동기->비동기적 일처리 방식으로 처리해라(이걸안하면 selectMember가 제대로 출력안됨)
      success:function(data){
         $('#output').empty();
         
         $.each(data, function(index, item){
            output += '<tr>';
            output += '<td>' + item.id + '</td>';
            output += '</tr>';   
         });
         $('#output').append(output);
         
         return output;
      },
      error:function(){
         alert("ajax통신실패!!!");
      }
   });
}

function autoInfo(place){
    var address = '';
    if (place.address_components) {
         address = [
          (place.address_components[0] && place.address_components[0].short_name || ''),
          (place.address_components[1] && place.address_components[1].short_name || ''),
          (place.address_components[2] && place.address_components[2].short_name || '')
        ].join(' ');
      }
    
    place = $('#InfoPlaceName').html(place.name);
    addNameress = $('#address').html(address);
    $('#output').empty();
}

var ClickEventHandler = function(map, marker) {         //prototype: 자바스크립트의 클래스같은거
     this.map = map;
     this.marker = marker;
     this.placesService = new google.maps.places.PlacesService(map);
     this.infoWindow = new google.maps.InfoWindow;
    
     //Listen for clicks on the map.
     this.map.addListener('click', this.handleClick.bind(this));   //.bind() : event가 발생하면 실행될 함수를 지정한다. (이미 생성된 DOM에 대하여)
}     

ClickEventHandler.prototype.handleClick = function(event) {       //메소드 만들어줌
   // If the event has a placeId, use it.
    if (event.placeId) {
       console.log('You clicked on place:' + event.placeId);

       event.stop();      //기본 인포창 안뜨게 하기
       
       $('#lat').val(event.latLng.lat());
       $('#lng').val(event.latLng.lng());
       
       this.getPlaceInformation(event.placeId);
    }
    else{
       console.log('You clicked on: ' + event.latLng);          
    }
};
  
ClickEventHandler.prototype.getPlaceInformation = function(placeId) {
    var me = this;
    me.placesService.getDetails({placeId: placeId}, function(place, status) {
       if (status === 'OK') {
          me.infoWindow.close();
          me.marker.setPosition(place.geometry.location);
          me.infoWindow.setPosition(place.geometry.location);
          
          autoInfo(place);
          
          me.infoWindow.setContent($('#infoContent').html());
          me.infoWindow.open(me.map, me.marker);

       }
   });
};

//지도오른쪽 리스트 버튼을 눌렀을 때
//팀장인지 아닌지 구별해줘야해
function change1(obj){
	placeName=$(obj).text();
   
	if(placeName == ""){      
		return null;
	}
   
	index = $.inArray(placeName, placeList);   //pickList에 placeName가 몇번째 위치에 있는지 알려줌.(-1)은 배열에 없다는 뜻
	if(index<0){   //리스트에서 선택 
		obj.style.background = '#b3cccc';
		placeList.push(placeName);            //확정한 모든 장소명이 담긴 배열
		confirmPlaceList.push(placeName);      //지금 선택한 장소명이 담긴 배열
      
		str=$(obj).val();
		var res= str.replace('(',' ').replace(',',' ').replace(')',' ').split(' ');      //문자열 쪼개서 좌표만 추출
		var center = new google.maps.LatLng(parseFloat(res[1]),parseFloat(res[3]));
		map.panTo(center);
		map.setZoom(14);
	}
	else{
		obj.style.background = '#f0f5f5';      //선택 해제
      
		placeList.splice(index,1);            //확정한 리스트에서 삭제
      
	}
	confirmPlaceList.push(placeName);
}

function reset(){ 
	$.ajax({
		url: 'resetPlace.mp',
		type: 'POST',
		async:false,
		data:{
			"planNo": planNo,
			"selectDate": selectDate},
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType : "json",
		success:function(retVal){
			if(retVal.res == "OK"){ 
				getAllPick(selectDate);
            }
            else
            {
            	alert("resetPlace Fail!!!");
            }
		},
		error:function(){
			alert("ajax통신실패!!!");
		}    
	});
}

