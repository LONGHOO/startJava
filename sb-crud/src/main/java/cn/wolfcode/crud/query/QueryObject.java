package cn.wolfcode.crud.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@Getter
@Setter
@ToString
public class QueryObject {

    private int currentPage = 1;
    private int pageSize = 3;
    private Long deptId = -1L;//根据部门查询
    private String keyword;
    private Integer maxAge;
    private Integer minAge;

    public String getKeyword() {
        return StringUtils.hasLength(keyword) ? keyword : null;
    }

    public int getStart() {
        return (currentPage - 1) * pageSize;
    }
}
