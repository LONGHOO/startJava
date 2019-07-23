package cn.wolfcode.crud.query;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class QueryObject {
    private  String keyWord;
    private  Long deptId = -1L;
    private  int currentPage = 1;
    private int pageSize = 3;

    public int getStart(){
        return (currentPage-1)*pageSize;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
