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
            <c:set var="menu" value="permission"/>
            <%@include file="../common/menu.jsp"%>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">权限管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/permission/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="${pageResult.currentPage}">
                <input type="hidden" name="pageSize" id="pageSize" value="${pageResult.pageSize}">
                <a href="/permission/reload.do" class="btn btn-success">
                    <span class="glyphicon glyphicon-refresh"></span> 重新加载权限
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>权限名称</th>
                    <th>权限表达式</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageResult.result}" var="entity" varStatus="vs">
                    <tr>
                       <td>${vs.count}</td>
                       <td>${entity.name}</td>
                       <td>${entity.expression}</td>
                       <td>
                           <a class="btn btn-danger btn-xs" href="/permission/delete.do?id=${entity.id}">
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