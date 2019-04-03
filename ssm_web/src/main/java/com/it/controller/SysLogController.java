package com.it.controller;

import com.it.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.DenyAll;


@Controller
@RequestMapping("/sysLog")
public class SysLogController {
@Autowired
private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("sysLogs",sysLogService.findAll());
        mv.setViewName("syslog-list");
        return mv;
    }
}
