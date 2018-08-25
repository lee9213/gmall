package com.lee9213.user.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.lee9213.common.utils.JwtTokenUtils;
import com.lee9213.user.constant.UserStatusConstant;
import com.lee9213.user.dal.entity.UserEntity;
import com.lee9213.user.dal.mapper.IUserMapper;
import com.lee9213.user.dto.*;
import com.lee9213.user.enums.ResponseCodeEnum;
import com.lee9213.user.exception.ExceptionUtil;
import com.lee9213.user.exception.ServiceException;
import com.lee9213.user.exception.ValidateException;
import com.lee9213.user.service.IUserCoreService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        try {
            beforeValidate(request);
            UserEntity user = userMapper.getUserByUserName(request.getUserName());

            if (user == null || !user.getPassword().equals(request.getPassword())) {
                response.setCode(ResponseCodeEnum.USERORPASSWORD_ERRROR.getCode());
                response.setMsg(ResponseCodeEnum.USERORPASSWORD_ERRROR.getMsg());
                return response;
            }

            response.setUid(user.getId());
            response.setAvatar(user.getAvatar());

            Map<String, Object> payLoad = new HashMap<>(2);
            payLoad.put("uid", user.getId());
            payLoad.put("exp", DateTime.now().plusSeconds(40).toDate().getTime() / 1000);
            String token = JwtTokenUtils.generatorToken(payLoad);

            response.setToken(token);

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

    @Override
    public CheckAuthResponse validToken(CheckAuthRequest request) {
        CheckAuthResponse response = new CheckAuthResponse();
        try {
            beforeValidate(request);
            Claims claims = JwtTokenUtils.phaseToken(request.getToken());
            response.setUid(claims.get("uid").toString());
            response.setCode(ResponseCodeEnum.SUCCESS.getCode());
            response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expire :" + ex);
            response.setCode(ResponseCodeEnum.TOKEN_EXPIRE.getCode());
            response.setMsg(ResponseCodeEnum.TOKEN_EXPIRE.getMsg());
        } catch (SignatureException ex) {
            LOGGER.error("SignatureException :" + ex);
            response.setCode(ResponseCodeEnum.SIGNATURE_ERROR.getCode());
            response.setMsg(ResponseCodeEnum.SIGNATURE_ERROR.getMsg());
        } catch (Exception ex) {
            LOGGER.error("login occur exception :" + ex);
            ServiceException serviceException = (ServiceException) ExceptionUtil.handlerException4biz(ex);
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMessage());
        }
        return response;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse response = new UserRegisterResponse();

        try {
            beforeRegisterValidate(userRegisterRequest);

            UserEntity user = new UserEntity();
            user.setUsername(userRegisterRequest.getUsername());
            user.setPassword(userRegisterRequest.getPassword());
            user.setMobile(userRegisterRequest.getMobile());
            user.setSex(userRegisterRequest.getSex());
            user.setStatus(UserStatusConstant.NORMAL_USER_STATUS);
            user.setCreateTime(new Date());

            int effectRow = userMapper.insert(user);
            if (effectRow > 0) {
                response.setUid(user.getId());
                response.setCode(ResponseCodeEnum.SUCCESS.getCode());
                response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
                return response;
            }
            response.setCode(ResponseCodeEnum.SYSTEM_BUSY.getCode());
            response.setMsg(ResponseCodeEnum.SYSTEM_BUSY.getMsg());
            return response;
        } catch (Exception ex) {
            ServiceException serviceException = (ServiceException) ExceptionUtil.handlerException4biz(ex);
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMessage());
        }

        return response;
    }


    private void beforeValidate(UserLoginRequest request) {
        if (request == null) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getUserName())) {
            throw new ValidateException("用户名为空");
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            throw new ValidateException("密码为空");
        }
    }

    private void beforeValidate(CheckAuthRequest request) {
        if (request == null) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getToken())) {
            throw new ValidateException("token信息为空");
        }
    }

    private void beforeRegisterValidate(UserRegisterRequest request) {
        if (null == request) {
            throw new ValidateException("请求对象为空");
        }
        if (StringUtils.isEmpty(request.getUsername())) {
            throw new ValidateException("用户名为空");
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            throw new ValidateException("密码为空");
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            throw new ValidateException("密码为空");
        }
    }

}
