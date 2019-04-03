package com.it.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.domain.Product;
import com.it.mapper.OrdersMapper;
import com.it.mapper.OrdersTravellerMapper;
import com.it.mapper.ProductMapper;
import com.it.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrdersTravellerMapper ordersTravellerMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        PageInfo pageInfo=new PageInfo(productMapper.findAll());
        return pageInfo;
    }

    @Override
    public void save(Product product) {
        productMapper.save(product);
    }

    @Override
    public void delete(String[] ids) {
        for(String id:ids){
            ordersTravellerMapper.deleteByPid(id);
            ordersMapper.deleteByPid(id);
            productMapper.delete(id);
        }
    }
}
