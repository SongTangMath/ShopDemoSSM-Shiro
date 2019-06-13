<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zkdx.database.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工页面</title>
</head>

<body>
	<div style="text-align: center">
		欢迎你,${sessionScope.employee} </div>

	<br><br><br>
		<a href="${pageContext.request.contextPath}/Quit">退出登录并返回首页</a>

</body>
</html>