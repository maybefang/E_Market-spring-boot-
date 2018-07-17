package com.EMT.interf.mapper;

import com.EMT.domain.Model.Shop;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface ShopMapper {
    @Insert("insert into shop (sname,spwd) values (#{sname},#{spwd})")
    Long insertShop(Shop shop);

    //根据sid查店铺
    @Select("select sname from shop where sid = #{sid}")
    String searchBySid(@Param("sid") Integer sid);

    //店铺更改物品数量
    @Update("update goods set num = #{num} where gid = #{gid}")
    Long updatenum(@Param("num")Integer num,@Param("gid")Integer gid);

    //查找店铺（登录）
    @Select("select sid from shop where sname = #{sname} and spwd = #{spwd}")
    Integer shopLogIn(@Param("sname") String sname,@Param("spwd") String spwd);
}
