<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <link rel="stylesheet" href="../../static/css/bootstrap.css"/>

    <script src="../../static/js/bootstrap.min.js"></script>
    <script src="../../static/js/jQuery.min.js" crossorigin="anonymous"></script>

</head>
<body>

<div class="col-md-6 col-md-offset-3">
    <a href="/department/input.do">新增</a>
    <div class="row">
        <form action="/department/list.do" method="post">
            <div class="row">
                <input type="hidden" id="currentPage" name="currentPage" value="${pageResult.currentPage}"/>
                <div class="col-md-6">关键字:<input type="text" name="keyword" value="${qo.keyword}"></div>
                <div class="col-md-6"><button class="btn-primary" onclick="search()" value="查询">查询</button> </div>
            </div>

            <table class="table table-bordered" style="text-align: center;margin-top:15px;">
                <tr>
                    <td>id</td>
                    <td>姓名</td>
                    <td>部门编号</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${pageResult.list}" var="department">
                    <tr>
                        <td>${department.id}</td>
                        <td>${department.name}</td>
                        <td>${department.sn}</td>
                        <td><a href="/department/delete.do?id=${department.id}">删除</a> |
                            <a href="/department/input.do?id=${department.id}">编辑</a></td>
                    </tr>
                </c:forEach>
            </table>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="javascript:void(0)" onclick="goPage(1)">首页</a></li>
                    <li><a href="javascript:void(0)" onclick="goPage(${pageResult.prePage})">上一页</a></li>
                    <li><a href="javascript:void(0)" onclick="goPage(${pageResult.nextPage})">下一页</a></li>
                    <li><a href="javascript:void(0)" onclick="goPage(${pageResult.totalPage})">末页</a></li>
                </ul>
            </nav>
            <span>当前${pageResult.currentPage}/${pageResult.totalPage}</span>
            每页<select name="pageSize" onchange="refresh()">
                    <option value="3" ${pageResult.pageSize == 3 ? "selected":""}>3</option>
                    <option value="5" ${pageResult.pageSize == 5 ? "selected":""}>5</option>
                    <option value="10" ${pageResult.pageSize == 10 ? "selected":""}>10</option>
                </select>
            共计${pageResult.rows}条记录
        </form>
    </div>
</div>

<script type="text/javascript">
    function search(){
        document.forms[0].submit();
    }
    function goPage(page){
        document.getElementById("currentPage").value = page;
        document.forms[0].submit();
    }
    function refresh(){
        search();
    }
</script>
</body>
</html>