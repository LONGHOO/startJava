<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	    <c:set var="menu" value="employee"/>
            <%@include file="../common/menu.jsp"%>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">员工编辑</h1>
                </div>
            </div>
            <div class="row col-sm-10">
                <form class="form-horizontal" action="/employee/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" value="${entity.id}" name="id">
                    <div class="form-group" >
                        <label class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="name" value="${entity.name}" placeholder="请输入用户名">
                        </div>
                    </div>
                    <c:if test="${empty entity.id}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">密码：</label>
                        <div class="col-sm-6">
                        <input type="password" class="form-control" id="pwd"  name="password"  placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">验证密码：</label>
                        <div class="col-sm-6">
                        <input type="password" class="form-control" name="repwd" placeholder="再输入一遍密码">
                        </div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="email" value="${entity.email}" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                        <input type="text" class="form-control" name="age" value="${entity.age}" placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                        <select class="form-control" name="dept.id">
                            <c:forEach var="d" items="${depts}">
                                <option value="${d.id}" >${d.name}</option>
                            </c:forEach>
                        </select>
                        </div>
                        <script>
                        	//部门回显
                           $("#editForm select[name='dept.id']").val(${entity.dept.id});
                           // $("#editForm select[name=\"dept.id\"]").val(${entity.dept.id});
                        </script>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">超级管理员：</label>
                        <label class="checkbox-inline" style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin">
                        </label>
                    </div>
                    <div class="form-group" id="role">
                        <div>
                            <label class="control-label" style="margin-left: 60px">角色：</label>
                        </div>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-4 col-sm-offset-1">
                                <%--显示系统中所有的角色--%>
                                <select multiple class="form-control allRoles" size="15">
                                <c:forEach items="${roles}" var="r">
                                    <option value="${r.id}">${r.name}</option>
                                </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-2" style="margin-top: 60px;" align="center">
                                <div >
                                    <a type="button" class="btn btn-info  "  style="margin-top: 10px"
                                       onclick="moveSelected('allRoles', 'selfRoles')">&nbsp;&gt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveSelected('selfRoles', 'allRoles')">&nbsp;&lt;&nbsp;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('allRoles', 'selfRoles')">&gt;&gt;</a>
                                    <br>
                                    <a type="button" class="btn btn-info " style="margin-top: 10px"
                                       onclick="moveAll('selfRoles', 'allRoles')">&lt;&lt;</a>
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <%--显示当前用户拥有的角色--%>
                                        <c:forEach items="${entity.role}" var="r">
                                            <option value="${r.id}">${r.name}</option>
                                        </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="button" class="btn btn-success">
                                <span class="glyphicon glyphicon-saved"></span> 保存
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>

   /* function moveSelected(src,traget) {
        $("."+src+" option:selected").appendTo("."+traget);
    }

    function moveAll(src,traget) {
        $("."+src+" option").appendTo("."+traget);
    }
    */

   function moveAll(src,traget) {
       $("."+src+" option").appendTo("."+traget);
   }

   function moveSelected(src,traget) {
       $("."+src+" option:selected").appendTo("."+traget);
   }
    
    var role;
    $(function () {
       /* $("#btn_submit").click(function () {
            $(".selfRoles option").prop("selected",true);
           $("#editForm").submit();
        })*/
        $("#btn_submit").click(function () {
            $(".selfRoles option").prop("selected",true);
            $("#editForm").submit();
        })


        $("#admin").click(function () {
            if( this.checked){
                role= $("#role").remove();
            }else {
                $(this).closest("div").after(role);
            }
        })


        //去重
        $.each($(".selfRoles option"),function (index,ele) {
            $(".allRoles option[value='"+ele.value+"']").remove();
        })



        <c:if test="${entity.admin}">
        $("#admin").click();
        </c:if>
    });



    $(function () {

    })


</script>
</body>
</html>