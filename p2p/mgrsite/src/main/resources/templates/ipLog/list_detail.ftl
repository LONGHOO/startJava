<#list pageInfo.list as item>
    <tr>
        <td>${item.username}</td>
        <td>${item.logintime?string("yyyy-MM-dd HH:mm:ss")}</td>
        <td>${item.ip}</td>
        <td>${item.stateDisplay}</td>
        <td>${item.userTypeDisplay}</td>
    </tr>
</#list>


<script type="text/javascript">
    $(function () {
        debugger;
        $("#page_container").empty().append($('<ul id="pagination" class="pagination"></ul>'));
        $("#pagination").twbsPagination({
            totalPages:${pageInfo.pages},
            currentPage:${pageInfo.pageNum},
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                $("#currentPage").val(page);
                $("#searchForm").submit();
            },
            first: "首页",
            prev: "上页",
            next: "下页",
            last: "尾页"
        });
    });
</script>