package com.it.mapper;

import com.it.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {
    @Select("select * from permission")
    List<Permission> findAll();
    @Select("select *from permission where id not in(select permissionId from role_permission where roleId=#{rid})")
    List<Permission> findExceptRid(String rid);
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void addPermission(Permission permission);
@Select("select * from permission where id in(select permissionId from role_permission where roleId=#{rid})")
    List<Permission> findByRid(String rid);
@Select("select * from permission where id=#{id}")
    Permission findOne(String id);
@Delete("delete from permission where id=#{id}")
    void deleteById(String id);

}
