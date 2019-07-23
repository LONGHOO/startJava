import cn.wolfcode.crm.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: 十一
 * @Date: 2019-05-10 21:43
 * @Descrption
 **/
@Getter
@Setter
@ToString
public class Apple extends BaseDomain {

    private String name;
    private String email;

}
