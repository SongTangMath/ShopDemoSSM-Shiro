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
<title>用户的页面</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.3.1.js"></script>
<script type="text/javascript">
	var path="${pageContext.request.contextPath}";
	function getextendedattributemap(id){
		
		var xmlRequest = new XMLHttpRequest();
		xmlRequest.open("GET"," ${pageContext.request.contextPath}/JSONExtendedAttributeMap?buyProductID=" +id, true);

		xmlRequest.send(null);
		xmlRequest.onreadystatechange = function() {
			
			if (xmlRequest.readyState == 4) {
				if (xmlRequest.status == 200) {
					var json = xmlRequest.responseText;	
					console.log(json);
					var js = eval("(" + json + ")");
					
					//var js0=js[0];
					var divTemp = document.getElementById("extendedattributemap"+id);
					for(var key in js)
					{						
					var selectTemp =document.createElement("select");
					var selectTempName="ProductID"+id+" "+key;
					console.log("selectTempName "+selectTempName);
						selectTemp.setAttribute("name","ProductID"+id+" "+key);
						selectTemp.setAttribute("id","ProductID"+id+" "+key);										
						var jsValue=js[key];						
						console.log("jsValue.length : "+jsValue.length);						
						
						for(var i=0;i<jsValue.length;i++){
							var op = document.createElement("option");
							op.value = jsValue[i];
							var text = document.createTextNode(jsValue[i]);
							
							op.appendChild(text);
							selectTemp.appendChild(op);
						}
						divTemp.appendChild(document.createTextNode(key));
						divTemp.appendChild(selectTemp);		
						divTemp.appendChild(document.createElement("br"));
					}					
				} else
					console.log("error");
			}
		}
		
	}
</script>
<body>
<div style="text-align: center">
	欢迎你, ${sessionScope.user} </div>
<body>
	<form method="POST"
		action="${pageContext.request.contextPath}/userQueryByKeyWords/ ${sessionScope.user.username}">

		<div style="text-align: center">
			<input type="text" name="productCategory" /> <input type="submit"
				value="按关键字搜索商品" />
		</div>
	</form>

	<form method="POST"
		action="${pageContext.request.contextPath}/userQueryByCategory/ ${sessionScope.user.username}">

		<div style="text-align: center">
			<br/>请选择大类<select id="level0" name="level0" onchange="level0CategoryChanged();">
				<option>    </option>
			</select> 请选择第二层分类<select id="level1" name="level1"
				onchange="level1CategoryChanged();">
				<option>    </option>
			</select> 请选择第三层分类<select id="level2" name="level2"
				onchange="level2CategoryChanged();">
				<option>    </option>
			</select> <input type="submit" value="按照类别搜索商品" />

		</div>
	</form>
	<form method="POST"
		action="${pageContext.request.contextPath}/userBuy/${sessionScope.user.username }">

		<table border="1">
			<tr>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>单价</th>
				<th>库存数量</th>
				<th>当前文案</th>
				<th>购买数量(若超过库存则认为全部购买)</th>
				<th>额外属性选择</th>

			</tr>
			<c:if test="${!empty sessionScope.products}">
			<c:forEach items="${sessionScope.products }" var="temp">
			<tr>
				<td>${temp.productName}</td>
				<td><img src="${temp.pictureLink}" /></td>
				<td>${temp.price}</td>
				<td><div>${temp.inventoryQuantity}</div></td>
				<td><div>${temp.productPlan}</div>
				</td>
				<td>购买数量<input type="number"
					name="buyProductID${temp.id}"/>
				</td>
			
				<td><div id="extendedattributemap${temp.id}">
						<img src="${pageContext.request.contextPath}/static/1.png"
						 onload= "getextendedattributemap(${temp.id});" />
					</div></td>

			</tr>
			
			</c:forEach>
			</c:if>
			
			</table>
			<div style="text-align: center">
			<input type="submit" value="购买上面商品">
		</div>
			</form>
			
			<br><br><br>
			
			<div style="text-align: center">历史订单信息</div>
	
		<table border="1">
			<tr>
				<th>订单时间</th>
				<th>商品及数量</th>
				<th>总价</th>
			</tr>
			
			<c:if test="${!empty sessionScope.orderInfoMap}">
			<c:set  var="sum" value="0"></c:set>
			<c:forEach items="${sessionScope.orderInfoMap}" var="entry">
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
			
		</table>
		<a href="${pageContext.request.contextPath}/Quit">退出登录并返回首页</a>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/level_category_changed.js"></script>
</html>