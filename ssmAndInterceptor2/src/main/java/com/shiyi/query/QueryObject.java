package com.shiyi.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: 十一
 * @Date: 2019-04-20 17:28
 * @Descrption
 **/
@Getter
@Setter
public class QueryObject {

    private String keyWord;
    private Integer currentPage = 1;
    private Integer pageSize = 5;

    public Integer getStart(){
        return (this.currentPage - 1) * pageSize;
    }

    public void verifyCurrentPage(Integer rows){
        int totalPage = rows % pageSize > 0 ? rows / pageSize + 1 : rows / pageSize ;
        if(rows == 0){
            this.currentPage = 1;
        }
        if(currentPage > totalPage){
            this.currentPage = totalPage;
        }
    }
}
