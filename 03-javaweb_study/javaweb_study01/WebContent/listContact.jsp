<%@ page language="java"
	import="java.util.*,com.csxiaoyao.contactSys_web.entity.*"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.csxiaoyao.com" prefix="csxiaoyao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询所有联系人</title>
<style type="text/css">
table td {
	/*文字居中*/
	text-align: center;
}
table {
	/*合并表格的边框*/
	border-collapse: collapse;
}
</style>
</head>

<body>
	<center>
		${loginName } <a href='LogoutServlet'>退出</a>(Session)  上次访问时间：${lastTime }(Cookies)<h3><csxiaoyao:showIp></csxiaoyao:showIp></h3>
	</center>
	<table align="center" border="1" width="700px">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>年龄</th>
			<th>电话</th>
			<th>邮箱</th>
			<th>QQ</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${contacts}" var="con" varStatus="varSta">
			<tr>
				<td>${varSta.count }</td>
				<td>${con.name }</td>
				<td>${con.gender }</td>
				<td>${con.age }</td>
				<td>${con.phone }</td>
				<td>${con.email }</td>
				<td>${con.qq }</td>
				<td>
					<a href="${pageContext.request.contextPath }/QueryContactServlet?id=${con.id}">修改</a>&nbsp;
					<a href="${pageContext.request.contextPath }/DeleteContactServlet?id=${con.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="8" align="center">
				<a href="${pageContext.request.contextPath }/addContact.jsp">[添加联系人]</a>
			</td>
		</tr>
	</table>
</body>
</html>

