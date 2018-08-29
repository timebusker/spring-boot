package com.timebusker.oauth2;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 认证
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-20 14:00
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2Realm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("******************************\t\t 开始授权 \t\t******************************");

        LOGGER.info("******************************\t\t 授权结束 \t\t******************************");
        return null;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOGGER.info("******************************\t\t 开始认证 \t\t******************************");

        LOGGER.info("******************************\t\t 认证结束 \t\t******************************");
        return null;
    }
}
