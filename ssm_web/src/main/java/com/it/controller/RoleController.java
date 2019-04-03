package com.it.controller;

import com.it.domain.Role;
import com.it.service.PermissionService;
import com.it.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ADMIN")
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/findAll.do")
public ModelAndView findAll(){
        ModelAndView mv= new ModelAndView();
        mv.addObject("roleList",roleService.findAll());
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.addRole(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView addPermission(String id){
        ModelAndView mv= new ModelAndView();
        Role one=roleService.findOne(id);
        mv.addObject("role",one);
        mv.addObject("permissionList", permissionService.findExceptRid(id));
       mv.setViewName("role-permission-add");
       return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findOne(String id){
        ModelAndView mv= new ModelAndView();
        mv.addObject("role",roleService.findOne(id));
        mv.setViewName("role-show");
        return mv;
    }
    @RequestMapping("/addPermissionToRole.do")
    public String roleAddPermission(String roleId, String[] ids) {
        roleService.roleAddPermission(roleId,ids);
        return "redirect:/role/findAll.do";
    }
    @RequestMapping("/deleteRole.do")
    public String delete(String id){
        roleService.deleteById(id);
        return "redirect:/role/findAll.do";
    }
}
