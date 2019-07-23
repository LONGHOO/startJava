package cn.wolfcode.crud.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PageResult<T> {
    private int currentPage;
    private int pageSize;

    private  int rows;
    private List<T> result;

    private int prevPage;
    private int nextPage;
    private int totalPage;

    public PageResult(int currentPage, int pageSize, int rows, List<T> result) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.rows = rows;
        this.result = result;

        if(rows<=pageSize){
            totalPage=1;
            nextPage=1;
            prevPage=1;
            return;
        }
        totalPage=rows%pageSize==0?rows/pageSize:rows/pageSize+1;
        prevPage=currentPage>1?currentPage-1:1;
        nextPage=currentPage<totalPage?currentPage+1:totalPage;
    }
}
