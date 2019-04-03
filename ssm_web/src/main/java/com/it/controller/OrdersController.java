package com.it.controller;

import com.github.pagehelper.PageInfo;
import com.it.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1",name = "page") Integer page, @RequestParam(defaultValue = "4",name = "size")Integer size){
        ModelAndView mv=new ModelAndView();
        PageInfo p = ordersService.findAll(page, size);
        mv.addObject("pageInfo",p);
        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/delete.do")
    public String delete(String[] ids){
        ordersService.deleteByIds(ids);
        return "redirect:/orders/findAll.do";
    }
    @RequestMapping("/findOne.do")
    public ModelAndView findOne(String id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("orders",ordersService.findOne(id));
        mv.setViewName("orders-show");
        return mv;
    }
}
