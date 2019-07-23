<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <#include "../common/link.ftl">
    <script>
        $(function () {
            $(".btn_reload").click(function () {
                var url = $(this).data("url");
               $.messager.confirm("提示信息","重新加载权限比较耗时，您还要继续吗？",function () {
                   $.get(url,function(response){
                        resultHandle.saveHandle(response,null,"刷新权限成功！");
                   })
               })
            })
        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="permission">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>权限管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/permission/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage">
                    <a href="javascript:;" data-url="/permission/reload.do" class="btn btn-success btn_reload"
                       style="margin: 10px;">
                        <span class="glyphicon glyphicon-repeat"></span> 重新加载
                    </a>
                </form>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <#if pageInfo ?? && (pageInfo.list)??>
                        <#list pageInfo.list as entity>
                            <tr>
                                <td>${entity_index+1}</td>
                                <td>${entity.name}</td>
                                <td>${entity.expression}</td>
                                <td>
                                    <a href="javascript:;" data-url="/permission/delete.do?id=${entity.id}"
                                       class="btn btn-danger btn-xs btn-delete">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </#if>
                </table>
                <#--分页-->
                <#include "../common/page.ftl"/>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
</body>
</html>
