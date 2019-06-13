<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工信息编辑页面</title>
</head>
<body>
<div>
		<form method="post"
			action="${pageContext.request.contextPath}/emp">
			<div style="text-align: center">注意,员工卡id不可修改</div>
			<div style="text-align: center">
				员工卡id:<input type="text" value="${requestScope.employee.identityCard}"
					name=identityCard id="employeeId" readonly="readonly" />
			</div>
			<div style="text-align: center">
				员工姓名 :<input type="text" name="name"
					value="${requestScope.employee.name}" id="employeeName"
					/>
			</div>

			<div style="text-align: center">
				员工密码 :<input type="password" name="password"
					value="${requestScope.employee.password}" id="employeePassword" />
			</div>

			<div style="text-align: center">
				员工部门 :<input type="text" name="departmentName"
					value="${requestScope.employee.departmentName}"
					id="employeedepartmentName" />
			</div>

			<div style="text-align: center">
				员工职位 :<input type="text" name="job"
					value="${requestScope.employee.job}" id="employeeJob" />
			</div>

			<div style="text-align: center">
				员工薪水 :<input type="text" name="salary" id="employeeSalary"
					value="${requestScope.employee.salary}"
					oninput="value=value.replace(/[^\d]/g,'')" />
			</div>
			<input type="hidden" name="id" value="${requestScope.employee.id}" />
			<div style="text-align: center">
				<input type="submit" value="submit" />
			</div>

		</form>
	</div>
</body>
</html>