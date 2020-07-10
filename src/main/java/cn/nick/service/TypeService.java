package cn.nick.service;

import cn.nick.pojo.Type;

import java.util.List;

public interface TypeService {
    //新增分类
    int addType(Type type);

    //查询分类
    Type queryTypeById(Long id);

    //通过name查询分类
    Type queryTypeByName(String name);

    //分页查询所有
    List<Type> listType();

    //修改分类
    int updType(Type type);

    //删除分类
    void delType(Long id);

    //查询所有分类以及其博客
    List<Type> queryTypeAndBlog();

    //根据博客内容排序
    List<Type> sortType(List<Type> tags);

    //获取前5条
    List<Type> selectFive(List<Type> types);

    //查询出单个分类的所有博客
    List<Type> queryAllTypeBlog(Long id);
}
