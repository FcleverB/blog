package cn.nick.service;

import cn.nick.dto.SearchBlog;
import cn.nick.pojo.Tag;
import java.util.List;


public interface TagService {
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

    //获得tagIds后将其转换成tag的List
    List<Tag> changeTagsToList(String tagIds);

    //查询所有标签以及其博客
    List<Tag> queryTagAndBlog();

    //获取前5条
    List<Tag> selectFive(List<Tag> tags);

    //根据博客内容排序
    List<Tag> sortTag(List<Tag> tags);

    //通过tagId查询博客做成首页
    List<Tag> queryTagAndBlogById(SearchBlog searchBlog);
}
