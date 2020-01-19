package com.lihaiyang.learn.core.utils;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class HttpUtils {

    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception var1) {
            return null;
        }
    }

    public static String getUserUAInfo(String loginName) {
        return UserAgentUtils.getUserUAInfo(getRequest(), loginName);
    }


    public static String getRemoteAddr() {
        HttpServletRequest request = getRequest();
        if (Objects.isNull(request)) {
            return null;
        } else {
            String remoteAddr = request.getHeader("X-Real-IP");
            if (org.apache.commons.lang3.StringUtils.isBlank(remoteAddr)) {
                remoteAddr = request.getHeader("X-Forwarded-For");
            }

            if (org.apache.commons.lang3.StringUtils.isBlank(remoteAddr)) {
                remoteAddr = request.getHeader("Proxy-Client-IP");
            }

            if (StringUtils.isBlank(remoteAddr)) {
                remoteAddr = request.getHeader("WL-Proxy-Client-IP");
            }

            return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
        }
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();

        try {
            InputStream inputStream = request.getInputStream();
            Throwable var3 = null;

            try {
                List<String> list = IOUtils.readLines(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                list.forEach((str) -> {
                    sb.append(str);
                });
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (inputStream != null) {
                    if (var3 != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        inputStream.close();
                    }
                }

            }
        } catch (Exception var15) {
        }

        return sb.toString();
    }
}
