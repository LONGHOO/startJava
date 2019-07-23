<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>蓝源Eloan-P2P平台(系统管理平台)</title>
	<#include "common/header.ftl"/>
	<script type="text/javascript" src="/js/plugins/jquery.form.js"></script>
	<script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
	<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js" ></script>

	<script type="text/javascript">
		$(function() {

			$('#pagination').twbsPagination({
				totalPages : ${pageInfo.pages}||1,
					startPage : ${pageInfo.pageNum},
			visiblePages : 5,
					first : "首页",
					prev : "上一页",
					next : "下一页",
					last : "最后一页",
					onPageClick : function(event, page) {
				$("#currentPage").val(page);
				$("#searchForm").submit();
			}
		});

			$("#query").click(function(){
				$("#currentPage").val(1);
				$("#searchForm").submit();
			});

			$(".return_money").click(function(){
				var useableAmount=parseFloat($("#useableAmount").val());
				var returnMoney=parseFloat($(this).data("returnmoney"));
				$.ajax({
					dataType:"json",
					type:"POST",
					url:"/returnMoney",
					data:{id:$(this).data("rid")},
					success:function(data){
						if(data.success){
							$.messager.confirm("提示","还款成功",function(){
								window.location.reload();
							});
						}else{
							$.messager.popup(data.msg);
						}
					}
				});
			});
		});
	</script>
</head>
<body>
<div class="container">
	<#include "common/top.ftl"/>
	<div class="row">
		<div class="col-sm-3">
			<#assign currentMenu="returnMoneyPage" />
			<#include "common/menu.ftl" />
		</div>
		<div class="col-sm-9">
			<div class="page-header">
				<h3>体验金管理</h3>
			</div>
			<form id="searchForm" class="form-inline" method="post" action="/expBidRequest_list">
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<div class="form-group">
					<label>状态</label>
					<select class="form-control" name="state" id="bidRequestState">
						<option value="-1">全部</option>
						<option value="1">登录失败</option>
						<option value="0">登录成功</option>
					</select>
					<script type="text/javascript">
						$("#state option[value=${qo.bidRequestState}]").attr("selected",true);
					</script>
				</div>
				<div class="form-group">
					<label>发布时间</label>
					<input class="form-control beginDate" type="text" name="beginDate" value='${(qo.beginDate?string("yyyy-MM-dd"))!""}'/>到
					<input class="form-control endDate" type="text" name="endDate" value='${(qo.endDate?string("yyyy-MM-dd"))!""}'/>
				</div>
			</form>
			<div class="panel panel-default">
				<div class="panel-heading">
					<span class="pull-left" style="line-height: 35px;">还款明细</span>
					<a class="pull-right btn btn-danger btn-sm" href="/recharge">
						账户充值
					</a>
					<div class="clearfix"></div>
				</div>
				<table class="table">
					<thead>
					<tr>
						<th>借款</th>
						<th>还款金额</th>
						<th>还款本金</th>
						<th>还款利息</th>
						<th>还款期数</th>
						<th>还款期限</th>
						<th>还款状态</th>
					</tr>
					</thead>
					<tbody>
					<#list pageInfo.list as data>
						<tr>
							<td><a href="/borrow_info?id=${data.bidRequestId}">${data.bidRequestTitle}</a></td>
							<td>${data.totalAmount}元</td>
							<td>${data.principal}元</td>
							<td>${data.interest}元</td>
							<td>${data.monthIndex}期</td>
							<td>${data.deadLine?string("yyyy-MM-dd")}</td>
							<td>
								<#if data.state=0>
									<a class="btn btn-success return_money" data-returnmoney="${data.totalAmount?string('0.##')}" data-rid="${data.id}">立刻还款</a>
								<#else>
									${data.stateDisplay}
								</#if>
							</td>
						</tr>
					</#list>
					</tbody>
				</table>
				<div style="text-align: center;">
					<ul id="pagination" class="pagination"></ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>