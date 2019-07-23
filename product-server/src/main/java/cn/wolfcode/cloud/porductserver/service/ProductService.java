package cn.wolfcode.cloud.porductserver.service;

import cn.wolfcode.cloud.domain.Product;
import cn.wolfcode.cloud.porductserver.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 十一
 * @Date: 2019-07-09 17:16
 * @Descrption
 **/
@Service
public class ProductService {

    @Autowired
    private ProductDao dao;

    public Product get(int id){
        return dao.get(id);
    }
}
