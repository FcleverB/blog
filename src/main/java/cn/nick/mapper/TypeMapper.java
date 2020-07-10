package cn.nick.mapper;

import cn.nick.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TypeMapper {
    //新增分类
    int addType(Type type);

    //通过name查询分类
    Type queryTypeByName(String name);

    //通过id查询分类
    Type queryTypeById(Long id);

    //分页查询所有
    List<Type> listType();

    //修改分类
    int updType(Type type);

    //删除分类
    void delType(Long id);

    //查询所有分类以及其博客
    List<Type> queryTypeAndBlog();

    //查询出单个分类的所有博客
    List<Type> queryAllTypeBlog(Long id);
}
