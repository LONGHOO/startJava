<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>
    <#include "../common/link.ftl">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="department">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>部门管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/department/list.do" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="javascript:;" class="btn btn-success btn-input btn-edit" style="margin: 10px">
                        <span class="glyphicon glyphicon-plus"></span> 添加
                    </a>
                </form>
                <!--编写内容-->
                <div class="box-body table-responsive no-padding ">
                    <table class="table table-hover table-bordered">
                        <tr>
                            <th>编号</th>
                            <th>部门名称</th>
                            <th>部门编号</th>
                            <th>操作</th>
                        </tr>
                        <#list pageInfo.list as entity>
                            <tr>
                                <td>${entity_index + 1}</td>
                                <td>${entity.name}</td>
                                <td>${entity.sn}</td>
                                <td>
                                    <a class="btn btn-info btn-xs btn-input btn-edit"
                                       href="javascript:;" data-json='${entity.json}'>
                                        <span class="glyphicon glyphicon-pencil "></span> 编辑
                                    </a>
                                    <@shiro.hasRole name="admin">
                                        <a href="javascript:;"
                                           class="btn btn-danger btn-xs btn-delete"
                                           data-url="/department/delete.do?id=${entity.id}">
                                            <span class="glyphicon glyphicon-trash"></span> 删除
                                        </a>
                                    </@shiro.hasRole>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <!--分页-->
                    <#include "../common/page.ftl">
                </div>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="editTitle">部门编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" action="/department/saveOrUpdate.do" id="departmentForm">
                    <input type="hidden" name="id" id="departmentId"/>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-4 control-label">部门名称</label>
                        <div class="col-sm-6">
                            <input type="text" name="name" class="form-control" id="departmentName" placeholder="部门名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-4 control-label">部门编号:</label>
                        <div class="col-sm-6">
                            <input type="text" name="sn" class="form-control" id="departmentNo" placeholder="部门编号">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-save">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
<script>
    $(function () {
        $(".btn-edit").click(function () {
            $("#departmentForm")[0].reset();
            var data = $(this).data("json");
            //如果有数据则为编辑，否则为新增
            if (data) {
                $("#departmentId").val(data.id);
                $("#departmentName").val(data.name);
                $("#departmentNo").val(data.sn);
                $("#editTitle").html("部门编辑");
            } else {
                $("#editTitle").html("部门新增");
            }
            $("#inputModal").modal('show');
        })

        $(".btn-save").click(function () {
            $("#departmentForm").ajaxSubmit(function (response) {
                resultHandle.saveHandle(response);
            })
        })

    })

</script>
</html>
