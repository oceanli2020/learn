package com.lihaiyang.learn.core.shrio;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lihaiyang.learn.core.result.Result;
import com.lihaiyang.learn.core.result.ResultStatus;
import com.lihaiyang.learn.core.utils.CookieUtils;
import com.lihaiyang.learn.core.utils.HttpUtils;
import com.lihaiyang.learn.core.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 过滤器
 * */
public class JwtAuthFilter extends BasicHttpAuthenticationFilter {


    public JwtAuthFilter() {
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        this.fillCorsHeader(httpServletRequest, httpServletResponse);
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(200);
            return false;
        } else {
            return super.preHandle(request, response);
        }
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (this.isLoginRequest(request, response)) {
            return true;
        } else {
            boolean allowed = false;
            AuthenticationToken token = this.createToken(request, response);
            if (token == null) {
                String msg = "token为空";
                throw new IllegalStateException(msg);
            } else {
                try {
                    Subject subject = this.getSubject(request, response);
                    subject.login(token);
                    allowed = true;
                } catch (IllegalStateException var7) {

                } catch (Exception var8) {

                    respondJson(WebUtils.toHttp(response), 401, new Result(ResultStatus.AUTH_UNAUTHORIZED, var8.getMessage()));
                }

                return allowed;
            }
        }
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String jwtToken = this.getToken(servletRequest);
        return StringUtils.isNotBlank(jwtToken) ? new JwtToken(jwtToken) : null;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse response = WebUtils.toHttp(servletResponse);
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        this.fillCorsHeader(request, response);
        return false;
    }

    private void fillCorsHeader(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE,PATCH");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
    }

    private static void respondJson(HttpServletResponse response, int respondStatus, Result resultModel) {
        response.setStatus(respondStatus);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        try {
            PrintWriter out = response.getWriter();
            Throwable var4 = null;

            try {
                String json = (new ObjectMapper()).writeValueAsString(resultModel);
                out.write(json);
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (out != null) {
                    if (var4 != null) {
                        try {
                            out.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        out.close();
                    }
                }

            }
        } catch (IOException var16) {

        }

    }

    private String getToken(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest)req;
        if (null == request) {
            return null;
        } else {
            String token = request.getHeader("token");
            if (StringUtils.isBlank(token)) {
                token = request.getHeader("Authorization");
            }
            if (StringUtils.isBlank(token)) {
                token = request.getParameter("token");
            }
            if (StringUtils.isBlank(token)) {
                token = CookieUtils.getCookie(request, "Admin-Token");
            }
            if (StringUtils.isBlank(token)) {
                token = HttpUtils.getBodyString(request);
            }
            return StringUtils.isNotBlank(token) ? token.replaceAll("Bearer ", "") : null;
        }
    }
}
