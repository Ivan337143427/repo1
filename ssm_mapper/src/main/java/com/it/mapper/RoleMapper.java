package com.it.mapper;

import com.it.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {
    @Select("select * from role where id in(select roleId from users_role where userId=#{uid})")
    @ResultMap("roleMap")
    List<Role> findByUid(String uid);

    @Select("select * from role")
    @ResultMap("roleMap")
    List<Role> findAll();

    @Select("select * from role where id not in(select roleId from users_role where userId=#{uid})")
    List<Role> findExceptUid(String uid);

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void addRole(Role role);

    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void roleAddPermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    @Select("select * from role where id=#{id}")
    @Results(id = "roleMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = List.class, many = @Many(select = "com.it.mapper.PermissionMapper.findByRid"))
    })
    Role findOne(String id);
    @Delete("delete from role where id=#{id}")
    void deleteById(String id);
}
