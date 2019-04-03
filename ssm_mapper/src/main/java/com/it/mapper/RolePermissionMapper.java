package com.it.mapper;

import org.apache.ibatis.annotations.Delete;

public interface RolePermissionMapper {

    @Delete("delete from role_permission where roleId=#{rid}")
    void deleteByRid(String rid);
    @Delete("delete from role_permission where permissionId=#{pid}")
    void deleteByPid(String pid);
}
