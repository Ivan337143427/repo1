package com.it.mapper;

import org.apache.ibatis.annotations.Delete;

public interface UsersRoleMappler {
    @Delete("delete from users_role where userid=#{uid}")
    void deleteByUid(String uid);
    @Delete("delete from users_role where roleid=#{rid}")
    void deleteByRid(String rid);
}
