<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous"></script>
</head>
<body>
JSP호출하자
</body>
<script>

/*로그인 후 데이터 가져오기
 $.ajax({
    type:"POST",
    beforeSend: function (request)
    {
        request.setRequestHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTU1MDYxNzM4NTM0LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1lbWJlcklkeCI6MSwibWVtYmVySWQiOiJ0ZXN0MSIsIm1lbWJlck5hbWUiOiLthYzsiqTtirgxIiwibWVtYmVyUHciOiIxMjM0IiwidXBkRHRtIjoiMjAxOTA0MDMxNTUwMDEwMSIsInVzZXJUeXBlQ29kZSI6Ik5PUk1BTCIsInN0YXR1c0NvZGUiOiJFTUFJTF9DT05GSVJNIn19.gwuHd5CpOMnpWzp8gkqqVNKW0zSZK1ye3NMbcdE8-z0");
    },
    url: "/rest/bbs/list",
	dataType: "json",
	contentType: 'application/json',
    data :JSON.stringify({
     	"offset" : 1
     	, "limit" : 10
     	, "scope" : "all"
     	,"keyword" : "00"
    }),
    processData: false,
    success: function(data) {
		console.log(data);
    }
});
*/
//파일업로드
$.ajax({
    type:"POST",
    beforeSend: function (request)
    {
        request.setRequestHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNTU1MDYxNzM4NTM0LCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwibWVtYmVyIjp7Im1lbWJlcklkeCI6MSwibWVtYmVySWQiOiJ0ZXN0MSIsIm1lbWJlck5hbWUiOiLthYzsiqTtirgxIiwibWVtYmVyUHciOiIxMjM0IiwidXBkRHRtIjoiMjAxOTA0MDMxNTUwMDEwMSIsInVzZXJUeXBlQ29kZSI6Ik5PUk1BTCIsInN0YXR1c0NvZGUiOiJFTUFJTF9DT05GSVJNIn19.gwuHd5CpOMnpWzp8gkqqVNKW0zSZK1ye3NMbcdE8-z0");
    },
    url: "/rest/bbs/list",
	dataType: "json",
	contentType: 'application/json',
    data :JSON.stringify({
     	"offset" : 1
     	, "limit" : 10
     	, "scope" : "all"
     	,"keyword" : "00"
    }),
    processData: false,
    success: function(data) {
		console.log(data);
    }
});
 
 //수동입력
 //var inData = { "memberId" : "test1" }
 //변환입력
 //var inData = $('#폼아디').serialize();
/* id 체크 
$.ajax({
	type: 'POST',
    url: "/rest/login/check",
	//dataType: "json",
	//contentType: 'application/json',
    //data : JSON.stringify({"memberId" : "test1"}),
	data : inData,
    success: function(data) {
    	console.log(data);
    },
    error: function(jqXHR, textStatus, errorThrown) { alert(jqXHR.responseText); }
});
*/

 /*회원가입 및 메일 인증
 $.ajax({
	type:"POST",
   	url: "/rest/login/insert",
	dataType: "json",
	contentType: 'application/json',
     data : JSON.stringify({
     	"memberId" : "test2019"
     	, "memberPw" : "1234"
     	, "memberName" : "이문"
     	,"email" : "8timeterran@naver.com"
     }),
     success: function(data) {
    	 console.log(data);
     }
 }); 
 */
 
 //로그인 
 
 /*
 $.ajax({
     type:"POST",
     url: "/rest/login/login",
     data : {
     	"memberId":"test2019"
     	, "memberPw":"1234"
     },
     success: function(data) {
    	 console.log(data);
     }
 });
 */
 
/*최초딩
	init();
	$loadMore();
   	//최초 로딩
   	var $loadMore = function(){
   		commonCallAjax("/event/getSelectEventList.do", "post", {start: start, end: end, searchDt: $("#searchDt").val()}, appendList, errorCallback);
   	}
 */  	
   	/* 스크롤 맨끝으로 이동시 alert 창 추가
   	$(window).scroll(function() {
   		var scrollHeight = $(document).outerHeight();
   		var scrollWindowHeight = $(window).outerHeight();
   		var scrollPosition = $(window).scrollTop();

   		if (scrollHeight <= (scrollWindowHeight + scrollPosition)) {
   			
   			if(!isMaxData){
   				commonCallAjax("/event/getSelectEventList.do", "post",{start: start, end: end, searchDt: $("#searchDt").val()}, appendList, errorCallback);
   			}
    			
   			scrollHeight = $(document).outerHeight();

   		}
   	});
   	*/
   	
   	/*페이징 init함수   	var pageInit			= 10;
   	var startDefault		= 1;
   	var endDefault			= pageInit;
   	var isMaxDataDefault	= false;
   	var start				= 0;
   	var end					= 0;
   	var isMaxData			= false;
   	
   	function init(){
   		start 		= startDefault;
   		end 		= endDefault;
   		isMaxData 	= isMaxDataDefault;
   	}
*/ 
/*list ajax
	function appendList(data) {
		
		var innerHtmlTxt = "";
		
		if(data.dataList.length > 0){
			
			$.each(data.dataList, function(key, value) {
				
	   			console.log(value.STATUS);
				
					innerHtmlTxt += "<li ";
					if(value.STATUS == "종료"){
						innerHtmlTxt += "class='done' ";
					}
					innerHtmlTxt += ">";
					innerHtmlTxt += 	"<figure class='figure'>";
					innerHtmlTxt += 		"<a href='/event/"+value.EVENT_PAGE+"EventDetail.do?eventKey="+value.EVENT_KEY+"'>";
				innerHtmlTxt += 			"<div class='photo'>";
					innerHtmlTxt += 				"<img src='/file/getImage.do?fileSeq="+value.FILE_SEQ+"' alt=''>";
					innerHtmlTxt += 			"</div>";
					innerHtmlTxt += 			"<figcaption class='figcaption'>";
					innerHtmlTxt += 				"<strong class='title'>"+value.TITLE+"</strong>";
					innerHtmlTxt += 				"<p>"+value.START_DTM+" ~ "+value.END_DTM+"</p>";
					innerHtmlTxt += 				"<span ";
					if(value.STATUS == "종료"){
						innerHtmlTxt	 += 				"class='labels done' ";
					}else if(value.STATUS == "준비"){
						innerHtmlTxt	 += 				"class='labels loading2' ";
					}else{
						innerHtmlTxt	 += 				"class='labels doing' ";
					}
					innerHtmlTxt += 				">"+value.STATUS;
					innerHtmlTxt += 				"</span>";
					innerHtmlTxt += 			"</figcaption>";
					innerHtmlTxt += 		"</a>";
					innerHtmlTxt += 	"</figure>";
					innerHtmlTxt += "</li>";
					
			})
			
			if(data.dataList.length < pageInit || data.dataCnt <= pageInit) {  
				isMaxData = true;
			} else {
				start += pageInit;
				end += pageInit;
			}
			
		} else {
			
			isMaxData = true;
			if(start == 1) {
				
  				innerHtmlTxt = "<p class='no-data type2'>게시물이 없어요</p>";
               
			}
		}
		
		$(".sns-list").append(innerHtmlTxt);
	}
	*/
</script>
</html>