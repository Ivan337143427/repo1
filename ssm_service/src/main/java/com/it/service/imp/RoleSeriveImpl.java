package com.it.service.imp;

import com.it.domain.Role;
import com.it.mapper.RoleMapper;
import com.it.mapper.RolePermissionMapper;
import com.it.mapper.UsersRoleMappler;
import com.it.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleSeriveImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private UsersRoleMappler usersRoleMappler;
    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public List<Role> findExceptUid(String uid) {
        return roleMapper.findExceptUid(uid);
    }

    @Override
    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    @Override
    public void roleAddPermission(String roleId, String[] permissionId) {
        for (String pid : permissionId) {
            roleMapper.roleAddPermission(roleId,pid);
        }
    }

    @Override
    public void deleteById(String id) {
        rolePermissionMapper.deleteByRid(id);
        usersRoleMappler.deleteByRid(id);
        roleMapper.deleteById(id);
    }

    @Override
    public Role findOne(String id) {
        return roleMapper.findOne(id);
    }
}
