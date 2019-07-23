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
        //权限加载
        $(".btn-reload").click(function () {
            $.messager.confirm("DSB","加载要耗时间,你确定吗?",function(){
                //如果确定,发起删除
                $.get("/permission/reload",function(data){
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
            <#assign menu="permission"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">权限管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/permission/list" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <a href="#" class="btn btn-success btn-reload">
                    <span class="glyphicon glyphicon-refresh"></span> 重新加载权限
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>权限名称</th>
                    <th>权限表达式</th>
                    <th>权限操作</th>
                </tr>
                <#list result.list as entity>
                    <tr>
                       <td>${entity_index+1}</td>
                       <td>${entity.name}</td>
                       <td>${entity.expression}</td>
                       <td>
                           <a class="btn btn-danger btn-xs btn-delete" href="#" data-url="/permission/delete?id=${entity.id}">
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