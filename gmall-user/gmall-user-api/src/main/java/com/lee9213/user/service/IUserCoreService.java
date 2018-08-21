package com.lee9213.user.service;

import com.lee9213.user.dto.CheckAuthRequest;
import com.lee9213.user.dto.CheckAuthResponse;
import com.lee9213.user.dto.UserLoginRequest;
import com.lee9213.user.dto.UserLoginResponse;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 8:41
 */
public interface IUserCoreService {

    /**
     * 用户登陆
     *
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);


    /**
     * 校验token
     *
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);
}
