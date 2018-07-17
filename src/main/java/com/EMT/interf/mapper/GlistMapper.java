package com.EMT.interf.mapper;

import com.EMT.api.request.GlistRequest;
import com.EMT.domain.Model.Glist;
import com.EMT.domain.Model.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GlistMapper {

    //添加一条购物车信息
    @Insert("insert into glist (gid,uid,flag) values (#{gid},#{uid},0)")
    Long insertGlist(Glist glist);

    //删除一条购物车信息
    @Delete("delete from glist where glid = #{glid}")
    Long deletGlist(@Param("glid") Integer glid);

    //购买购物车中物品，更新flag
    @Update("update glid set flag = 1 where glid = #{glid}")
    Long buyInGlist(@Param("glid") Integer glid);

    //查找购物车中信息对应用户id
    @Select("select * from goods where gid in (select gid from glist where uid = #{uid} and flag = 0)")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "price", column = "price"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "num",column = "num"),
            @Result(property = "sid", column = "sid"),
            @Result(property = "msg", column = "msg")
    })
    List<Goods> goodsInGlist(@Param("uid") Integer uid);

    //直接购买物品
    @Insert("insert into glist (gid,uid,flag) values (#{gid},#{uid},1)")
    Long buyDirect(GlistRequest glistRequest);
}
