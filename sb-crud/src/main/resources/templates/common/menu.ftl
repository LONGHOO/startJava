<ul id="menu" class="list-group">
	<li class="list-group-item">
		<a href="javascript:" list-toggle="collapse" list-target="#strategy_detail">
			<span>系统管理</span>
		</a>
		<ul class="in" id="strategy_detail">
			<li class="department"><a href="/department/list">部门管理</a></li>
            <li class="employee"><a href="/employee/list">员工管理</a></li>
            <li class="permission"><a href="/permission/list">权限管理</a></li>
            <li class="role"><a href="/role/list">角色管理</a></li>
		</ul>
        <a href="javascript:" list-toggle="collapse" list-target="#systemdictionry">
            <span>数据管理</span>
        </a>
        <ul class="in" id="systemdictionry">
            <li class="systemdictionary"><a href="/systemdictionary/list">字典目录</a></li>
            <li class="systemdictionaryItem"><a href="/systemdictionaryItem/list">字典明细</a></li>
        </ul>
        <a href="javascript:" list-toggle="collapse" list-target="#customerManager">
            <span>客户管理</span>
        </a>
        <ul class="in" id="customerManager">
            <li class="customer"><a href="/customer/potentialList">潜在客户</a></li>
            <li class="poolCustomer"><a href="/customer/poolList">客户池</a></li>
            <li class="failList"><a href="/failList/list">失败客户</a></li>
        </ul>
        <a href="javascript:" list-toggle="collapse" list-target="#customerHistory">
            <span>客户历史</span>
        </a>
        <ul class="in" id="customerHistory">
            <li class="customerTraceHistory"><a href="/customertracehistory/list">跟进历史</a></li>
            <li class="customerTransferHistory"><a href="/customertransfer/list">移交历史</a></li>
        </ul>
        <a href="javascript:" list-toggle="collapse" list-target="#chartManager">
            <span>报表统计</span>
        </a>
        <ul class="in" id="chartManager">
            <li class="customerChart"><a href="/customerChart/list">潜在客户报表</a></li>
        </ul>
	</li>
</ul>

<script type="text/javascript">
    $(".in li.${(menu)!}").addClass("active");
</script>
