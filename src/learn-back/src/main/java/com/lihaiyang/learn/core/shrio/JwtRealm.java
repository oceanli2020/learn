package com.lihaiyang.learn.core.shrio;


import com.lihaiyang.learn.core.config.MobileProperties;
import com.lihaiyang.learn.core.utils.TokenUtils;
import com.lihaiyang.learn.core.utils.UserUtils;
import com.lihaiyang.learn.entity.Role;
import com.lihaiyang.learn.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

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
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roleList = UserUtils.getRoleList();
        info.setRoles((Set)roleList.parallelStream().map(Role::getEnname).collect(Collectors.toSet()));
        return info;
    }
}


