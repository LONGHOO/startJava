<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>潜在客户管理</title>
    <#include "../common/link.ftl">
    <script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn_query").click(function () {
                $("#searchForm").submit();
            })
            $(".inputBtn").click(function () {
                var data = $(this).data("json");
                $("#editForm")[0].reset();
                if(data){
                    $("input[name=id]").val(data.id);
                    $("input[name=name]").val(data.name);
                    $("input[name=age]").val(data.age);
                    $("select[name=gender]").val(data.gender);
                    $("input[name=tel]").val(data.tel);
                    $("input[name=qq]").val(data.qq);
                    $("select[name='job.id']").val(data.job);
                    $("select[name='source.id']").val(data.source);
                    $("select[name='seller.id']").val(data.seller);
                }
                $("#editModal").modal('show');
            })

            $(".inputSubmit").click(function () {
                $("#editForm").ajaxSubmit(function (data) {
                    resultHandle.saveHandle(data);
                })
            })
            $(".statusBtn").click(function () {
                var data = $(this).data("json");
                $("input[name=customerId]").val(data.id);
                $("#statusModal").modal('show');
            })
            $(".statusSubmit").click(function () {

                $("#statusForm").ajaxSubmit(function (data) {
                    resultHandle.saveHandle(data);
                })
            })

            $(".traceBtn").click(function () {
                var data = $(this).data("json");
                $("input[name=name]").val(data.name);
                $("input[name=type]").val(data.status);
                $("input[name='customer.id']").val(data.id);
                $("#traceModal").modal('show');
            })
            $(".traceSubmit").click(function () {
                $("#traceForm").ajaxSubmit(function (data) {
                    resultHandle.saveHandle(data);
                })
            })

            $(".transferBtn").click(function () {
                var data = $(this).data('json');
                $("#transferForm")[0].reset();
                if(data){
                    $("#customerTransferId").val(data.id);
                    $("input[name='customer.name']").val(data.name);
                    $("input[name='customer.id']").val(data.id);
                    $("input[name='oldSeller.id']").val(data.seller);
                    $("input[name='oldSeller.name']").val(data.sellerName);
                }

                $("#transferModal").modal('show');
            })

            $(".transferSubmit").click(function () {
              $("#transferForm").ajaxSubmit(function (data) {
                  resultHandle.saveHandle(data);
              })
            })


        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="customer_potential">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>潜在客户管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/customer/potentialList.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!''}" placeholder="请输入姓名/电话">
                        </div>
                        <@shiro.hasAnyRoles name="admin,Market_Manager">
                            <div class="form-group">
                                <label for="seller">市场专员:</label>
                                <select class="form-control" id="seller" name="sellerId">
                                    <option value="-1">全部</option>
                                    <#list sellers as e>
                                        <option value="${e.id}">${e.name}</option>
                                    </#list>
                                </select>
                                <script>
                                    $("#seller option[value='${(qo.sellerId)!}']").prop("selected", true);
                                </script>
                            </div>
                        </@shiro.hasAnyRoles>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                        <a href="JavaScript:;" class="btn btn-success inputBtn"><span class="glyphicon glyphicon-plus"></span>  添加</a>
                    </form>
                </div>
                <table class="table table-striped table-hover" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>名称</th>
                        <th>电话</th>
                        <th>QQ</th>
                        <th>职业</th>
                        <th>来源</th>
                        <th>销售员</th>
                        <th>录入时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                   <#list pageInfo.list as entity>
                       <tr>
                           <td>${entity_index+1}</td>
                           <td>${entity.name}</td>
                           <td>${entity.tel}</td>
                           <td>${entity.qq}</td>
                           <td>${(entity.job.title)!''}</td>
                           <td>${(entity.source.title)!''}</td>
                           <td>${(entity.seller.name)!''}</td>
                           <td>${(entity.inputTime)?string("yyyy-MM-dd HH:mm:ss")}</td>

                           <td>
                               <a class="btn btn-info btn-xs inputBtn"  data-json='${(entity.json)}'>
                                   <span class="glyphicon glyphicon-pencil"></span>编辑
                               </a>

                               <a class="btn btn-primary btn-xs traceBtn"  data-json='${entity.json}'>
                                   <span class="glyphicon glyphicon-pencil"></span>跟进
                               </a>
                               <@shiro.hasAnyRoles name="admin,Market_Manager">
                               <a class="btn btn-warning btn-xs transferBtn"  data-json='${entity.json}'>
                                   <span class="glyphicon glyphicon-pencil"></span>移交
                               </a>
                               </@shiro.hasAnyRoles>
                               <a class="btn btn-danger btn-xs statusBtn"  data-json='${entity.json}'>
                                   <span class="glyphicon glyphicon-pencil"></span>修改状态
                               </a>
                           </td>
                       </tr>
                    </#list>
                </table>
                <#--分页-->
                <#include "../common/page.ftl"/>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
<#include  "potential_modal.ftl"/>

</body>
</html>
