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
                $("input[name=name]").val(json.name);
                $("input[name=age]").val(json.age);
                $("select[name=gender]").val(json.gender);
                $("input[name=tel]").val(json.tel);
                $("input[name=qq]").val(json.qq);
                $("select[name='job.id']").val(json.jobId);
                $("select[name='source.id']").val(json.sourceId);
                $("select[name='seller.id']").val(json.sellerId);
            }
            //给id为myModal的模态框绑定一个点击事件,弹出模态框
            $("#editModal").modal("show");
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
        //修改状态
        $(".btn-status").click(function () {
            var json = $(this).data("json");
            //设置用户的id和name
            if(json) {
                $("input[name=cid]").val(json.id);
                $("input[name=cname]").val(json.name);
            }
            $("#statusModal").modal("show");
        })
        //修改状态转客户池
        $(".btn-statusSubmit").click(function () {
            $("#statusForm").ajaxSubmit(function (data) {
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
        //跟进
        $(".btn-trace").click(function () {
            var json = $(this).data("json");
            //设置用户的id和name
            if(json) {
                $("input[name='customer.id']").val(json.id);
                $("input[name=name]").val(json.name);
            }
            $("#traceModal").modal("show");
        })
        //保存跟进历史
        $(".btn-traceSubmit").click(function () {
            //.ajaxSubmit表单异步提交
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
        //潜在用户移交用户
        $(".btn-transferBtn").click(function () {
            var json = $(this).data("json");
            //设置移交信息
            if(json) {
                $("input[name='customer.id']").val(json.id);
                $("input[name='customer.name']").val(json.name);
                $("input[name='oldseller.id']").val(json.sellerId);
                $("input[name='oldseller.name']").val(json.sellerName);
                $("input[name='customer.name']").val(json.name);
            }
            $("#transferModal").modal("show");
        })
        //移交保存
        $(".btn-transferSubmit").click(function () {
            //.ajaxSubmit表单异步提交
            $("#transferForm").ajaxSubmit(function (data) {
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
</script>
<body>
<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
        <#--设置当前要回显当前菜单,必须在载人菜单前完成设置-->
        <#--<c:set var="menu" value="department"/>-->
            <#assign menu="customer"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">潜在客户管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/customer/potentialList" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label for="keyword">关键字:</label>
                    <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}" placeholder="请输入姓名/电话">
                </div>
                <#--<@shiro.hasAnyRoles name="admin,003">-->
                    <div class="form-group">
                        <label for="seller">市场专员:</label>
                        <select class="form-control" id="seller" name="sellerId">
                            <option value="-1">全部</option>
                            <#list sellers as s>
                                <option value="${s.id}">${s.name}</option>
                            </#list>
                        </select>
                            <#--//$("#seller").val(${qo.sellerId});-->
                        <script>
                            $("#seller option[value='${(qo.sellerId)!}']").prop("selected", true);
                        </script>
                    </div>
                <#--</@shiro.hasAnyRoles>-->
                <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                <a href="JavaScript:;" class="btn btn-success btn_save"><span class="glyphicon glyphicon-plus"></span>  添加</a>
            </form>


            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>名称</th>
                    <th>电话</th>
                    <th>qq</th>
                    <th>职业</th>
                    <th>来源</th>
                    <th>销售员</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <#list result.list as entity>
                    <tr>
                        <td>${entity_index+1}</td>
                        <td>${entity.name}</td>
                        <td>${(entity.tel)!}</td>
                        <td>${entity.qq}</td>
                        <td>${(entity.job.title)!}</td>
                        <td>${(entity.source.title)!}</td>
                        <td>${(entity.seller.name)!}</td>
                        <td>${entity.statusName}</td>
                        <td>
                            <a class="btn btn-info btn-xs btn_save" href="#" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span> 编辑
                            </a>
                            <a class="btn btn-primary btn-xs btn-trace"  data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span>跟进
                            </a>
                            <a class="btn btn-warning btn-xs btn-transferBtn"  data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span>移交
                            </a>
                            <a class="btn btn-danger btn-xs btn-status"  data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span>修改状态
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>

<#--客户编辑模态框-->
<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title inputTitle">潜在客户编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/customer/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" value="" name="id">
                    <div class="form-group" >
                        <label  class="col-sm-3 control-label">客户名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name"
                                   placeholder="请输入客户姓名"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">客户年龄：</label>
                        <div class="col-sm-6">
                            <input type="number" class="form-control" name="age"
                                   placeholder="请输入客户年龄"/>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label  class="col-sm-3 control-label">客户性别：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="gender">
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">客户电话：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="tel"
                                   placeholder="请输入客户电话"/>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label  class="col-sm-3 control-label">客户QQ：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="qq"
                                   placeholder="请输入客户QQ"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">客户工作：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="job.id">
                                <#list jobs as j>
                                    <option value="${j.id}">${j.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-3 control-label">客户来源：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="source.id">
                                <#list sources as s>
                                    <option value="${s.id}">${s.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label  class="col-sm-3 control-label">销售人员：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="seller.id">
                               <#list sellers as e>
                                   <option value="${e.id}">${e.name}</option>
                               </#list>
                            </select>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn_submit" >保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
            </div>
        </div>
    </div>
</div>
<#--修改状态-->
<div class="modal fade" id="statusModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改客户状态</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/customer/updateStatus" method="post" id="statusForm">
                    <input type="hidden" name="cid"/>
                    <div class="form-group" >
                        <label class="col-lg-4 control-label">客户名称：</label>
                        <div class="col-lg-6">
                            <input type="text" class="form-control" name="cname"
                                   readonly placeholder="请输入客户名称"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">客户状态：</label>
                        <div class="col-lg-6">
                            <select class="form-control" name="status">
                                <option value="0">潜在客户</option>
                                <option value="1">移入客户池</option>
                                <option value="2">转正式客户</option>
                                <option value="3">开发失败</option>
                                <option value="4">客户流失</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-statusSubmit">保存</button>
            </div>
        </div>
    </div>
</div>
<#--跟进历史-->
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
                    <input type="hidden" name="customer.id"/><#--customer.-->
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
<#--移交模态框-->
<div id="transferModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增/编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/customertransfer/save" method="post" id="transferForm" style="margin: -3px 118px">
                    <input type="hidden" name="id" id="customerTransferId"/>
                    <div class="form-group" >
                        <label for="name" class="col-sm-4 control-label">客户姓名：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="customer.name"   readonly >
                            <input type="hidden" class="form-control"  name="customer.id"  >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">旧营销人员：</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control"  name="oldseller.name" readonly >
                            <input type="hidden" class="form-control"    name="oldseller.id"  >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">新营销人员：</label>
                        <div class="col-sm-8">
                            <select name="newseller.id" id="newseller" class="form-control">
                                <#list sellers as e>
                                    <option value="${e.id}">${e.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sn" class="col-sm-4 control-label">移交原因：</label>
                        <div class="col-sm-8">
                            <textarea type="text" class="form-control" id="reason" name="reason" cols="10" ></textarea>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary btn-transferSubmit" >保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
</html>