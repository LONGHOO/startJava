<div class="el-header" >
		<div class="container" style="position: relative;">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/main">首页</a></li>
				<#if LOGININFO_IN_SESSION.username??>
					<li>
						<a class="el-current-user" href="/personal">用户名:${LOGININFO_IN_SESSION.username}</a>
					</li>
					<li><a  href="/recharge">账户充值  </a></li>
					<li><a  href="/logout">注销 </a></li>
					<#else>
						<li><a href="/login.html">登录</a></li>
						<li><a href="/register.html">快速注册</a></li>
				</#if>
				<li><a href="#">帮助</a></li>
			</ul>
		</div>
</div>
