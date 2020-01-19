package com.lihaiyang.learn.core.utils;


import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


public class UserAgentUtils {


    public static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

    public static DeviceType getDeviceType(HttpServletRequest request) {
        return getUserAgent(request).getOperatingSystem().getDeviceType();
    }
    public static String getUserUAInfo(ServletRequest req, String loginName) {
        HttpServletRequest request = (HttpServletRequest)req;
        UserAgent userAgent = getUserAgent(request);
        String ip = HttpUtils.getRemoteAddr();
        int id = userAgent.getId();
        String name = userAgent.getBrowser().getName();
        Version v = userAgent.getBrowserVersion();
        String version = "Unknown";
        if (Objects.nonNull(v)) {
            version = userAgent.getBrowserVersion().getVersion();
        }

        String str = ip + ":" + id + ":" + name + ":" + version + ":" + loginName;
        String sessionInfo = DigestUtils.encodeBase64(str);

        try {
            return sessionInfo;
        } catch (Exception var12) {
            var12.printStackTrace();
            return null;
        }
    }

}
