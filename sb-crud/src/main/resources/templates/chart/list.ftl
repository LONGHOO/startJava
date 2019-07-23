<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <#include "../common/header.ftl"/>
</head>
<script>
    $(function () {
        $(".btn-chart").click(function () {
            //清空模态框的缓存数据
            $("#chartModal").removeData("bs.modal");
            //console.log($("#searchForm").serialize());
            var url = $(this).data("url");
            $("#chartModal").modal({
                remote:url+"?"+$("#searchForm").serialize()
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
            <#assign menu="customerChart"/>
            <#include "../common/menu.ftl"/>
        </div>
        <div class="col-sm-9">
            <div class="row">
                <div class="col-sm-12">
                    <h1 class="page-head-line">潜在客户报表管理</h1>
                </div>
            </div>
            <!--高级查询--->
            <!--高级查询--->
            <div style="margin: 10px;">
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/customerChart/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <div class="form-group">
                        <label for="keyword">员工姓名:</label>
                        <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!}" placeholder="请输入姓名">
                    </div>
                    <div class="form-group">
                        <label for="date">时间段查询:</label>
                        <input type="text" class="form-control" style="width: 100px" id="beginTime" name="beginDate" onclick="WdatePicker()" value="${((qo.beginDate)?string("yyyy-MM-dd"))!}"> -
                        <input type="text" class="form-control" style="width: 100px" id="endTime" name="endDate" onclick="WdatePicker()" value="${((qo.endDate)?string("yyyy-MM-dd"))!}">
                    </div>
                    <div class="form-group">
                        <label for="status">分组类型:</label>
                        <select class="form-control" id="groupType" name="groupType">
                            <option value="seller.name">
                                员工
                            </option>
                            <option value="DATE_FORMAT(c.input_time,'%Y')">
                                年
                            </option>
                            <option value="DATE_FORMAT(c.input_time,'%Y-%m')">
                                月
                            </option>
                            <option value="DATE_FORMAT(c.input_time,'%Y-%m-%d')">
                                日
                            </option>
                        </select>
                        <script>
                            $("#groupType").val("${qo.groupType}")
                        </script>
                        <button id="btn_query" class="btn btn-primary">
                            <span class="glyphicon glyphicon-search"></span> 查询
                        </button>
                        <button type="button" class="btn btn-info btn-chart" data-url="/customerChart/chartByBar">
                            <span class="glyphicon glyphicon-stats"></span> 柱状图
                        </button>
                        <button type="button" class="btn btn-warning btn-chart"  data-url="/customerChart/chartByPie">
                            <span class="glyphicon glyphicon-dashboard"></span> 饼状图
                        </button>
                    </div>
                </form>

            <table class="table table-striped table-hover" >
                <tr>
                    <th>分组类型</th>
                    <th>潜在客户数量</th>
                </tr>
                <#list charts as c>
                    <tr>
                       <td>${c.groupType}</td>
                       <td>${c.number}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
</div>

    <div id="chartModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">

            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</body>
</html>