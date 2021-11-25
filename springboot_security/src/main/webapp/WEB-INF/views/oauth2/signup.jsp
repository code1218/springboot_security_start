<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<input type="tel" id=phoneNumber>
	<button type="button" id="request">인증요청</button>
	
	<script type="text/javascript">
		const request = document.querySelector('#request');
		var number = '';
		
		request.onclick = () => {
			$.ajax({
				type: "get",
				url: "/check/sendSMS",
				data: {
					"phoneNumber" : $("#phoneNumber").val()
				}, 
				success: function(data){
					number = data;
				},
				error: function(){
					alert('비동기 처리 오류');
				}
				
			})
		}
	</script>
</body>
</html>