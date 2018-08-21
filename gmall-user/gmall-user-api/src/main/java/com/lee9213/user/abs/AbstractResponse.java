package com.lee9213.user.abs;

import java.io.Serializable;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 13:32
 */
public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = -4355995461088968120L;

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
