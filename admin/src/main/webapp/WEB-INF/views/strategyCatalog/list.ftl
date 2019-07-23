<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#-- 包含其他文件的内容 -->
    <#include "../common/header.ftl"/>
    <script>
        $(function () {
            $(".inputBtn").click(function () {
                var json = $(this).data("json");
                $("#searchForm")[0].reset();
                if(json){
                    $("input[name=id]").val(json.id);
                    $("select[name=state]").val(json.state+'');
                    $("input[name=name]").val(json.name);
                    $("input[name=sequence]").val(json.sequence);
                    $("select[name='strategy.id']").val(json.strategyId);
                }else{
                    //清除id
                    $("input[name=id]").val('');
                    if($("select[name='strategyId']").val() != "-1"){
                        $("select[name='strategy.id']").val($("select[name='strategyId']").val())
                    }
                }
                $("#inputModal").modal('show');
            })
            //保存按钮
            $(".submitBtn").click(function () {
                $("#editForm").ajaxSubmit(function (response) {
                    resultHandle.saveHandle(response,null,"保存成功");
                })
            })
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#-- 往数据模型中填充一个属性 -->
            <#assign menu="strategyCatalog"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略目录管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategyCatalog/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>所属攻略:</label>
                    <select class="form-control" name="strategyId" >
                        <option value="-1">所有攻略</option>
                        <#list strategys as st>
                            <option value="${st.id}">${st.title}</option>
                        </#list>
                    </select>
                    <script>
                        $("select[name=strategyId]").val(${qo.strategyId});
                    </script>
                </div>
                <button id="btn_query" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span> 查询
                </button>
                <a role="button" class="btn btn-success inputBtn">
                    <span class="glyphicon glyphicon-plus"></span> 添加
                </a>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>分类名称</th>
                    <th>所属攻略</th>
                    <th>排序</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>

            <#-- 循环迭代指令 -->
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index+1}</td>
                        <td>${entity.name}</td>
                        <td>${entity.strategy.title}</td>
                        <td>${entity.sequence}</td>
                        <td>${entity.state?string('启用','禁用')}</td>
                        <td>
                            <a class="btn btn-info btn-xs inputBtn" data-json='${entity.json}'>
                                <span class="glyphicon glyphicon-pencil"></span> 修改
                            </a>
                        </td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>


<!-- 模态框必须作为body的子标签 -->
<div class="modal fade" id="inputModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">新增/编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/strategyCatalog/saveOrUpdate.do" method="post" id="editForm">

                    <input type="hidden" name="id">
                    <div class="form-group" >
                        <label class="col-sm-4 control-label">分类名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="name" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="strategy.id" >
                                <#list strategys as st>
                                    <option value="${st.id}">${st.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="state" >
                                <option value="true">启用</option>
                                <option value="false">禁用</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    <span class="glyphicon glyphicon-off"></span> 关闭
                </button>
                <button type="button" class="btn btn-primary submitBtn">
                    <span class="glyphicon glyphicon-saved"></span> 保存
                </button>
            </div>
        </div>
    </div>
</div>





</body>
</html>