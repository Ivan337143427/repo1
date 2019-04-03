package com.it.mapper;

import com.it.domain.Member;
import com.it.domain.Orders;
import com.it.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersMapper {
    @Select("select * from orders")
    List<Orders> findAll();
    @Delete("delete from orders where productId=#{pid}")
    void deleteByPid(String pid);

    @Delete("delete  from orders where id=#{id}")
    void deleteById(String id);
    @Select("select * from orders where id=#{id}")
    @Results({
            @Result(id=true, property = "id",column = "id"),
            @Result( property = "orderNum",column = "orderNum"),
            @Result( property = "orderTime",column = "orderTime"),
            @Result( property = "orderStatus",column = "orderStatus"),
            @Result( property = "peopleCount",column = "peopleCount"),
            @Result( property = "payType",column = "payType"),
            @Result( property = "orderDesc",column = "orderDesc"),
            @Result( property = "product",column = "productId",javaType =Product.class,one=@One(select = "com.it.mapper.ProductMapper.findByid")),
            @Result( property = "member",column = "memberId",javaType =Member.class,one=@One(select = "com.it.mapper.MemberMapper.findByid")),
            @Result( property = "travellers",column = "id",javaType =List.class,one=@One(select = "com.it.mapper.TravellerMapper.findByOid")),

    })
    Orders findById(String id);
}
