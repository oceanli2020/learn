package com.lihaiyang.learn.service;

import javax.servlet.http.HttpServletRequest;

public interface ILoginService {

    String getToken(String refreshToken, HttpServletRequest request);
}
