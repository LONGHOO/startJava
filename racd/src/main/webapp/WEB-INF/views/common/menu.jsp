<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul id="menu" class="list-group">
	<li class="list-group-item">
		<a href="javascript:" list-toggle="collapse" list-target="#strategy_detail">
			<span>系统管理</span>
		</a>
		<ul class="in" id="strategy_detail">
			<li class="department"><a href="/department/list.do">部门管理</a></li>
            <li class="employee"><a href="/employee/list.do">员工管理</a></li>
            <li class="permission"><a href="/permission/list.do">权限管理</a></li>
            <li class="role"><a href="/role/list.do">角色管理</a></li>
		</ul>
	</li>
</ul>

<script type="text/javascript">
    $(".in li.${menu}").addClass("active");
</script>
