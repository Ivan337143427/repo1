package com.it.service.imp;

import com.it.domain.Permission;
import com.it.mapper.PermissionMapper;
import com.it.mapper.RolePermissionMapper;
import com.it.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public List<Permission> findExceptRid(String rid) {
        return permissionMapper.findExceptRid(rid);
    }

    @Override
    public void addPermission(Permission permission) {
        permissionMapper.addPermission(permission);
    }

    @Override
    public Permission findOne(String id) {
        return permissionMapper.findOne(id);
    }

    @Override
    public void deleteById(String id) {
        rolePermissionMapper.deleteByPid(id);
        permissionMapper.deleteById(id);
    }
}
