<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <#-- 包含其他文件的内容 -->
    <#include "../common/header.ftl"/>
    <script>
        $(function () {
            //搜索
            $("#search").click(function () {
                $("#editForm").submit();
            })
            //修改事件
            $(".inputBtn").click(function () {
                var json = $(this).data("json");
                $("input[name=id]").val(json.id);
                $("input[name=title]").val(json.title);
                $("input[name=subTitle]").val(json.subTitle);
                $("input[name=coverUrl]").val(json.coverUrl);
                $("input[name=schedule]").val(json.schedule);
                $("#coverUrl").attr("src",json.coverUrl);
                $("input[name=type]").val(json.type);
                $("#travelRelease").attr("href", "/travel/releaseList.do?travelId=" + json.id);
                $("#hotModal").modal('show');
            })
            //保存
            $(".submitBtn").click(function () {
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
            <#assign menu="travelCommend"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">游记推荐管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/travelCommend/list.do" method="post">
                <input type="hidden" name="currentPage" id="currentPage" value="1">
                <div class="form-group">
                    <label>推荐类型:</label>
                    <select class="form-control" name="type" id="type" >
                        <option value="-1">全部</option>
                        <option value="0">每周推荐</option>
                        <option value="1">每月推荐</option>
                        <option value="2">攻略推荐</option>
                    </select>
                  <script>
                      $("#type").val(${qo.type})
                  </script>
                </div>
                <button  class="btn btn-default" id="search">
                    <span class="glyphicon glyphicon-search"></span> 查询
                </button>
            </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>编号</th>
                    <th>封面</th>
                    <th>标题</th>
                    <th>副标题</th>
                    <th>推荐时间安排</th>
                    <th>推荐类型</th>
                    <th>操作</th>
                </tr>

            <#-- 循环迭代指令 -->
                <#list pageInfo.list as entity>
                    <tr>
                       <td>${entity_index + 1}</td>
                       <td><img src="${entity.coverUrl}" width="30px"/></td>
					   <td>${entity.title}</td>
					   <td>${entity.subTitle}</td>
					   <td>${entity.schedule?string('yyyy-HH-dd')}</td>
					   <td>${(entity.typeName)!}</td>
                        <td>
                            <a class="btn btn-info btn-xs inputBtn"  data-json='${(entity.json)!}'>
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
                    <!--游记推荐的id-->
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
                           <img id="coverUrl" src="" width="100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label"></label>
                        <div class="col-sm-6">
                            <input type="file" name="file"/>
                        </div>
                    </div>
                    <input type="hidden" name="coverUrl"/>
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

                    <div class="form-group" style="text-align: center">
                        <a id="travelRelease" target="_blank">点击查看游记文章明细</a>
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