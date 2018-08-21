package com.lee9213.user.dto;

import com.lee9213.user.abs.AbstractRequest;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 8:40
 */
public class UserLoginRequest extends AbstractRequest {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
