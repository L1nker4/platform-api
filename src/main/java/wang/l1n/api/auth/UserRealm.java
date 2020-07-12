package wang.l1n.api.auth;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.l1n.api.dao.UserRepository;
import wang.l1n.api.entity.User;
import wang.l1n.api.entity.common.ConstantVariable;
import wang.l1n.api.utils.CacheUtil;
import wang.l1n.api.utils.ForestUtil;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 19:36
 * @description： Shiro Realm认证
 */

@Slf4j
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        String encryptToken = ForestUtil.encryptToken(token);
        String redisTemp = null;
        String username = JWTUtil.getUsername(token);
        try {
            redisTemp = cacheUtil.get(ConstantVariable.USER_CACHE_PREFIX + username);
        } catch (Exception e) {
            //找不到说明已经失效
            log.error(e.getMessage());
        }
        if (StringUtils.isBlank(redisTemp)) {
            throw new AuthenticationException("token已过期");
        }

        if (StringUtils.isBlank(username)) {
            throw new AuthenticationException("token校验不通过");
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        if (!JWTUtil.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("token校验不通过");
        }
        return new SimpleAuthenticationInfo(token, token, "shiroRealm");
    }
}
