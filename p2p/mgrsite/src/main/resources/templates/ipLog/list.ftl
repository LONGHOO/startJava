<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>蓝源Eloan-P2P平台(系统管理平台)</title>
    <#include "../common/header.ftl"/>
    <script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>

    <script type="text/javascript">

        $(function () {
            <#--$('#pagination').twbsPagination({ totalPages: 35,-->
            <#--visiblePages: 5,-->
            <#--startPage:${pageInfo.pageNum}||1,-->
            <#--totalPages:${pageInfo.pages},-->
            <#--onPageClick: function (event, page) {-->
            <#--$("#currentPage").val(page);-->
            <#--getDetail();-->
            <#--},-->
            <#--first:"首页" ,-->
            <#--prev:"上页" ,-->
            <#--next:"下页" ,-->
            <#--last:"尾页"-->
            <#--});-->

            $("#searchForm").ajaxForm(function (data) {
                // $("#page_container").html('<ul id="pagination" class="pagination"></ul>');
                $("#tbody").html(data);
            });
            $("#searchForm").submit();
            $("#query").click(function () {
                $("#searchForm").submit();
            })
        })
        ;
    </script>
</head>
<body>
<div class="container">
    <#include "../common/top.ftl"/>
    <div class="row">
        <div class="col-sm-3">
            <#include "../common/menu.ftl" />
        </div>
        <div class="col-sm-9">
            <div class="page-header">
                <h3>登录日志查询</h3>
            </div>
            <form id="searchForm" class="form-inline" method="post" action="/logDetail">
                <input type="hidden" id="currentPage" name="currentPage" value="${qo.currentPage!'1'}"/>
                <div class="form-group">
                    <label>状态</label>
                    <select class="form-control" name="state" id="state">
                        <option value="-1">全部</option>
                        <option value="0">登录失败</option>
                        <option value="1">登录成功</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>登陆时间</label>
                    <input class="form-control beginDate" type="text" onclick="WdatePicker()" name="beginDate"
                           value='${(qo.beginDate?string("yyyy-MM-dd"))!""}'/>到
                    <input class="form-control endDate" type="text" onclick="WdatePicker()" name="endDate"
                           value='${(qo.endDate?string("yyyy-MM-dd"))!""}'/>
                </div>
                <div class="form-group">
                    <label>用户类型</label>
                    <select class="form-control" name="userType" id="userType">
                        <option value="-1">全部</option>
                        <option value="1">管理员</option>
                        <option value="0">普通用户</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>用户名</label>
                    <input class="form-control" type="text" name="keyword" value='${(qo.keyword)!""}'/>
                </div>
                <div class="form-group">
                    <button id="query" type="submit" class="btn btn-success"><i class="icon-search"></i> 查询</button>
                </div>

                <script type="text/javascript">
                    $("#state").find("option[value='${(qo.state)!"-1"}']").attr("selected", true);
                    $("#userType").find("option[value='${(qo.userType)!"-1"}']").attr("selected", true);
                </script>
            </form>
            <div class="panel panel-default">
                <table class="table">
                    <thead>
                    <tr>
                        <th>用户</th>
                        <th>登录时间</th>
                        <th>登录ip</th>
                        <th>登录状态</th>
                        <th>用户类型</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">

                    </tbody>
                </table>
                <div style="text-align: center;" >
                    <ul id="pagination" class="pagination"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>