<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<script>
    $(function () {
        $(".btn-input").click(function () {
            //添加和上一次编辑的数据重复,解决办法:重置表单$("#editForm")[0].reset()
            $("#traceForm")[0].reset();
            //获取按钮上绑定的数据
            var json = $(this).data("json");
            if(json){
                $("input[name=id]").val(json.id);
                $("input[name=name]").val(json.customerName);
                $("input[name=traceTime]").val(json.traceTime);
                $("input[name=traceDetails]").val(json.traceDetails);
                $("select[name='traceType.id']").val(json.traceTypeId);
                $("select[name=traceResult]").val(json.traceResult);
                $("textarea[name=remark]").val(json.remark);
            }
            //给id为myModal的模态框绑定一个点击事件,弹出模态框
            $("#traceModal").modal("show");
        })
        $(".btn-traceSubmit").click(function () {
            $("#traceForm").ajaxSubmit(function (data) {
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
        //时间跟进选择
        $("input[name=traceTime]").click(function () {
            WdatePicker({
                readOnly:true,
                maxDate:new Date()
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
            <#assign menu="customerTraceHistory"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">跟进历史管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/customertracehistory/list" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label for="keyword">关键字:</label>
                    <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}" placeholder="请输入姓名/电话">
                </div>
                <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
            </form>


            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>客户姓名</th>
                    <th>跟进日期</th>
                    <th>跟进内容</th>
                    <th>跟进方式</th>
                    <th>跟进结果</th>
                    <th>录入人</th>
                    <th>操作</th>
                </tr>
                <#list result.list as entity>
                    <tr>
                        <td>${entity_index+1}</td>
                        <td>${entity.customer.name}</td>
                        <td>${(entity.traceTime)?string('yyyy-MM-dd')}</td>
                        <td>${entity.traceDetails}</td>
                        <td>${(entity.traceType.title)!}</td>
                        <td>${entity.traceResultName}</td>
                        <td>${(entity.inputUser.name)!}</td>
                        <td>
                            <a class="btn btn-info btn-xs btn-input" href="#" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>
<#--跟进历史编辑-->
<div class="modal fade" id="traceModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">跟进</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/customertracehistory/saveOrUpdate" method="post" id="traceForm">
                <#--新增,新增跟进历史没有ID,客户应该要有ID,不然就不知道当前是哪个客户的跟进历史-->
                    <input type="hidden" name="id"/><#--customer.-->
                    <div class="form-group" >
                        <label class="col-lg-4 control-label">客户姓名：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" readonly name="name"/>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-lg-4 control-label">跟进时间：</label>
                        <div class="col-lg-6 ">
                            <input type="text" class="form-control "  name="traceTime" placeholder="请输入跟进时间">
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-lg-4 control-label">跟进记录：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" name="traceDetails"
                                   placeholder="请输入跟进记录"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">交流方式：</label>
                        <div class="col-lg-6">
                            <select class="form-control" name="traceType.id">
                                <#list types as c>
                                    <option value="${c.id}">${c.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">跟进结果：</label>
                        <div class="col-lg-6">
                            <select class="form-control" name="traceResult">
                                <option value="3">优</option>
                                <option value="2">中</option>
                                <option value="1">差</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-lg-4 control-label">备注：</label>
                        <div class="col-lg-6">
                            <textarea type="text" class="form-control" name="remark"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-traceSubmit">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>