package com.it.mapper;

import org.apache.ibatis.annotations.Delete;

public interface OrdersTravellerMapper {
    @Delete("delete from order_traveller where orderId=#{oid}")
    void deletebyOid(String oid);
    @Delete("delete from order_traveller where orderId in(select id from orders where productId=#{pid})")
    void deleteByPid(String pid);
}
