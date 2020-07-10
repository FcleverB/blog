package cn.nick.mapper;

import cn.nick.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //注册
    boolean register(User user);

    //查重用户名
    String findUserByName(String regname);

    //登录
     User queryUserByName(@Param("username") String username);
    //修改头像以及昵称
    int updUser(User user);


}
