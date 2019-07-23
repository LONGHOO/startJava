<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<script>
    $(function () {
        //删除信息提示优化
        $(".btn-delete").click(function () {
            //获取要删除的id上的数据
            var url = $(this).data("url");
            //弹出确认提示框
            $.messager.confirm("DSB","删除不可恢复",function(){
                //如果确定,发起删除
                $.post(url,function(data){
                    if (data.success){//success在JSONResult工具类里默认操作成功
                        $.messager.alert("MMP","已经做好了.");
                        setTimeout(function () {
                            //成功后刷新页面
                            window.location.reload();
                        },1000)
                    }else{
                        $.messager.alert("TMD",data.msg);
                    }
                })
            })
        });
        $("#all").change(function () {
            $(".selectedCheckBox").prop("checked",this.checked);
        });
        //取出数组类型参数的[]问题
        jQuery.ajaxSettings.traditional = true;
        //批量删除
        $(".btn-batchDelete").click(function (data) {
            //当用户没有选择数据就点击批量删除,出现的效果
            if($(".selectedCheckBox:checked").length == 0){
                $.messager.alert("DSB","还没有选择数据,你想删什么?");
                return;
            }
            var ids = [];
            //获取需要删除的数据
            $(".selectedCheckBox:checked").each(function (index,checkbox) {
                ids.push($(checkbox).data("id"));
            })
            console.log(ids);
            //选择删除,就显示提示框
            $.messager.confirm("DSB","删除就不能恢复啦!",function(){
                //如果确定,发起删除
                $.get("/employee/batchDelete",{ids:ids},function(data){
                    if (data.success){//success在JSONResult工具类里默认操作成功
                        $.messager.alert("MMP","已经做好了.");
                        setTimeout(function () {
                            //成功后刷新页面
                            window.location.reload();
                        },1000)
                    }else{
                        $.messager.alert("TMD",data.msg);
                    }
                })
            })
        })
        $(".btn-import").click(function () {
            //给id为myModal的模态框绑定一个点击事件,弹出模态框
            $("#importModal").modal("show");
        })
        $(".importBtn").click(function () {
            $("#editForm").submit();
        })
    })
</script>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#assign menu="employee"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">员工管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/employee/list" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>关键字:</label>
                    <input type="text" class="form-control" name="keyword" value="${(qo.keyword)!}"
                           placeholder="请输入姓名/邮箱">
                </div>
                <div class="form-group">
                    <label>部门:</label>
                    <select class="form-control" name="deptId">
                        <option value="-1">全部</option>
                        <#list depts as d>
                            <option value="${d.id}">${d.name}</option>
                        </#list>
                    </select>
                    <script>
                        //回显查询的部门
                        $("#searchForm select[name='deptId']").val(${qo.deptId});
                    </script>
                </div>
                <button id="btn_query" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span> 查询
                </button>
                <#--/employee/input-->
                <a href="/employee/input" class="btn btn-success">
                    <span class="glyphicon glyphicon-plus"></span> 添加
                </a>
                <a href="#" class="btn btn-danger btn-batchDelete">
                    <span class="glyphicon glyphicon-trash"></span> 批量删除
                </a>
                <a href="/employee/exportExcel" class="btn btn-warning btn-export">
                    <span class="glyphicon glyphicon-export"></span> 导出
                </a>
                <a href="#" class="btn btn-warning btn-import">
                    <span class="glyphicon glyphicon-import"></span> 导入
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>
                        <input type="checkbox" id="all"/>
                    </th>
                    <th>编号</th>
                    <th>名称</th>
                    <th>邮箱</th>
                    <th>年龄</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                <#list result.list as entity>
                    <tr>
                        <td>
                            <input type="checkbox" class="selectedCheckBox" data-id="${entity.id}"/>
                        </td>
                        <td>${entity_index+1}</td>
                        <td>${entity.name}</td>
                        <td>${entity.email}</td>
                        <td>${(entity.age)!}</td>
                        <td>${(entity.dept.name)!"暂未分配"}</td>
                        <td>
                            <#--href="/employee/input?id=${entity.id}"-->
                            <a class="btn btn-info btn-xs btn" href="/employee/input?id=${entity.id}">
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>

                            <#--<@shiro.hasPermission name="employee:delete">-->
                                <a class="btn btn-danger btn-xs btn-delete" href="#" data-url="/employee/delete?id=${entity.id}">
                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            <#--</@shiro.hasPermission>-->
                        </td>
                    </tr>
                </#list>
            </table>

            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>
<!-- 导入员工 -->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">导入员工</h4>
            </div>
            <div class="modal-body">
                <!--填充编辑界面-->
                <form id="editForm" class="form-horizontal" action="/employee/importExcel"
                      enctype="multipart/form-data" method="post">
                    <input type="hidden" name="id"/>
                    <div class="form-group" >
                        <label for="name" class="col-lg-4 control-label">上传文件：</label>
                        <div class="col-lg-6">
                            <input type="file" name="file" accept="application/vnd.ms-excel" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-lg-4 control-label">参考模板：</label>
                        <div class="col-lg-6">
                            <a href="/template/employee.xls" class="btn btn-success btn-block">
                                <span class="glyphicon glyphicon-download"></span> 下载模板
                            </a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary importBtn">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>