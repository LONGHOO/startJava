package cn.wolfcode.crud.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
public class PageResult {

    private int currentPage;
    private int pageSize;
    private int rows;
    private List<?> pageList;
    private int prevPage;
    private int nextPage;
    private int totalPage;

    public PageResult(int currentPage, int pageSize, int rows, List<?> pageList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.rows = rows;
        this.pageList = pageList;
        if (rows<=pageSize){
            prevPage=1;
            nextPage=1;
            totalPage=1;
            return;
        }
        totalPage= rows%pageSize == 0 ? rows/pageSize : rows/pageSize+1;
        prevPage = currentPage-1> 1?currentPage-1:1;
        nextPage = currentPage+1 < totalPage ? currentPage+1:totalPage;
    }

    public PageResult(int pageSize) {
        this(1,pageSize,0, Collections.EMPTY_LIST);
    }
}
