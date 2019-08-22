package com.zzj.security.shiro;

import com.zzj.entity.User;
import com.zzj.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Objects;


/**
 * Created by Administrator on 2018/8/14.
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);


    @Autowired
    private UserService userService;
//    @Autowired
//    private ShiroUserManager userManager;
//    private String authorizationCacheName;
//    private boolean cachingEnabled;
//    private boolean authorizationCachingEnabled;
//    public ShiroRealm(UserService userService) {
//        this.setCredentialsMatcher(userManager.getMatcher());
//        this.userService = userService;
//        this.setAuthenticationCacheName("authenticationCacheName");
//        this.setAuthenticationCachingEnabled(true);
//        this.setAuthorizationCachingEnabled(true);
//        this.setCachingEnabled(true);
//    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("check authorization info");

        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        // 获取当前用户
        User user = (User) principals.getPrimaryPrincipal();
        // 查询角色信息
//        List<RoleInfo> roleInfos = userManager.getRoles(userInfo.getUsername());
//
//        if (roleInfos != null) {
//            for (RoleInfo roleInfo : roleInfos) {
//
//                authInfo.addRole(roleInfo.getRoleName());
//
//                if (roleInfo.getPerms() != null) {
//                    for (String perm : roleInfo.getPerms()) {
//                        authInfo.addStringPermission(perm);
//                    }
//                }
//            }
//        }

        return authInfo;
    }

    /**
     * 这里不thorw出去，直接进行判断
     *
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        logger.info("doGetAuthenticationInfo authentication info");
        String username = (String) token.getPrincipal();
        try {
            // 获取用户信息
            User user = userService.getByUsername(username);
            if (ObjectUtils.isEmpty(user)) {
                return null;
            }
            if(Objects.isNull(user)){
                return null;

            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
            authenticationInfo = new SimpleAuthenticationInfo(user, user.getP(),
                    ByteSourceUtils.bytes(user.getU()), getName());
            return authenticationInfo;
        } catch (UnknownAccountException ex) {
            logger.info("用户名没有找到");
            throw new UnknownAccountException("用户名没有找到");
        } catch (IncorrectCredentialsException ex) {
            logger.info("用户名密码不匹配");
            throw new IncorrectCredentialsException("用户名密码不匹配");
        } catch (AuthenticationException e) {
            logger.info("服务器错误");
            throw new AuthenticationException("服务器错误");
        }
    }
}
