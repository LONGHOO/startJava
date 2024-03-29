﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <c:set var="menu" value="department"/>
            <%@include file="../common/menu.jsp"%>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">部门编辑</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="/department/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id" value="${department.id}">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label">部门名称：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" value="${department.name}"
                               placeholder="请输入部门的名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门编号：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="sn" value="${department.sn}"
                               placeholder="请输入部门编号">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="submit" class="btn btn-success">
                                <span class="glyphicon glyphicon-saved"></span> 保存
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>