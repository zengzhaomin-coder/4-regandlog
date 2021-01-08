<%@page import="regandlog.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结果</title>
</head>
<body>

<div style='width: 400px; margin: 1em auto; background: #eee; padding: 2em;'>
	登录成功！！！！
	<p style="color: red">
	<%
	User user = (User) session.getAttribute("user"); 
	out.println("欢迎您 " + user.getUsername());
	%>
	</p>
</div>

</body>
</html>