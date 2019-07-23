<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>叩丁狼骡窝窝后台管理系统</title>
    <#include "../common/header.ftl" >
    <script>
        $(function () {
            $.get("/region/listByParentId.do", {type: "tree"}, function (response) {
                $("#treeview").treeview({
                    data: [{text: '全部地区', nodes: response}],
                    showTags: true,
                    lazyLoad: function (node) {//懒加载查询
                        $.get("/region/listByParentId.do", {parentId: node.id, type: "tree"}, function (res) {
                            $("#treeview").treeview("addNode", [res, node]);
                        })
                    },
                    onNodeSelected: function(event, data) {
                        $.get("/region/listByParentId.do", {parentId: data.id},function (response) {
                            $("#regionTb tbody").empty();
                            $.each(response,function(index,value){
                                var temp = $("#templateTable tr").clone(true);
                                $(temp).find("td:nth-child(1)").html(index+1);
                                $(temp).find("td:nth-child(2)").html(value.name);
                                $(temp).find("a").attr("data-json",value.json);
                                if(value.state == 1){
                                    $(temp).find("a:nth-child(2)").html("<span class=\"glyphicon glyphicon-info-sign\"></span> 取消推荐")
                                }
                                $("#regionTb tbody").append(temp);
                            })
                        })
                    }
                })
            })

            //编辑
            $(".btn-edit").click(function () {
                var json = $(this).data("json");
                $("input[name=id]").val(json.id);
                $('#name').val(json.name);
                $("#parentId").val(json.parentId);
                $("#parentName").val(json.parentName);
                $("#inputModal").modal('show');
            })
            //新增
            $(".btn-input").click(function () {
                var node = $("#treeview").treeview('getSelected')[0];
                if(node){
                    $("#parentId").val(node.id);
                    $("#parentName").val(node.text);
                }else{
                    $("#parentName").val("全部地区");
                }
                $("#inputModal").modal('show');
            })

            //保存
            $(".btn-submit").click(function () {
                $("#editForm").ajaxSubmit(function(data){
                    resultHandle.saveHandle(data);
                })
            })
            //更改是否推荐
            $(".btn-change").click(function () {
                var json = $(this).data("json");
                $.get("/region/changeState.do",{id:json.id,state:json.state^1},function (data) {
                    resultHandle.saveHandle(data);
                })
            })
        })

    </script>
</head>
<body>
<table id="templateTable" style="display:none">
    <tr>
        <td></td>
        <td></td>
        <td>
            <a class="btn btn-info btn-xs btn-input btn-edit"
               href="javascript:;" data-json=''>
                <span class="glyphicon glyphicon-pencil"></span> 编辑
            </a>
            <a href="javascript:;"
               class="btn btn-danger btn-xs btn-change"
               data-json="">
                <span class="glyphicon glyphicon-fire"></span> 设为推荐
            </a>
        </td>
    </tr>
</table>
<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl">
    <div class="row">
        <div class="col-sm-3">
            <#assign menu="region"/>
            <#include "../common/menu.ftl">
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">地区管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/region/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <a href="javascript:;" class="btn btn-success btn-input">添加</a>
            </form>


            <div class="row">
                <div class="col-sm-4">
                    <div id="treeview"></div>
                </div>
                <div class="col-sm-8">
                    <table class="table table-striped table-hover" id="regionTb">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="inputModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">编辑</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" action="/region/saveOrUpdate.do" method="post" id="editForm">
                        <input type="hidden" name="id">
                        <div class="form-group">
                            <label for="name" class="col-sm-4 control-label">名称：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="name" name="name"
                                       placeholder="请输入地区名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sn" class="col-sm-4 control-label">上级地区：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="parentName" readonly>
                                <input type="hidden" class="form-control" id="parentId" name="parent.id" >
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary btn-submit">保存</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>