package com.it.mapper;

import com.it.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserInfoMapper {
    @Select("select * from users")
    @Results(id="userInfoMap",value = {
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType =List.class ,many = @Many(select = "com.it.mapper.RoleMapper.findByUid"))
    })
    public List<UserInfo> findAll();
    @Select("select * from users where id=#{id}")
    @ResultMap("userInfoMap")
    UserInfo findOne(String id);
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void userAddRole(@Param("userId") String uid,@Param("roleId")String roleId);
    @Insert("insert into users(email,username,PASSWORD,phoneNum,STATUS) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void addUser(UserInfo userInfo);
    @Select("select * from users where username=#{username}")
    @ResultMap("userInfoMap")
    UserInfo findByUsername(String username);
}
