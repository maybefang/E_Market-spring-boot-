package com.EMT.domain.Service;


import com.EMT.domain.Model.User;
import com.EMT.interf.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    //添加用户
    public Long addUser(User user){
        return userMapper.insertUser(user);
    }

    //查找用户（登录）
    public Integer logIn(String uname,String upwd) {
        return userMapper.logIn(uname,upwd);
    }

    //根据uid查找用户
    public User searchUserByUid(Integer uid) {
        return userMapper.searchUserByUid(uid);
    }
}
