package com.EMT.interf.mapper;
import com.EMT.api.request.IdRequest;
import com.EMT.domain.Model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //添加用户
    @Insert("insert into  user( " +
            "uname," +
            "upwd" +
            ") values(" +
            "#{uname}," +
            "#{upwd}" +
            ")")
    Long insertUser(User user);

    //查找用户
    @Select("select uid from user where uname = #{uname} and upwd = #{upwd}")
    @Results({
            @Result( property = "uid",column = "uid")})
    Integer logIn(@Param("uname") String uname,@Param("upwd") String upwd);

    //根据uid查用户
    @Select("select * from user where uid = #{uid}")
    @Results({
            @Result(property = "uid", column = "uid"),
            @Result(property = "uname", column = "uname")
    })
    User searchUserByUid(@Param("uid")Integer uid);

}
