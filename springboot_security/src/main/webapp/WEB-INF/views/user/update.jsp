<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보수정</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		회원정보수정
		<form action="/auth/signup" method="post">
			username: <input type="text" name="username" value="${principal.user.username }" readonly="readonly"><br>
			password: <input type="password" name="password" value="${principal.user.password }"><br>
			email: <input type="email" name="email" value="${principal.user.email }"><br>
			name: <input type="text" name="name" value="${principal.user.name }"><br>
			<input type="submit" value="수정">
		</form>
	</body>
</html>