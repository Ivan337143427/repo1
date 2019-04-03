package com.it.mapper;

import com.it.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerMapper {
    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId=#{oid})")
    List<Traveller> findByOid(String oid);
}
