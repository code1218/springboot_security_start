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
	<input type="tel" id="phone-number" placeholder="전화번호입력">
	<button type="button" id="auth-req-button">인증요청</button>
	
	<input type="text" id="auth-number" placeholder="인증번호입력">
	<button type="button" id="auth-button">인증확인</button>
	
	<script type="text/javascript">
		const authReqButton = document.querySelector('#auth-req-button');
		const authButton = document.querySelector('#auth-button');
		
		var authCode = "";
		
		authReqButton.onclick = () => {
			const phoneNumberObj = document.querySelector('#phone-number');
			let phoneNumber = phoneNumberObj.value;
			alert(phoneNumber);
			$.ajax({
				type: "get",
				url: "/check/sendSMS",
				data: {
					"phoneNumber" : phoneNumber
				},
				dataType: "text",
				success: function(data){
					authCode = data;
				},
				error: function(){
					alert('비동기 처리 오류');
				}
			})
		}
		
		authButton.onclick = () => {
			const authNumberObj = document.querySelector('#auth-number');
			let authNumber = authNumberObj.value;
			
			if(authNumber == authCode){
				alert('인증 성공!');
			}else{
				alert('인증 실패!');
			}
		}
	</script>
</body>
</html>