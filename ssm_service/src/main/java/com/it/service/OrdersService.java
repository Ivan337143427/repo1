package com.it.service;

import com.github.pagehelper.PageInfo;
import com.it.domain.Orders;

public interface OrdersService {
    PageInfo findAll(int page, int size);
    void deleteByIds(String[] ids);
    Orders findOne(String oid);
}
