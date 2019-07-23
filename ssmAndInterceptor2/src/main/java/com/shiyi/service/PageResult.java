package com.shiyi.service;

import com.alibaba.druid.sql.PagerUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-04-20 17:48
 * @Descrption
 **/

@Getter
@Setter
public class PageResult {

    private Integer firstPage = 1;
    private Integer totalPage ;
    private Integer currentPage;
    private Integer pageSize;
    private Integer prePage;
    private Integer nextPage;
    private Integer rows;
    private List<?> list;


    public PageResult(Integer currentPage,Integer pageSize,List<?> list,Integer rows){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
        this.rows = rows;
        if(rows == 1){
            this.currentPage = 1;
            this.totalPage = 1;
            this.prePage = 1;
            this.nextPage = 1;
            return ;
        }
        this.totalPage = rows % pageSize > 0 ? rows / pageSize + 1 : rows / pageSize ;
        this.prePage = this.currentPage > 1 ? this.currentPage - 1: 1;
        this.nextPage = this.currentPage < this.totalPage ? this.currentPage + 1 : this.totalPage;

    }
}
