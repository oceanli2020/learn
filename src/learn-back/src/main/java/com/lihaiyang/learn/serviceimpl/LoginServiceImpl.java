package com.lihaiyang.learn.serviceimpl;

import javax.servlet.http.HttpServletRequest;

import com.lihaiyang.learn.core.utils.HttpUtils;
import com.lihaiyang.learn.core.utils.TokenUtils;
import com.lihaiyang.learn.core.utils.UserAgentUtils;
import com.lihaiyang.learn.service.ILoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {


    @Override
    public String getToken(String refreshToken, HttpServletRequest request) {
        if (StringUtils.isBlank(refreshToken)) {
            throw new UnauthorizedException("refreshToken验证失败");
        } else {
            TokenUtils.RefreshToken rToken = TokenUtils.parseRefreshToken(refreshToken);
            boolean isSameUa = rToken.getUserAgent().equals(HttpUtils.getUserUAInfo(rToken.getUserId()));
            long now = System.currentTimeMillis();
            boolean isNotExp = now < rToken.getExp();
            if (isSameUa && isNotExp) {
                return TokenUtils.createToken(rToken.getUserId(), UserAgentUtils.getDeviceType(request).getName());
            } else {
                throw new UnauthorizedException("refreshToken验证失败");
            }
        }
    }
}

