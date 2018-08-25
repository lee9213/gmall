package com.lee9213.user.dto;

import com.lee9213.user.abs.AbstractRequest;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-25 1:31
 */
public class UserRegisterRequest extends AbstractRequest {

    private String username;

    private String password;

    private String mobile;

    private String sex;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
