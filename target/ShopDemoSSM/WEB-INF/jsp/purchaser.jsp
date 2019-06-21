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
<title>Insert title here</title>
</head>
<script type="text/javascript">
	var path="${pageContext.request.contextPath}";
</script>
<body>
<div style="text-align: center">
		欢迎你,${sessionScope.employee}</div>
			
			
			<form method="POST"
		action="${pageContext.request.contextPath}/AddInventory">

		<table border="1">
			<tr>
				<th>商品名称</th>
				<th>商品图片</th>
				<th>单价</th>
				<th>库存数量</th>
				<th>当前文案</th>
				<th>进货数量</th>				
				<th>当前状态</th>
				<th>上架/下架</th>
				<th>编辑图片文案</th>
				<th>编辑属性信息</th>

			</tr>
			<c:if test="${!empty sessionScope.products}">
			<c:forEach items="${sessionScope.products }" var="temp">
			<tr>
				<td>${temp.productName}</td>
				<td><img src="${temp.pictureLink}" /></td>
				<td>${temp.price}</td>
				<td><div>${temp.inventoryQuantity}</div></td>
				<td><div>${temp.productPlan}</div></td>
				<td>进货数量<input type="number"
					name="buyProductID${temp.id}"/>
				</td>
			<td><div>${temp.productStatus==0? "正常": "已下架"}</div></td>
				<td>
				<a href="${pageContext.request.contextPath}/SetOnShelf/${temp.id}">上架</a>
				<br>
				<a href="${pageContext.request.contextPath}/SetOffShelf/${temp.id}">下架</a>
				</td>
				
				<td>				
				<a href="${pageContext.request.contextPath}/EditPlanAndPic/${temp.id}">编辑图片文案</a>
				</td>
				
				<td>				
				<a href="${pageContext.request.contextPath}/EditExtendedAttribute/${temp.id}">编辑属性</a>
				</td>

			</tr>
			
			</c:forEach>
			</c:if>
			
			</table>
			<div style="text-align: center">
			<input type="submit" value="submit">
		</div>
			</form>
			
			<div style="text-align: center">下面是新增商品的表单</div>
	<form method="POST"
		action="${pageContext.request.contextPath}/AddNewProduct">
		<div style="text-align: center">
			商品名称<input type="text" name="productName" /> <br/>商品售价<input type="number" min="0"
				name="price" />  <br/>商品进价<input type="number" min="0" name="buyingPrice" />
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
			 <input type="submit"
				value="submit">
		</div>
 <br/>


		</div></form>
	
		

	<div style="text-align: center">下面是新增商品类别的表单.对顶级类别,上级类别无效</div>
	<form method="POST"
		action="${pageContext.request.contextPath}/AddNewCategory">
		<div style="text-align: center">
			  <label>
			<input type="radio" name="isTop" value="0" />非顶级类别</label>
             <label><input type="radio" name="isTop" value="1" />顶级类别</label>
				
				<label>上级类别名称<input type="text" name="parentName"/></label>
				<label>类别名称<input type="text" name="name"/></label>
			 <br/>
			 <input type="submit"
				value="submit">
		</div>
 <br/>

	</form>
	
	
	
	<br><br><br>
			<div style="text-align: center">通过excel新增商品,格式如下:第一行为各字段名称,后面的行为数据<br>
	序号 商品名称 商品售价 商品进价 商品分类
	</div>
	<form method="POST" enctype="multipart/form-data"
		action="${pageContext.request.contextPath}/AddProductsFromExcel">
		<div style="text-align: center">
			请选择excel文件<input type="file" name="excelFile" />
			 <input type="submit"
				value="submit">
		</div>


	</form>
	<br><br><br>
	<a href="${pageContext.request.contextPath}/DeleteCategory"  target="_self">前往删除类别的页面</a>
	<br><br>
			
			
		<a href="${pageContext.request.contextPath}/Quit">退出登录并返回首页</a>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/level_category_changed.js"></script>
</body>
</html>