<div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
</div>
<script type="text/javascript">
    $(function(){
        $("#pagination").twbsPagination({
            totalPages:${result.pages} || 1,
            startPage:${result.pageNum} || 1,
            visiblePages: 5,
            first:"首页",
            prev:"上页",
            next:"下页",
            last:"尾页",
            onPageClick:function(event, page){
                //讲当前页设置page值
                $("#currentPage").val(page);
                //表单提交
                $("#searchForm").submit();
        }
    });

    });
</script>
