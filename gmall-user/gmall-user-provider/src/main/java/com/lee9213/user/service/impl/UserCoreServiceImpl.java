package com.lee9213.user.service.impl;

import com.lee9213.user.dal.entity.UserEntity;
import com.lee9213.user.dal.mapper.IUserMapper;
import com.lee9213.user.dto.UserLoginRequest;
import com.lee9213.user.dto.UserLoginResponse;
import com.lee9213.user.enums.ResponseCodeEnum;
import com.lee9213.user.exception.ExceptionUtil;
import com.lee9213.user.exception.ServiceException;
import com.lee9213.user.service.IUserCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 10:54
 */
@Service("userCoreService")
public class UserCoreServiceImpl implements IUserCoreService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserMapper userMapper;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse response = new UserLoginResponse();
        UserEntity user = userMapper.getUserByUserName(request.getUserName());

        try {
            if (user == null || !user.getPassword().equals(request.getPassword())) {
                response.setCode(ResponseCodeEnum.USERORPASSWORD_ERRROR.getCode());
                response.setMsg(ResponseCodeEnum.USERORPASSWORD_ERRROR.getMsg());
                return response;
            }

            response.setUid(user.getId());
            response.setAvatar(user.getAvatar());
            response.setCode(ResponseCodeEnum.SUCCESS.getCode());
            response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());

        } catch (Exception ex) {
            LOGGER.error("login occur exception :" + ex);
            ServiceException serviceException = (ServiceException) ExceptionUtil.handlerException4biz(ex);
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMessage());
        }
        return response;
    }
}
