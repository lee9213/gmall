package com.lee9213.user.dto;

import com.lee9213.user.abs.AbstractRequest;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 17:23
 */
public class CheckAuthRequest extends AbstractRequest {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
