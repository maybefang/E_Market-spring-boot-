package com.EMT.interf.mapper;

import com.EMT.domain.Model.Goods;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.List;

@Mapper
public interface GoodsMapper {
    //添加物品
    @Insert("insert into goods (gname,price,pic,num,sid,msg,sname) values (" +
            "#{gname},#{price},#{pic},#{num},#{sid},#{msg},#{sname})")
    Long insertGoods(Goods goods);

    //删除物品
    @Delete("delete from goods where gid = #{gid}")
    Long deleteGoods(@Param("gid") Integer gid);

    //由于用户买入更改商品库存数量
    @Update("update goods set num = num-1 where gid = #{gid}")
    Long updateGoodsNum(@Param("gid") Integer gid);

    //主页查找全部库存不为零商品
    @Select("select * from goods where num <> 0")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "price", column = "price"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "num", column = "num"),
            @Result(property = "sid", column = "sid"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "sname",column = "sname")
    })
    List<Goods> getAllGoods();

    //根据名字查物品
    @Select("select * from goods where num <> 0 and gname like CONCAT('%', #{lgname,jdbcType=VARCHAR}, '%') ")
//    @Select("select * from goods where num <> 0 and gname = #{lgname}")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "price", column = "price"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "num", column = "num"),
            @Result(property = "sid",column = "sid"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "sname", column = "sname")
    })
    List<Goods> searchByGname(@Param("lgname") String lgname);

    //根据店铺sid查找物品
    @Select("select * from goods where sid = #{sid}")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "price", column = "price"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "num", column = "num"),
            @Result(property = "sid",column = "sid"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "sname", column = "sname")
    })
    List<Goods> searchBySid(@Param("sid")Integer sid);

    //查找已购买物品
    @Select("select * from goods where gid in (select gid from glist where uid = #{uid} and flag = 1)")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "price", column = "price"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "num", column = "num"),
            @Result(property = "sid",column = "sid"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "sname", column = "sname")
    })
    List<Goods> hasBuy(@Param("uid") Integer uid);

    //根据gid查找物品
    @Select("select * from goods where gid = #{gid}")
    @Results({
            @Result(property = "gid", column = "gid"),
            @Result(property = "gname", column = "gname"),
            @Result(property = "price", column = "price"),
            @Result(property = "pic", column = "pic"),
            @Result(property = "num", column = "num"),
            @Result(property = "sid",column = "sid"),
            @Result(property = "msg", column = "msg"),
            @Result(property = "sname", column = "sname")
    })
    Goods oneGoods(@Param("gid") Integer gid);



}
