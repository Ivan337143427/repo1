package com.it.controller;

import com.github.pagehelper.PageInfo;
import com.it.domain.Product;
import com.it.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1",name = "page") Integer page, @RequestParam(defaultValue = "4",name = "size")Integer size){
        ModelAndView mv=new ModelAndView();
        PageInfo p = productService.findAll(page, size);
        mv.addObject("productList",p);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(Product product){
       productService.save(product);
        return "redirect:/product/findAll.do";
    }
    @RequestMapping("/delete.do")
    public String delete(String[] ids){
        System.out.println(ids.toString());
        productService.delete(ids);
        return "redirect:/product/findAll.do";
    }
}
