package com.it.controller;

import com.github.pagehelper.PageInfo;
import com.it.domain.Role;
import com.it.domain.UserInfo;
import com.it.service.RoleService;
import com.it.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<UserInfo> list= userInfoService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findOne(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo one = userInfoService.findOne(id);
        mv.addObject("user",one);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo one = userInfoService.findOne(id);
        mv.addObject("user",one);
        List<Role> roleList = roleService.findExceptUid(id);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/addRoleToUser.do")
    public String addRole(String userId,String[] ids){
        userInfoService.userAddRole(userId,ids);
        return "redirect:/user/findAll.do";
    }
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo){
        userInfoService.addUser(userInfo);
        return "redirect:/user/findAll.do";
    }
}
