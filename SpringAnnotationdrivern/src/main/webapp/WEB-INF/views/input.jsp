<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>员工编辑</h1>
<form action="/department/saveOrUpdate.do" method="post">
	<input type="hidden" name="id" value="${department.id}">
	用户名:<input type="text" name="name" value="${department.name}"/>
	部门编号:<input type="text" name="sn" value="${department.sn}"/>
	<input type="submit" value="提交"/>
</form>
</body>
</html>