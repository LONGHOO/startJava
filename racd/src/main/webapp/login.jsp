<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>叩丁狼客户管理系统</title>
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/css/core.css" type="text/css" />
<script src="/js/jquery/jquery-2.1.4.min.js"></script>
<style type="text/css">
	body{
		background-color:  #FFFFFF;
	}
	.cm-container{
		margin-top: 160px;
	}
	.login {
		width: 360px;
		height: 300px;
		margin: 0px auto;
	}
</style>
<script type="text/javascript">
	$(function(){
		//ajax提交表单
        
		//按enter自动提交表单

	});		
</script>

</head>
<body>
	<div class="container cm-container">
		<h3 class="text-center"><label style="color: #337ab7;">叩丁狼客户管理系统(系统管理平台)</label></h3>
		<hr />
		<div class="login">
			<span style="color:red">${result.msg}</span>
			<form id="loginForm" action="/login.do" method="post">
				<div class="form-group form-group-lg">
					<div class="input-group">
						<div class="input-group-addon">账&emsp;号</div>
						<input class="form-control" name="username"  value="admin"/>
					</div>
				</div>
				<div class="form-group form-group-lg">
					<div class="input-group">
						<div class="input-group-addon">密&emsp;码</div>
						<input class="form-control" name="password" type="password" value="1"/>
					</div>
				</div>
				<div class="form-group">
					<button type="button" class="btn btn-lg btn-primary btn-block" id="btn_submit">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	$(function(){
		$("#btn_submit").click(function(){
			$("#loginForm").submit();
		})
	})
</script>
</html>