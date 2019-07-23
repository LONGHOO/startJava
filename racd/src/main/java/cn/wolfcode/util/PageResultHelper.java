package cn.wolfcode.util;

import cn.wolfcode.crud.domain.Department;
import cn.wolfcode.crud.query.PageResult;
import cn.wolfcode.crud.query.QueryObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 十一
 * @Date: 2019-05-04 19:37
 * @Descrption
 **/
public class PageResultHelper {

    private PageResultHelper() {
    }

    /**
     *
     * 功能描述: 通过反射的方法来获取进行分页查询
     * @param:
     * @return:
     * @auther: 十一
     * @date:
     */
    public static PageResult getPageResult(Object mapper,QueryObject qo) {
        Class<?> clazz = mapper.getClass();
        try {
            Method method = clazz.getMethod("queryForCount", qo.getClass());
            int rows = 0;
            if(method != null){
                rows = (Integer) method.invoke(mapper, qo);
                if(rows == 0){
                    return new PageResult(qo.getCurrentPage(), qo.getPageSize(), rows, Collections.emptyList());
                }
            }
            Method queryForList = clazz.getMethod("queryForList", qo.getClass());
            if(queryForList != null){
                List<?> list  = (List<?>) queryForList.invoke(mapper, qo);
                return new PageResult(qo.getCurrentPage(),qo.getPageSize(),rows,list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
