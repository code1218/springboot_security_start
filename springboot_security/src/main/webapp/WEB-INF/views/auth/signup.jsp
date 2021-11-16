<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting/setting_taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		회원가입
		<form id="signup-form">
			username: <input type="text" name="username"><br>
			password: <input type="password" name="password"><br>
			email: <input type="email" name="email"><br>
			name: <input type="text" name="name"><br>
			<button type="button" id="signup-btn">가입</button>
		</form>
		<button onclick="location.href='/auth/signin'">로그인</button>
		
		<script type="text/javascript">
			const signupBtn = document.querySelector('#signup-btn');
			const signupForm = document.querySelector('#signup-form');
			signupBtn.onclick = () => {
				let formData = new FormData(signupForm);
				let signupObj = {
						username: formData.get('username'),
						password: formData.get('password'),
						email: formData.get('email'),
						name: formData.get('name')					
				}
				$.ajax({
					type: "post",
					url: "/auth/signup",
					data: signupObj,
					dataType: "text",
					success: function(data){
						let respObj = JSON.parse(data);
						if(respObj.code == 400){
							alert('오류코드: ' + respObj.code +
									'\n오류메세지' + 
									'\nusername: ' + respObj.msg.username +
									'\npassword: ' + respObj.msg.password +
									'\nemail: ' + respObj.msg.email +
									'\nname: ' + respObj.msg.name);
						}else if(respObj.code == 410 || respObj.code == 500){
							alert('오류코드: ' + respObj.code +
									'\n오류메세지' + 
									'\n' + respObj.msg);
						}else {
							alert(respObj.msg);
							location.href = '/auth/signin';
						}
					},
					error: function(){
						alert("전송 오류!");
					}
				})
			}
		</script>
	</body>
</html>