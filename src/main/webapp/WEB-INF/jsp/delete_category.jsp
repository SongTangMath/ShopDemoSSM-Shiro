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
<title>删除类别的页面</title>
</head>
<script type="text/javascript">
	var path="${pageContext.request.contextPath}";
</script>
<body>
<form method="POST"
		action="${pageContext.request.contextPath}/DeleteCategoryConfirmed">
		<div style="text-align: center">
			商品名称<input type="text" name="productName" /> <br/>商品售价<input type="number"
				name="price" />  <br/>商品进价<input type="number" name="buyingPrice" />
			 <br/>商品类别选择
			
			
			
			<div style="text-align: center">
			<br/>请选择大类<select id="level0" name="level0" onchange="level0CategoryChanged();">
				<option>    </option>
			</select> 请选择第二层分类<select id="level1" name="level1"
				onchange="level1CategoryChanged();">
				<option>    </option>
			</select> 请选择第三层分类<select id="level2" name="level2"
				onchange="level2CategoryChanged();">
				<option>    </option>
			</select> 
			 <br/>
			 要删除的类别名称(所有子类别也会被删除)<input type="text"		name="categoryNameToDel">
			 <input type="submit"
				value="submit">
		</div>
 <br/>
		</div>
	</form>
	<a href="${pageContext.request.contextPath}/GiveUpDeleteCategory" target="_self">放弃删除类别</a>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/level_category_changed.js"></script>
</html>