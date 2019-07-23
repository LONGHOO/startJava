<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../common/header.jsp"%>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <%@include file="../common/top.jsp"%>
    <div class="row">
        <div class="col-sm-3">
            <c:set var="menu" value="role"/>
            <%@include file="../common/menu.jsp"%>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">角色管理</h1>
                </div>
            </div>

            <form class="form-inline" id="searchForm" action="/role/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <a href="/role/input.do" class="btn btn-success">
                    <span class="glyphicon glyphicon-plus"></span> 添加
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>角色名称</th>
                    <th>角色编号</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageResult.result}" var="entity" varStatus="vs">
                    <tr>
                        <td>${vs.count}</td>
                        <td>${entity.name}</td>
                        <td>${entity.sn}</td>
                        <td>
                            <a class="btn btn-info btn-xs" href="/role/input.do?id=${entity.id}">
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>
                            <a href="/role/delete.do?id=${entity.id}" class="btn btn-danger btn-xs">
                                <span class="glyphicon glyphicon-trash"></span> 删除
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <%@ include file="../common/page.jsp"%>
        </div>
    </div>
</div>
</body>
</html>