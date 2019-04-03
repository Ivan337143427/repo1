package com.it.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.domain.Orders;
import com.it.mapper.OrdersMapper;
import com.it.mapper.OrdersTravellerMapper;
import com.it.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersTravellerMapper ordersTravellerMapper;
    @Override
    public PageInfo findAll(int page, int size) {
        PageHelper.startPage(page,size);
        PageInfo pageInfo=new PageInfo(ordersMapper.findAll());
        return pageInfo;
    }

    @Override
    public void deleteByIds(String[] ids) {
        for(String id:ids){
            ordersTravellerMapper.deletebyOid(id);
            ordersMapper.deleteById(id);
        }
    }

    @Override
    public Orders findOne(String oid) {
        return ordersMapper.findById(oid);
    }
}
