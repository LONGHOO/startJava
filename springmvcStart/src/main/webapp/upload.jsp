<%--
  Created by IntelliJ IDEA.
  User: minger
  Date: 2019-04-17
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/uploadfile" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="提交"/>
</form>
</body>
</html>
