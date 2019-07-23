<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#-- 包含其他文件的内容 -->
    <#include "../common/header.ftl"/>
    <script src="/js/ckeditor/ckeditor.js"></script>
    <script>
        $(function () {
            var ckeditor = CKEDITOR.replace("textarea");
            //查询
            $("#btn_query").click(function () {
                $("#searchForm").submit();
            })
            //新增编辑
            $(".inputBtn").click(function () {
                var json = $(this).data("json");
                //清空数据
                $("#editForm")[0].reset();
                $("#catalogSelect").val('');
                ckeditor.setData('');
                $("#coverImg").attr("src", "");
                //清空隐藏域名中的id
                $("input[name=id]").val("");
                if(json){
                    $("input[name=id]").val(json.id);
                    $("input[name=title]").val(json.title);
                    $("input[name=coverUrl]").val(json.coverUrl);
                    $("input[name=sequence]").val(json.sequence);
                    $("select[name=state]").val(json.state);
                    $("#coverImg").attr("src", json.coverUrl);
                    $("#strategySelect").val(json.strategyId);
                    //获取当前的攻略类别列表
                    $.get("/strategyCatalog/queryByStrategyId.do",{strategyId:json.strategyId},function (response) {
                        if(response.success){
                            var temp ='';
                            $.each(response.data,function (index,value) {
                                temp +=  "<option value='"+value.id+"'>"+value.name+"</option>";
                            })
                        }
                        $('#catalogSelect').html(temp);
                       $("#catalogSelect").val(json.catalogId);
                    })
                    //查询当前文章的内容
                    $.get("/strategyDetail/getContentById.do",{id:json.id},function (response) {
                        ckeditor.setData(response.data.content);
                    })
                }
                $('#inputModal').modal('show');
            })
            //攻略选择发生改变加载对应的类别
            $("#strategySelect").change(function () {
                var strategyId = $(this).val();
                $.get("/strategyCatalog/queryByStrategyId.do",{strategyId:strategyId},function (response) {
                    if(response.success){
                        var temp ="";
                        $.each(response.data,function (index,value) {
                            temp +=  "<option value='"+value.id+"'>"+value.name+"</option>";
                        })
                    }
                    $('#catalogSelect').html(temp);
                })
            })

            //保存
            $(".submitBtn").click(function () {
                $("#textarea").html(ckeditor.getData());
                $("#editForm").ajaxSubmit(function (response) {
                    resultHandle.saveHandle(response);
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
            <#assign menu="strategyDetail"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">攻略文章管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategyDetail/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>关键字:</label>
                    <input type="text" class="form-control" name="keyword"
                           value="${(qo.keyword)!}" placeholder="请输入标题">
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
                    <th>标题</th>
                    <th>封面</th>
                    <th>发布时间</th>
                    <th>排序</th>
                    <th>攻略类别</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>

            <#-- 循环迭代指令 -->
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${entity.title}</td>
                        <td><img src="${entity.coverUrl}" width="50px;"/> </td>
                        <td>${(entity.releaseTime?string("yyyy-MM-dd"))!}</td>
                        <td>${entity.sequence}</td>
                        <td>${entity.catalog.name}</td>
                        <td>${(entity.stateName)!}</td>
                        <td>
                            <a class="btn btn-info btn-xs inputBtn"  data-json='${entity.json}'>
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
                <form class="form-horizontal" action="/strategyDetail/saveOrUpdate.do" method="post" id="editForm">

                    <input type="hidden" name="id">
                    <div class="form-group" >
                        <label class="col-sm-4 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">封面：</label>
                        <div class="col-sm-6">
                            <img id="coverImg" width="100%"/>
                            <input type="hidden"  name="coverUrl" >
                            <input type="file" class="form-control" name="file" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">所属攻略：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="strategySelect" >
                                <option value="-1">所有攻略</option>
                                <#list strategy as st>
                                    <option value="${st.id}">${st.title}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">攻略类别：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="catalogSelect"  name="catalog.id" >

                            </select>
                        </div>
                    </div>
                    <div class="form-group" >
                        <label class="col-sm-4 control-label">序号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="sequence" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="state" >
                                <option value="0">草稿</option>
                                <option value="1">发布</option>
                                <option value="-1">禁用</option>
                            </select>
                        </div>
                    </div>
                    <textarea id="textarea" name="strategyContent.content" cols="10" rows="80"></textarea>
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