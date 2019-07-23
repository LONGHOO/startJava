package cn.wolfcode.crm.query;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class PageResult<T> {
    private List<T> list;
    private int totalCount;
    private int currentPage;
    private int pageSize;

    private int prevPage;
    private int nextPage;
    private int totalPage;

    public static PageResult EMPTY_PAGE_RESULT = new PageResult(Collections.EMPTY_LIST,0,1,5);
    public PageResult(List<T> list, int totalCount, int currentPage, int pageSize) {
        this.list = list;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        if (totalCount <= pageSize) {
            totalPage = 1;
            prevPage = 1;
            nextPage = 1;
            return;
        }

        this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        this.prevPage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
        this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
    }
}
