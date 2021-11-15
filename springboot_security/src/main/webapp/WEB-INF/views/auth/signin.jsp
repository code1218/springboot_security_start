<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../setting/setting_taglib.jsp"></jsp:include>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인</title>
	</head>
	<body>
		로그인
		<form action="/auth/signin" method="post">
			username: <input name="username"><br>
			password: <input type="password" name="password"><br>
			<input type="submit" value="로그인">
		</form>
		<button onclick="location.href='/auth/signup'">회원가입</button>
	</body>
</html>