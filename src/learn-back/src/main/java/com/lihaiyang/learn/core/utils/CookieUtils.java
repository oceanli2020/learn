package com.lihaiyang.learn.core.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CookieUtils {

    public static String getCookie(HttpServletRequest request, String name) {
        return getCookie(request, (HttpServletResponse)null, name, false);
    }

    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove) {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie[] var6 = cookies;
            int var7 = cookies.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                Cookie cookie = var6[var8];
                if (cookie.getName().equals(name)) {
                    try {
                        value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException var11) {
                        var11.printStackTrace();
                    }

                    if (isRemove) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }

        return value;
    }
}
