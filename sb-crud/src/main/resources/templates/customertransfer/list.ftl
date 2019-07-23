<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<script>

</script>
<body>
<div class="container " style="margin-top: 20px">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
        <#--设置当前要回显当前菜单,必须在载人菜单前完成设置-->
            <#assign menu="customerTransferHistory"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">跟进历史管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <form class="form-inline" id="searchForm" action="/customertransfer/list" method="post">
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
                    <th>操作人</th>
                    <th>操作日期</th>
                    <th>旧营销人员</th>
                    <th>新营销人员</th>
                    <th>移交原因</th>
                </tr>
                <#list result.list as entity>
                    <tr>
                        <td>${entity_index+1}</td>
                        <td>${entity.customer.name}</td>
                        <td>${entity.operator.name}</td>
                        <td>${(entity.operateTime)?string("yyyy-MM-dd")}</td>
                        <td>${(entity.oldseller.name)!}</td>
                        <td>${entity.newseller.name}</td>
                        <td>${entity.reason}</td>
                    </tr>
                </#list>
            </table>
            <#include "../common/page.ftl"/>
        </div>
    </div>
</div>
</body>
</html>