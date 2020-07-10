package cn.nick.mapper;

import cn.nick.dto.SearchBlog;
import cn.nick.pojo.Tag;
import cn.nick.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagMapper {
    //新增标签
    int addTag(Tag tag);

    //通过name查询标签
    Tag queryTagByName(String name);

    //通过id查询标签
    Tag queryTagById(Long id);

    //分页查询所有
    List<Tag> listTag();

    //修改标签
    int updTag(Tag tag);

    //删除标签
    void delTag(Long id);

    //查询所有标签以及其博客
    List<Tag> queryTagAndBlog();

    //通过tagId查询博客做成首页
 //   List<Tag> queryTagAndBlogById(SearchBlog searchBlog);
}
