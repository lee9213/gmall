package com.lee9213.sso.support;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 19:52
 * 需要配置{@link org.springframework.web.context.request.RequestContextListener}
 */
public final class ServletHolder {

    private ServletHolder() {
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return req;
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return resp;
    }
}
