package com.lihaiyang.learn.servlet;


import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;


public class XssFilter implements Filter {

    public XssFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String requestUrl = req.getRequestURL().toString().toLowerCase().replaceAll("\\r|\\t|\\s", "");
        if (StringUtils.isNotBlank(requestUrl) && this.CRLF(requestUrl, requestUrl, response)) {
            throw new ServletException("CRLF输入.......");
        } else {
            Enumeration headerNames = req.getHeaderNames();

            String param;
            while(headerNames.hasMoreElements()) {
                param = req.getHeader((String)headerNames.nextElement()).toLowerCase();
                if (StringUtils.isNotBlank(param) && this.CRLF(param, requestUrl, response)) {
                    throw new ServletException("CRLF输入.......");
                }
            }

            param = req.getQueryString();
            if (StringUtils.isNotBlank(param) && this.CRLF(param, requestUrl, response)) {
                throw new ServletException("CRLF输入.......");
            } else {
                if (requestUrl.indexOf("<script>") != -1 || requestUrl.indexOf("<style>") != -1) {
                    response.reset();
                    request.getRequestDispatcher("/").forward(request, response);
                }

                HttpServletResponse resp = (HttpServletResponse)response;
                resp.setHeader("SET-COOKIE", "JSESSIONID=" + req.getSession().getId() + "; HttpOnly");
                resp.setHeader("x-frame-options", "SAMEORIGIN");
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    private boolean CRLF(String str, String requestUrl, ServletResponse response) throws IOException, ServletException {
        Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)|(%0d|%0a)");
        Matcher m = CRLF.matcher(str.toLowerCase());
        HttpServletResponse res = (HttpServletResponse)response;
        if (m.find()) {
            res.reset();
            res.setStatus(403);
            return true;
        } else {
            return false;
        }
    }
}
