<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function(){

            $(".delete").click(function(){
                var href=$(this).attr("href");
                $("#deleteform").attr("action",href).submit();
                return false;
            });


            $(".put").click(function(){
                var href=$(this).attr("href");
                $("#putform").attr("action",href).submit();
                return false;

            });


        })
    </script>
</head>
<body>
<div style="text-align: center">
    <p>这里是总经理管理页面</p>
</div>

<form id="deleteform" action="" method="POST">
    <input type="hidden" name="_method" value="DELETE"/>
</form>

<form id="putform" action="" method="POST">
    <input type="hidden" name="_method" value="PUT"/>
</form>
<c:if test="${empty sessionScope.employees}">
    <div style="text-align: center">没有员工	</div>

</c:if>
<c:if test="${!empty sessionScope.employees}">

    <table border="1" align="center">
        <tr>
            <th>员工id</th>
            <th>员工id卡号</th>
            <th>姓名</th>
            <th>部门</th>
            <th>职务</th>
            <th>薪水</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${sessionScope.employees }" var="emp">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.identityCard}</td>
                <td>${emp.name }</td>
                <td>${emp.departmentName }</td>
                <td>${emp.job }</td>
                <td>${emp.salary }</td>
                <td><a class="delete" href="${pageContext.request.contextPath}/emp/${emp.id}">删除 </a>
                    <a class="put" href="${pageContext.request.contextPath}/emp/${emp.id}">编辑 </a>
                </td>
            </tr>

        </c:forEach>
    </table>
</c:if>
<br><br><br>
<div style="text-align: center">
    <p>添加新员工</p>
</div>
<div style="text-align: center">
    <form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee">
        员工id卡号<form:input path="identityCard"/><br>
        员工密码<form:input path="password"/><br>
        姓名<form:input path="name"/><br>
        部门<form:input path="departmentName"/><br>
        职务<form:input path="job"/><br>
        薪水<form:input path="salary"/><br>
        <input type="submit" value="Register" />
    </form:form>
</div>
<br><br><br>
<div style="text-align: center">
    根据时间段查询销售情况
    <form action="${pageContext.request.contextPath}/QuerySellingStatus" target="frame1">
        查询起始时间(yyyy-mm-dd)<input type="date" name="beginDateString" /> <br>
        查询结束时间(yyyy-mm-dd)<input type="date" name="endDateString" />
        <input				type="submit" value="获取结果excel表" />

    </form>
    <iframe name="frame1" frameborder="0" id="frame"></iframe>
</div>
<a href="${pageContext.request.contextPath}/Quit">退出登录并返回首页</a>
</body>
</html>