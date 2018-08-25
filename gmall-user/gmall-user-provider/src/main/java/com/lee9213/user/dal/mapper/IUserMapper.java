package com.lee9213.user.dal.mapper;

import com.lee9213.user.dal.entity.UserEntity;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-21 11:19
 */
public interface IUserMapper {

    UserEntity getUserByUserName(String userName);

    int insert(UserEntity user);

}
