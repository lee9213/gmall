package com.lee9213.sso.controller;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 14:00
 */
public abstract class BaseController {


    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public void setUid(String uid) {
        threadLocal.set(uid);
    }

    public String getUid() {
        return threadLocal.get();
    }
}
