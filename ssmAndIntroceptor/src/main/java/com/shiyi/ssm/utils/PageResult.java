package com.shiyi.ssm.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-19 21:52
 * @Descrption
 **/
@Getter
@Setter

public class PageResult {

    private Integer firstPage = 1;

    private Integer prePage;

    private Integer nextPage;

    private Integer pageSize;

    private Integer totalPage;

    private Integer currentPage;

    private List<?> list;

    private Integer rows;

    public PageResult(Integer currentPage,Integer pageSize,List<?> list,Integer rows){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
        this.rows = rows;
        if(rows == 0){
            this.currentPage = 1;
            this.totalPage = 1;
            this.prePage = 1;
            this.nextPage = 1;
            this.rows = rows;
            return ;
        }
        this.totalPage = rows % pageSize > 0 ? rows / pageSize + 1:rows/pageSize;
        this.prePage = this.currentPage > 1 ? this.currentPage - 1:1;
        this.nextPage = this.currentPage < totalPage ? this.currentPage + 1:this.totalPage;
    }
}
