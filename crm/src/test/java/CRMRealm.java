import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CRMRealm extends AuthorizingRealm {

    //获取认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //暂且使用假数据来模拟真实的账号和密码
        String username = "zhangsan";
        String password = "zhangsan"; //如果账号正确，返回一个AuthenticationInfo对象 if(username.equals(token.getPrincipal())){
        return new SimpleAuthenticationInfo(username, password, this.getName());//账号 password,//密码
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
