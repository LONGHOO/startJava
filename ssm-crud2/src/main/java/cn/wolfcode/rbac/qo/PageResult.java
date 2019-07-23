package cn.wolfcode.rbac.qo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResult<T> {
    private Integer pageSize;
    private Integer currentPage;

    private Integer rows;
    private List<T> list;

    private Integer totalPage;
    private Integer prevPage;
    private Integer nextPage;

    public PageResult() {
    }

    public PageResult(Integer pageSize, Integer currentPage, Integer rows, List<T> list) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.rows = rows;
        this.list = list;
        if (rows == 0) {
            this.totalPage = 1;
            this.prevPage = 1;
            this.nextPage = 1;
            return;
        }
        this.totalPage = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
        this.prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
    }
}
