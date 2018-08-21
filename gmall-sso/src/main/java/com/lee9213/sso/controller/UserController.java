package com.lee9213.sso.controller;

import com.lee9213.sso.support.ResponseData;
import com.lee9213.user.dto.UserLoginRequest;
import com.lee9213.user.dto.UserLoginResponse;
import com.lee9213.user.service.IUserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public ResponseData doLogin(String userName, String password) {
        ResponseData data = new ResponseData();

        UserLoginRequest request = new UserLoginRequest();
        request.setUserName(userName);
        request.setPassword(password);

        UserLoginResponse login = userCoreService.login(request);
        data.setCode(login.getCode());
        data.setMessage(login.getMsg());
        data.setData("http://localhost:8080/pages/register.html");

        return data;
    }
}
