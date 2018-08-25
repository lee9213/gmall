package com.lee9213.user;

import com.lee9213.user.constant.UserStatusConstant;
import com.lee9213.user.dal.entity.UserEntity;
import com.lee9213.user.dal.mapper.IUserMapper;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-25 10:37
 */
public class UserTest {

    @Test
    public void insert(){

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/*.xml".split("[,\\s]+"));
        applicationContext.start();

        UserEntity user = new UserEntity();
        user.setUsername("l111");
        user.setPassword("96e79218965eb72c92a549dd5a330112");
        user.setMobile("18080800926");
        user.setSex("0");
        user.setStatus(UserStatusConstant.NORMAL_USER_STATUS);
        user.setCreateTime(new Date());

        IUserMapper userMapper =  applicationContext.getBean(IUserMapper.class);

        int effectRow = userMapper.insert(user);

        System.out.println(effectRow);
        System.out.println(user);
        applicationContext.stop();
    }
}
