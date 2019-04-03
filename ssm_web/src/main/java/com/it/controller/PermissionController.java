package com.it.controller;

import com.it.domain.Permission;
import com.it.domain.UserInfo;
import com.it.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/permission")
public class PermissionController {
@Autowired
    private PermissionService permissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv= new ModelAndView();
        mv.addObject("permissionList",permissionService.findAll());
        mv.setViewName("permission-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Permission permission){
        permissionService.addPermission(permission);
        return "redirect:/permission/findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findOne(String id){
        ModelAndView mv= new ModelAndView();
        mv.addObject("permission",permissionService.findOne(id));
        mv.setViewName("permission-show");
        return mv;
    }
    @RequestMapping("/deletePermission.do")
    public String delete(String id){
        permissionService.deleteById(id);
        return "redirect:/permission/findAll.do";
    }
}
