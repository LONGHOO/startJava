<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客户报表</title>
    <#include "../common/link.ftl">
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/echarts/echarts.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btn_query").click(function () {
              $("#searchForm").submit();
            })

            $(".chart_btn").click(function () {
                var url = $(this).data("url");
                $("#chartModal").removeData("bs.modal");
                $("#chartModal").modal({
                    remote:url+"?"+$("#searchForm").serialize()
                })
            })
        });
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="customerReport">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>潜在客户报表</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/customerReport/reportList.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">员工姓名:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!""}" placeholder="请输入姓名">
                        </div>
                        <div class="form-group">
                            <label for="date">时间段查询:</label>
                            <input type="text" class="form-control" style="width: 100px" id="beginTime" name="beginDate" onclick="WdatePicker({readOnly:true})" value="${(qo.beginDate?string('yyyy-MM-dd'))!""}"> -
                            <input type="text" class="form-control" style="width: 100px" id="endTime" name="endDate" onclick="WdatePicker({readOnly:true})" value="${(qo.endDate?string('yyyy-MM-dd'))!""}">
                        </div>
                        <div class="form-group">
                            <label for="status">分组类型:</label>
                            <select class="form-control" id="groupType" name="groupType">
                                <option value="e.name">员工</option>
                                <option value="DATE_FORMAT(c.input_time,'%Y')">年</option>
                                <option value="DATE_FORMAT(c.input_time,'%Y-%m')">月</option>
                                <option value="DATE_FORMAT(c.input_time,'%Y-%m-%d')">日</option>
                            </select>
                            <script>
                                $("#groupType").val("${qo.groupType}");
                            </script>
                            <button id="btn_query" class="btn btn-primary">
                                <span class="glyphicon glyphicon-search"></span> 查询
                            </button>
                            <button type="button" class="btn btn-info chart_btn" data-url="/customerReport/chartByBar.do">
                                <span class="glyphicon glyphicon-stats"></span> 柱状图
                            </button>
                            <button type="button" class="btn btn-warning chart_btn"  data-url="/customerReport/chartByPie.do">
                                <span class="glyphicon glyphicon-dashboard"></span> 饼状图
                            </button>
                        </div>
                    </form>
                </div>
                <table class="table table-striped table-hover" >
                    <thead>
                    <tr>
                        <th>分组类型</th>
                        <th>潜在客户新增数</th>
                    </tr>
                    </thead>
                   <#list pageInfo as entity>
                       <tr>
                           <td>${entity.groupType}</td>
                           <td>${entity.number}</td>
                       </tr>
                   </#list>
                </table>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
<div id="chartModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">

        </div>
    </div>
</div>

</body>
</html>
