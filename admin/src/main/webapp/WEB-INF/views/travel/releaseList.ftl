<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#-- 包含其他文件的内容 -->
    <#include "../common/header.ftl"/>
    <script>
        $(function () {
            $(".showContentBtn").click(function() {
                var id = $(this).data("id");
                $.get("/travel/getContentById.do",{id:id},function(response){
                    $(".modal-body").html(response.content);
                    $("#showContentModal").modal('show');
                })
            })
            //取消发布
            $(".changeStateBtn").click(function () {
                var id = $(this).data("id");
                var state = $(this).data("state");
                $.get("/travel/changeState.do",{id:id,state:state},function (response) {
                    if(response.success){
                        resultHandle.saveHandle(response);
                    }else{
                        $.messager.alert("提示信息",response.msg);
                    }
                })
            })
            //推荐按钮点击事件
            $(".hotBtn").click(function () {
                var json = $(this).data("json");
                $("input[name=title]").val(json.title);
                $("input[name=coverUrl]").val(json.coverUrl);
                $("input[name='travel.id']").val(json.id);
                $("#coverUrl").attr("src",json.coverUrl);
                $("#hotModal").modal('show');
            })
            //保存
            $(".submitBtn").click(function () {
                $("#editForm").ajaxSubmit(function (response) {
                    if(response.success){
                        resultHandle.saveHandle(response);
                    }
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
            <#assign menu="releaseList"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">已发布游记管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travel/releaseList.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>地点</th>
                    <th>作者</th>
                    <th>发布时间</th>
                    <th>状态</th>
                    <th></th>
                </tr>

            <#-- 循环迭代指令 -->
                <#list pageInfo.list as entity>
                    <tr>
                        <td>${entity_index + 1}</td>
                        <td>${entity.title}</td>
                        <td><img src="${entity.coverUrl}" style="width:30px;"/></td>
                        <td>${entity.region.name}</td>
                        <td>${entity.author.nickName}</td>
                        <td>${entity.stateName}</td>
                        <td>
                            <a class="btn btn-info btn-xs showContentBtn" data-id="${entity.id}" >
                                <span class="glyphicon glyphicon-pencil"></span> 查看文章
                            </a>
                            <a class="btn btn-danger btn-xs changeStateBtn" data-id="${entity.id}" data-state="1" >
                                <span class="glyphicon glyphicon-pencil"></span> 取消发布
                            </a>
                            <a class="btn btn-danger btn-xs hotBtn" data-json='${entity.json}' data-state="-1">
                                <span class="glyphicon glyphicon-pencil" ></span> 推荐
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
<div class="modal fade" id="showContentModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">查看</h4>
            </div>
            <div class="modal-body">
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>



<!-- 模态框必须作为body的子标签 -->
<div class="modal fade" id="hotModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">推荐</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="/travelCommend/saveOrUpdate.do" method="post" id="editForm">
                    <!--关联的游记id-->
                    <input type="hidden" name="travel.id">
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
                            <img width="100%" id="coverUrl"/>
                        </div>
                        <input type="hidden" name="coverUrl"/>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label"></label>
                        <div class="col-sm-6">
                            <input type="file" name="file"/>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐时间：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="schedule" onclick="WdatePicker()" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">推荐类型：</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="type" >
                                <option value="0">每周推荐</option>
                                <option value="1">每月推荐</option>
                                <option value="2">攻略推荐</option>
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


<style>
    #showContentModal img{
        width:100%
    }
</style>

</body>
</html>