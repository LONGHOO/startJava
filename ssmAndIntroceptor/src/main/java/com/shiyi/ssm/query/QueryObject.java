package com.shiyi.ssm.query;

/**
 * @Author: 十一
 * @Date: 2019-04-19 21:50
 * @Descrption
 **/
public class QueryObject {

    private String keyword;
    private Integer currentPage=1;
    private Integer pageSize = 5;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStart(){
        return (this.currentPage - 1) * this.pageSize;
    }

    public void verifyCurrentPage(Integer rows){
        Integer totalPage = rows % this.pageSize > 0 ? rows / this.pageSize + 1 : rows / this.pageSize;
        if(this.currentPage > totalPage){
            this.currentPage = totalPage;
        }
    }
}
