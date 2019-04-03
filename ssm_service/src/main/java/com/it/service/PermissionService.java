package com.it.service;

import com.it.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();
    List<Permission> findExceptRid(String rid);
    void addPermission(Permission permission);
    Permission findOne(String id);
    void deleteById(String id);
}
