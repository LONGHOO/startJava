<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <#include "../common1/link.ftl">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common1/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="employee">
    <#include "../common1/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <form class="form-inline" id="searchForm" action="/employee/list" method="post">

                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>
                            查询
                        </button>
                        <a href="javascript:;" class="btn btn-success btn_redirect" data-url="/employee/input.do">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                        <a href="javascript:;" class="btn btn-danger btn_batchDelete"
                           data-url="/employee/batchDelete.do">
                            <span class="glyphicon glyphicon-trash"></span> 批量删除
                        </a>
                        <a href="/employee/exportFile.do" target="_blank" class="btn btn-warning">
                            <span class="glyphicon glyphicon-export"></span> 导出
                        </a>
                        <a href="javascript:;" class="btn btn-warning btn_import">
                            <span class="glyphicon glyphicon-import"></span> 导入
                        </a>
                    </form>
                </div>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="checkAll"></th>
                        <th>编号</th>
                        <th>名称</th>
                        <th>email</th>
                        <th>年龄</th>
                        <th>部门</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <#list pageInfo.list as entity>
                        <tr>
                            <td><input type="checkbox" class="cb" data-id="${entity.id}"></td>
                            <td>${entity_index+1}</td>
                            <td>${entity.name}</td>
                            <td>${entity.email}</td>
                            <td>${(entity.age)!}</td>
                            <td>${(entity.dept.name)!''}</td>
                            <td>
                                <a class="btn btn-info btn-xs btn_redirect" href="javascript:;"
                                   data-url="/employee/input.do?id=${entity.id}">
                                    <span class="glyphicon glyphicon-pencil"></span> 编辑
                                </a>
                            </td>
                        </tr>
                    </#list>
                </table>
                <!--分页-->
                <#include "../common1/page.ftl">
            </div>
        </section>
    </div>
    <#include "../common1/footer.ftl">
</div>

</body>
</html>
