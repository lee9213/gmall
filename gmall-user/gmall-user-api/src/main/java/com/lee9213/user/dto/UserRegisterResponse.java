package com.lee9213.user.dto;

import com.lee9213.user.abs.AbstractResponse;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-25 1:31
 */
public class UserRegisterResponse extends AbstractResponse {

    private Integer uid;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
