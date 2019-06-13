<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
            <%@ page import="com.zkdx.database.*"%>
<%@ page import="java.text.DateFormat"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" align="center">
		<tr>
			<th>属性id</th>
			<th>产品id</th>
			<th>产品名称</th>
			<th>属性名称</th>
			<th>属性值</th>
			<th>操作</th>
		</tr>
		<c:if test="${!empty requestScope.listAttributes&&! empty requestScope.productInfo}">
		
			<c:forEach items="${requestScope.listAttributes }" var="temp">
			<tr>
				<td>${temp.attributeID }</td>
			<td>${temp.productID }</td>
			<td>${requestScope.productInfo.productName }</td>			
			<td>${temp.attributeName }</td>
			<td>${temp.attributeValue }</td>
			<td><a href="${pageContext.request.contextPath}/DeleteExtendedAttribute/${temp.attributeID }">删除</a></td>
			</tr>
			</c:forEach>							
		</c:if>
			
		
		
	</table>
	<div style="text-align: center">
	增加属性
	<br/>
	<form method="post" action="${pageContext.request.contextPath}/AddExtendedAttribute/${requestScope.productInfo.id}">
		属性名<input type="text" name="attributeName" /> <br> 
		属性可选值,不同的值用空格分隔<input
			type="text" name="attributeValue" />			
			<input type="submit" value="submit" />
	</form>
	</div>

	<a href="${pageContext.request.contextPath}/GiveUp">返回上一级页面</a>
</body>
</html>