package com.it.mapper;

import com.it.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {
    @Select("select * from member where id=#{id}")
    Member findByid(String id);
}
