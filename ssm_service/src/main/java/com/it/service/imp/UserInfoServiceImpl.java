package com.it.service.imp;

import com.it.domain.UserInfo;
import com.it.mapper.UserInfoMapper;
import com.it.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
@Autowired
private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.findAll();
    }

    @Override
    public UserInfo findOne(String id) {
        return userInfoMapper.findOne(id);
    }

    @Override
    public void userAddRole(String uid, String[] roleId) {
        for (String rid : roleId) {
            userInfoMapper.userAddRole(uid,rid);
        }
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoMapper.addUser(userInfo);
    }
}
