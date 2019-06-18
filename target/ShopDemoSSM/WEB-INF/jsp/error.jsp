<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zkdx.database.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%=exception.getMessage()%>
    <p align="center">
        <a href="${pageContext.request.contextPath}/index.jsp" >
            出现异常，请点击链接返回首页</a>
    </p>
</body>
</html>
