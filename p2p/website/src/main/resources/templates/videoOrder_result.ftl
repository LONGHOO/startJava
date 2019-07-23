<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>蓝源Eloan-P2P平台</title>
		<#include "common/links-tpl.ftl" />
		<link type="text/css" rel="stylesheet" href="/css/account.css" />
		<script type="text/javascript" src="/js/plugins/jquery.twbsPagination.min.js"></script>
		<script type="text/javascript" src="/js/plugins-override.js"></script>
		<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
		<style type="text/css">
			.bg-success{
				padding: 10px;
				font-size: 16px;
			}
		</style>
	</head>
	<body>
		<!-- 网页顶部导航 -->
		<#include "common/head-tpl.ftl" />
		<!-- 网页导航 -->
		<#assign currentNav="personal" />
		<#include "common/navbar-tpl.ftl" />
		
		<div class="container">
			<div class="row">
				<!--导航菜单-->
				<div class="col-sm-3">
					<#assign currentMenu="videoAuditOrder" />
					<#include "common/leftmenu-tpl.ftl" />		
				</div>
				<!-- 功能页面 -->
				<div class="col-sm-9">
					<#if auditing>
                        <p class="bg-success">预约成功：预约客服 [ ${videoAuth.auditor.username} ] ,时间 ：[ ${videoAuth.orderBeginDate?string("yyyy-MM-dd HH:mm")} -  ${videoAuth.orderEndDate?string("yyyy-MM-dd HH:mm")} ]
                            <a>撤销预约</a>
                        </p>
					<#else>
                        <p class="bg-success">您已经通过视频认证
                        </p>
					</#if>
				</div>
			</div>
		</div>		
		<#include "common/footer-tpl.ftl" />
	</body>
</html>