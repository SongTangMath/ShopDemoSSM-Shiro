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
<div style="text-align: center">当前图片</div>
	<div style="margin: 100px 300px">
		<img src="${requestScope.product.pictureLink }">
		<div>
			<form method="POST"
				action="${pageContext.request.contextPath}/ModifyProduct/${requestScope.product.id}"
				enctype="multipart/form-data">
				请选择图片<input type="file" name="newpicture" /> <br> 或者直接输入URL<input
					type="url" name="pictureUrl" />
				
				<br>当前文案<br> <input type="text" name="productPlan"
					value="${requestScope.product.productPlan }"> <input type="hidden"
					name="productname" value="${requestScope.product.productName }"> <input
					type="submit" value="submit">

			</form>
		</div>
	</div>
</body>
</html>