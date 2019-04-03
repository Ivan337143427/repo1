package com.it.mapper;

import com.it.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogMapper {
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method}) ")
    void save(SysLog sysLog);
    @Select("select * from sysLog ")
    List<SysLog> findAll();
}
