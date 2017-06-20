<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>SpringMVC</title>
  </head>
  <body>
	<form action="${pageContext.request.contextPath}/add.action" method="POST">
		<table border="2" align="center">
			<tr>
				<th>姓名</th>
				<td><input type="text" name="username" value="孙"/></td>
			</tr>
			<tr>
				<th>性别</th>
				<td><input type="text" name="gender" value="男"/></td>
			</tr>
			<tr>
				<th>薪水</th>
				<td><input type="text" name="salary" value="50000"/></td>
			</tr>
			<tr>
				<th>时间</th>
				<td><input type="text" name="hiredate" value="2012-12-12"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="添加"/>
				</td>
			</tr>
		</table>
	</form>
  </body>
</html>