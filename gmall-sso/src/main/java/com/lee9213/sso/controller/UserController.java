package com.lee9213.sso.controller;

import com.lee9213.common.annotation.Anoymous;
import com.lee9213.sso.enums.ResponseEnum;
import com.lee9213.sso.support.ResponseData;
import com.lee9213.sso.support.ServletHolder;
import com.lee9213.user.constant.KafkaTopicConstant;
import com.lee9213.user.dto.UserLoginRequest;
import com.lee9213.user.dto.UserLoginResponse;
import com.lee9213.user.dto.UserRegisterRequest;
import com.lee9213.user.dto.UserRegisterResponse;
import com.lee9213.user.service.IUserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 13:59
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private IUserCoreService userCoreService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Anoymous
    @PostMapping("/login")
    public ResponseData doLogin(String userName, String password) {
        ResponseData data = new ResponseData();

        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(userName);
        request.setPassword(password);

        UserLoginResponse login = userCoreService.login(request);
        data.setCode(login.getCode());
        data.setMsg(login.getMsg());
        data.setData("http://localhost:8080/pages/register.html");

        ServletHolder.getResponse().addHeader("Set-Cookie",
                "access_token=" + login.getToken() + ";Path=/;HttpOnly");
        return data;
    }

    @Anoymous
    @PostMapping("/register")
    public ResponseData register(String username, String password, String mobile) {
        ResponseData data = new ResponseData();

        UserRegisterRequest request = new UserRegisterRequest();
        request.setMobile(mobile);
        request.setUsername(username);
        request.setPassword(password);
        try {
            UserRegisterResponse response = userCoreService.register(request);
            //异步化解耦
            kafkaTemplate.send(KafkaTopicConstant.LUCKY_DRAW_CHANCE_TOPIC, response.getUid().toString());

            data.setMsg(response.getMsg());
            data.setCode(response.getCode());
        } catch (Exception e) {
            data.setMsg(ResponseEnum.FAILED.getMsg());
            data.setCode(ResponseEnum.FAILED.getCode());
        }
        return data;
    }
}
