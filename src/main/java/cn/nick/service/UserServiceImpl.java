package cn.nick.service;

import cn.nick.mapper.UserMapper;
import cn.nick.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    //注册用户
    @Override
    public boolean registerUser(User user) {
        return userMapper.register(user);
    }
    //注册时查重用户名
    @Override
    public String findUserByName(String regname) {
        return userMapper.findUserByName(regname);
    }


    @Override
    public User checkUser(String username) {
        return userMapper.queryUserByName(username);
    }

    @Override
    public int updUser(User user) {
        return userMapper.updUser(user);
    }



}
