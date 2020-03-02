package com.lihaiyang.learn.core.shrio;


import com.lihaiyang.learn.core.config.MobileProperties;
import com.lihaiyang.learn.core.utils.TokenUtils;
import com.lihaiyang.learn.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtRealm extends AuthorizingRealm {

    private MobileProperties mobileProperties;

    @Autowired
    protected IUserService userService;

    public JwtRealm(MobileProperties mobileProperties, JwtMatcher jwtMatcher) {
        this.mobileProperties = mobileProperties;
        this.setCredentialsMatcher(jwtMatcher);
    }

    @Override
    public Class<?> getAuthenticationTokenClass() {
        return JwtToken.class;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken)authcToken;
        String token = jwtToken.getToken();

        try {
            Claims claims = TokenUtils.parseJwt(token, this.mobileProperties.getJwtSecretKey());
            return new SimpleAuthenticationInfo(claims, claims, "jwtRealm");
        } catch (ExpiredJwtException var5) {
            throw new AuthenticationException("token已过期");
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        return null;
    }
}


