package com.it.service;

import com.it.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoService {

    List<UserInfo> findAll();
    UserInfo findOne(String id);
    void userAddRole(String uid, String[] roleId);
    void addUser(UserInfo userInfo);
}
