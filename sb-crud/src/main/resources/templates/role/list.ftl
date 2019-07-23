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
        })
    })
</script>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#assign menu="role"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">角色管理</h1>
                </div>
            </div>

            <form class="form-inline" id="searchForm" action="/role/list" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <a href="/role/input" class="btn btn-success">
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
                <#list result.list as entity>
                    <tr>
                        <td>${entity_index+1}</td>
                        <td>${entity.name}</td>
                        <td>${entity.sn}</td>
                        <td>
                            <a class="btn btn-info btn-xs" href="/role/input?id=${entity.id}">
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>
                            <a href="#" data-url="/role/delete?id=${entity.id}" class="btn btn-danger btn-xs btn-delete">
                                <span class="glyphicon glyphicon-trash"></span> 删除
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>
</body>
</html>