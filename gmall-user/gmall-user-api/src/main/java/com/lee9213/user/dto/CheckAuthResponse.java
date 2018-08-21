package com.lee9213.user.dto;

import com.lee9213.user.abs.AbstractResponse;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 17:24
 */
public class CheckAuthResponse extends AbstractResponse {

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
