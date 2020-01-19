package com.lihaiyang.learn.core.shrio;


import com.lihaiyang.learn.core.account.AccountLocals;
import com.lihaiyang.learn.entity.User;
import com.lihaiyang.learn.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class JwtMatcher implements CredentialsMatcher {

    @Autowired
    protected IUserService userService;

    public JwtMatcher() {
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Claims claims = (Claims)info.getCredentials();
        try {
            User user = (User)this.userService.getById(Long.valueOf(claims.getSubject()));
            if (user != null) {
                AccountLocals.setAccount(user);
                return true;
            } else {
                return false;
            }
        } catch (SignatureException var5) {
            throw new AuthenticationException("jwt的secretKey无效");
        } catch (ExpiredJwtException var6) {
            throw new AuthenticationException("token过期");
        } catch (Exception var7) {
            throw new AuthenticationException("token无效");
        }
    }
}

