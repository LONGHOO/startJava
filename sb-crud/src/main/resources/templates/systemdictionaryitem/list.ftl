﻿<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<script>
    $(function () {
        //获取添加里面的class属性.btn_save
        $(".btn_save").click(function () {//编辑class里也添加同样的属性用于弹出模态框
            //添加和上一次编辑的数据重复,解决办法:重置表单$("#editForm")[0].reset()
            $("#editForm")[0].reset();
            //获取按钮上绑定的数据
            var json = $(this).data("json");
            if(json){
                $("input[name=id]").val(json.id);
                $("input[name=title]").val(json.title);
                $("input[name=sequence]").val(json.sequence);
            }
            <#if parent??>
                $("input[name=parentId]").val(${parent.id});
                $("input[name=parentTitle]").val("${parent.title}");
            </#if>
            //给id为myModal的模态框绑定一个点击事件,弹出模态框
            $("#myModal").modal("show");
        })
        //添加和编辑信息提示优化
        $(".btn_submit").click(function () {
            //.ajaxSubmit表单异步提交
            $("#editForm").ajaxSubmit(function (data) {
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
            <#--设置当前要回显当前菜单,必须在载人菜单前完成设置-->
            <#--<c:set var="menu" value="systemdictionary"/>-->
            <#assign menu="systemdictionaryItem"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">字典明细管理</h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <ul id="menu" class="list-group">
                        <li class="list-group-item">
                            <a href="javascript:" list-toggle="collapse" list-target="#strategy_detail">
                                <span>字典目录</span>
                            </a>
                            <ul class="in" id="strategy_detail">
                                <#list dics as d>
                                    <li class="${((qo.parentId)?? && d.id==qo.parentId)?string('active','')}">
                                        <a href="/systemdictionaryItem/list?parentId=${d.id}">${d.title}</a></li>
                                </#list>
                            </ul>
                        </li>
                    </ul>
                </div>

                <div class="col-sm-9">
                    <#if result??>
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="" method="post">
                        <input type="hidden" id="currentPage" name="currentPage" value="1">
                        <a href="#" class="btn btn-success btn_save">
                            <span class="glyphicon glyphicon-plus"></span> 字典明细添加
                        </a>
                    </form>
                    <table class="table table-striped table-hover" >
                        <tr>
                            <th>编号</th>
                            <th>标题</th>
                            <th>序号</th>
                            <th>操作</th>
                        </tr>
                        <#list result.list as entity>
                            <tr>
                                <td>${entity_index+1}</td>
                                <td>${entity.title}</td>
                                <td>${entity.sequence}</td>
                                <td>
                                    <a class="btn btn-info btn-xs btn_save" href="#" data-json='${entity.json}'>
                                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                                    </a>
                                    <a href="#" class="btn btn-danger btn-xs btn-delete" data-url="/systemdictionaryItem/delete?id=${entity.id}">
                                        <span class="glyphicon glyphicon-trash"></span> 删除
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </table>
                    <#include "../common/page.ftl"/>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">字典明细编辑</h4>
            </div>
            <div class="modal-body">
                <#--提交的表单-->
                <form class="form-horizontal" action="/systemdictionaryItem/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" name="id" value="${(entity.id)!}">

                    <div class="form-group">
                        <label class="col-sm-3 control-label">目录：</label>
                        <div class="col-sm-6">
                            <input type="hidden" class="form-control" name="parentId"/>
                            <input type="text" class="form-control" name="parentTitle" disabled="disabled"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" value="${(entity.title)!}"
                                   placeholder="请输入字典明细标题">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence" value="${(entity.sequence)!}"
                                   placeholder="请输入字典明细序号">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn_submit">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>