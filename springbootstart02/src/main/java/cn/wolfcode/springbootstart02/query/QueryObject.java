package cn.wolfcode.springbootstart02.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Setter @Getter
public class QueryObject {
    private String keyword;
    private Long deptId = -1L; //根据部门做查询

    private Integer currentPage = 1;
    private Integer pageSize = 3;

    public int getStart(){
        return (currentPage - 1) * pageSize;
    }

    public String getKeyword() {
        return StringUtils.hasLength(keyword) ? keyword : null;
    }
}
