<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ page import="java.util.*"%>
<%@ page import="com.zkdx.database.*"%>
<%@ page import="java.text.DateFormat"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>客服的页面</title>
</head>
<body>
	<div style="text-align: center">
		欢迎你,${sessionScope.employee}</div>

	<form method="POST"
		action="${pageContext.request.contextPath}/CustomerServiceQueryUser">

		<div style="text-align: center">
			<input type="text" name="username"> <input type="submit"
				value="搜索指定用户的订单">
		</div>
	</form>

	
	<c:if test="${empty requestScope.userOrderInfoMap}">
	<div style="text-align: center">未搜索到用户</div>
	</c:if>
	<table border="1">
			<tr>
				<th>订单时间</th>
				<th>商品及数量</th>
				<th>总价</th>
			</tr>
			
			<c:if test="${!empty requestScope.userOrderInfoMap}">
			<div style="text-align: center">${requestScope.userByCustomerService.username}的订单信息</div>
			<c:set  var="sum" value="0"></c:set>
			<c:forEach items="${requestScope.userOrderInfoMap}" var="entry">
			<tr>
			<td>${entry.key}</td>
			<td>
			<c:forEach items="${entry.value }" var="orderInfo">
			<c:set  var="sum" value="${sum+orderInfo.price*orderInfo.productQuantity }"></c:set>
			商品名称 ${orderInfo.productName }<br>
			商品数量${orderInfo.productQuantity }<br>
			属性信息${orderInfo.extendedAttributeString }
			<br><br>
									
			
			</c:forEach>
			</td>
			<td>${sum }</td>
			<c:set  var="sum" value="0"></c:set>
			</tr>
			</c:forEach>
			
		
			</c:if>
			
		</table><br/><br/><br/><br/>
<div style="text-align: center">分页显示所有订单,每页10条记录</div>
<c:if test="${empty requestScope.userOrderInfoList}">
	<div style="text-align: center">未搜索到订单</div>
	</c:if>
	<form method="POST"
		action="${pageContext.request.contextPath}/ListOrderByPage">	
			
			
			<table border="1">
		<tr>
			<th>订单时间</th>
			<th>订单id</th>
			<th>用户名</th>
			<th>商品及数量</th>
			<th>该订单总价</th>
		</tr>
		
	
	<c:if test="${!empty requestScope.userOrderInfoList}">
	<c:set  var="sum" value="0"></c:set>
			<c:forEach items="${requestScope.userOrderInfoList}" var="orderInfo">						
			<c:set  var="sum" value="${orderInfo.price*orderInfo.productQuantity }"></c:set>			
			<tr>
			<td>${orderInfo.username }</td>
			<td>${orderInfo.orderID }</td>
			<td>${orderInfo.username }</td>
			<td>
			商品名称 ${orderInfo.productName }<br>
			商品数量${orderInfo.productQuantity }<br>
			属性信息${orderInfo.extendedAttributeString }
			<br><br>
									
		
			</td>
			<td>${sum}</td>
			
			</tr>
			</c:forEach>
		
	</c:if>
		
		</table>	
			请输入页数编号,共(${requestScope.totalPages })页<input type="number" name="orderListIndex">
			<input type="submit" value="GO!">
		
	</form>
	
</body>
</html>