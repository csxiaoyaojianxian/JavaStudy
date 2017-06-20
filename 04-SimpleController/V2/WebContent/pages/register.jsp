<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
</head>
<body>
	${registererror}
    <form action="${pageContext.request.contextPath }/register.action" method="post">  
         用户名:<input type="text" name="username" /><br />  
        <br> 密   码:   <input type="password" name="password" /><br />  
        <br><input type="submit" value="register" /> 
        <input type="reset"  value="reset" /> 
    </form>  
</body>
</html>