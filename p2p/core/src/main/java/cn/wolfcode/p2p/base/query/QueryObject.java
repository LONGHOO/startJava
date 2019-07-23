package cn.wolfcode.p2p.base.query;

import cn.wolfcode.p2p.base.util.DateUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataUnit;

import java.util.Date;

@Getter
@Setter
@ToString
public class QueryObject {

    private int currentPage = 1;
    private int pageSize = 10;
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Date getEndDate() {
        return DateUtil.getEndDate(endDate);
    }

    public String getKeyword() {
        return StringUtils.hasLength(keyword) ? keyword : null;
    }
}
