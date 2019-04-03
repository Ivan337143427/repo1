package com.it.service;

import com.it.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {

    List<Role> findAll();
    List<Role> findExceptUid(String uid);
    void addRole(Role role);
    void roleAddPermission(String roleId,String[] permissionId);
    void deleteById(String id);
    Role findOne(String id);
}
