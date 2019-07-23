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
            //发布/拒绝
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
        })
    </script>
</head>
<body>

<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#-- 往数据模型中填充一个属性 -->
            <#assign menu="travel"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">待审核游记管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travel/audit.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>状态:</label>
                    <select class="form-control" name="state" id="stateSelect" >
                        <option value="1">待审核</option>
                        <option value="-1">已拒绝</option>
                    </select>
                    <script>
                        $("#stateSelect").val(${qo.state})
                    </script>
                </div>
                <button id="btn_query" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span> 查询
                </button>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>标题</th>
                    <th>封面</th>
                    <th>地点</th>
                    <th>作者</th>
                    <th>状态</th>
					<th>操作</th>
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
                            <a class="btn btn-info btn-xs showContentBtn"  data-id="${entity.id}">
                                <span class="glyphicon glyphicon-pencil"></span> 查看文章
                            </a>
                            <a class="btn btn-danger btn-xs changeStateBtn"  data-id="${entity.id}" data-state="2">
                                <span class="glyphicon glyphicon-pencil"></span> 发布
                            </a>
                            <a class="btn btn-danger btn-xs changeStateBtn" data-id="${entity.id}" data-state="-1">
                                <span class="glyphicon glyphicon-pencil"></span> 拒绝
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


<style>
    #showContentModal img{
        width:100%
    }
</style>

</body>
</html>