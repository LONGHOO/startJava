<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#-- 包含其他文件的内容 -->
    <#include "../common/header.ftl"/>
    <script>
        $(function () {
            $(".btn-default").click(function () {
                $("#searchForm").submit();
            })
            //添加按钮
            $(".inputBtn").click(function () {
                var json = $(this).data("json");
                if(json){
                    $("input[name=id]").val(json.id);
                    $("input[name=title]").val(json.title);
                    $("input[name=subTitle]").val(json.subTitle);
                    $("#coverImg").attr("src",json.coverUrl);
                    $("input[name=coverUrl]").val(json.coverUrl);
                    $("select[name=state]").val(json.state);
                    $("select[name='place.id']").val(json.placeId);

                }
                $("#inputModal").modal('show');
            })
            //保存
            $(".submitBtn").click(function () {
                $("#editForm").ajaxSubmit(function(response){
                    resultHandle.saveHandle(response, null, "保存成功");
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
            <#assign menu="strategy"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">大攻略管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/strategy/list.do" method="post">
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
                    <th>封面</th>
                    <th>攻略标题</th>
                    <th>副标题</th>
                    <th>所属地区</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>

            <#-- 循环迭代指令 -->
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index+1}</td>
                        <td><img src="${entity.coverUrl}" width="30px"/> </td>
                        <td>${entity.title}</td>
                        <td>${entity.subTitle}</td>
                        <td>${entity.place.name}</td>
                        <td>${entity.stateName}</td>
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
                <form class="form-horizontal" action="/strategy/saveOrUpdate.do" method="post" id="editForm">
                    <input type="hidden" name="id">
                    <div class="form-group" >
                        <label class="col-sm-4 control-label">标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="title" >
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">副标题：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="subTitle" >
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
                        <label class="col-sm-4 control-label">所属地区：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="place.id" >
                                <#list regions as r>
                                    <option value="${r.id}">${r.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">状态：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="state" >
                                <option value="0">普通</option>
                                <option value="-1">拒绝</option>
                                <option value="1">推荐</option>
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