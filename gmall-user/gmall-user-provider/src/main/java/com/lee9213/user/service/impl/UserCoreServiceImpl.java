package com.lee9213.user.service.impl;

import com.lee9213.user.dto.UserLoginRequest;
import com.lee9213.user.dto.UserLoginResponse;
import com.lee9213.user.service.IUserCoreService;
import org.springframework.stereotype.Service;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 10:54
 */
@Service("userCoreService")
public class UserCoreServiceImpl implements IUserCoreService {


    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        return null;
    }
}
