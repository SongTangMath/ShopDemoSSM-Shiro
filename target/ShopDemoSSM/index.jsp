<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<p>用户登陆页面</p>

<div>
    <form method="post"
          action="login">
        <div style="text-align: center">
            Username:<input type="text" name="username" id="username" />
        </div>
        <div style="text-align: center">
            Password :<input type="password" name="password" id="password" />
        </div>

        <div style="text-align: center">
            <input type="hidden" name="loginCategory" value="user"/>
            <input type="submit" value="Login" />
        </div>

    </form>
</div>
<div style="margin: 100px 300px">
    <img src="https://www.12306.cn/index/images/pic/banner19.jpg"
         width="960px" height="225px">
</div>

<div style="text-align: center">
    <a href="register.jsp">没有账号?注册一个</a>
    <br>
    <a	href="manager_login.jsp">经理在这里登陆</a>
    <br>
    <a	href="employee_login.jsp">员工在这里登陆</a>
</div>
</body>
</html>