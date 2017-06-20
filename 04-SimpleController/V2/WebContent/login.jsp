<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<a href='${pageContext.request.contextPath }/pages/register.jsp'>注册</a>
	<form action="${pageContext.request.contextPath }/login.action" method='post'>
		<input type="text" name="username" placeholder="用户名:sunshine" value="sunshine"/>
		<input type="password" name="password" placeholder="密码:19931128" value="19931128" />
		<input type="submit" value="登陆">
	</form>
</body>
</html>