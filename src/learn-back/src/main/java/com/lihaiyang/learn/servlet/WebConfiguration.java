package com.lihaiyang.learn.servlet;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 解决跨域问题
 * */
@Configuration
public class WebConfiguration {

    @Value("${adminPath}")
    private String adminPath;
    @Value("${allowed.origins}")
    private String allowedOrigins;

    public WebConfiguration() {
    }

    @Bean
    public FilterRegistrationBean requestBodyRegistrationBean() {
        StringBuffer excludedUriStr = new StringBuffer();
        excludedUriStr.append("/+" + this.adminPath + "/login");
        excludedUriStr.append(",");
        excludedUriStr.append("/" + this.adminPath + "/login/*");
        excludedUriStr.append(",");
        excludedUriStr.append("/" + this.adminPath + "/js/*");
        excludedUriStr.append(",");
        excludedUriStr.append("/logout/*");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestReplacedFilter());
        registration.addInitParameter("excludedUri", excludedUriStr.toString());
        registration.addUrlPatterns(new String[]{"/*"});
        registration.setName("requestFilter");
        registration.setOrder(5);
        return registration;
    }

    @Bean
    public FilterRegistrationBean xssRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(new String[]{"/*"});
        registration.setName("requestFilter");
        registration.setOrder(6);
        return registration;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        final String heads = "Content-Type,X-XSRF-Token,Authorization,token,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Content-Disposition";
        final String exposeHader = "Content-Disposition";
        final String[] allowOrgins = this.allowedOrigins.split(",");
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(allowOrgins).allowedMethods(new String[]{"POST", "GET", "PUT", "OPTIONS", "DELETE", "PATCH"}).allowedHeaders(StringUtils.split(heads, ",")).exposedHeaders(StringUtils.split(exposeHader, ",")).maxAge(3600L).allowCredentials(true);
            }
        };
    }
}
