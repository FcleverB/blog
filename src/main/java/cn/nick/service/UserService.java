package cn.nick.service;

import cn.nick.pojo.User;

public interface UserService {
     //注册用户
     boolean registerUser(User user);

     //查重用户名
     String findUserByName(String regname);

     /**
      * 登录逻辑
      * @param username
      * @return
      */
     User checkUser(String username);
     //修改头像以及昵称
     int updUser(User user);

     //查重用户名(待加)


}
