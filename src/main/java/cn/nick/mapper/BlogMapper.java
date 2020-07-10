package cn.nick.mapper;

import cn.nick.dto.*;
import cn.nick.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {

    //通过id查找blog
    Blog queryBlogById(Long id);

    //分页查询所有
    List<Blog> listblog();

    //通过组合条件查询blog
    List<BlogQuery> queryBlogByTypeOrTagOrRecommend(SearchBlog searchBlog);

    //新增blog
    int addBlog(Blog blog);
    int addBlogAndTags(BlogAndTag blogAndTag);

    //修改blog
    int updBlog(Blog blog);

    //删除blog
    void delBlog(Long id);
    void delBlogAndTag(Long id);

    //查询index页面显示的博客列表
    List<IndexBlog> queryIndexBlog();

    //获得推荐的博客
    List<RecommendBlog> queryRecommendBlog();

    //全局搜索
    List<IndexBlog> queryAllBlog(String query);

    //通过id查询博客详情
    Blog queryDetailBlogById(Long id);

    //通过typeId或tagId找到博客
    List<IndexBlog> queryIndexBlogByTypeOrTag(SearchBlog searchBlog);

    //查询归档的博客所有时间
    List<DateTime> queryAllDate();

    //查询归档中年份所属的所有博客
    List<Blog> queryAllBlogByYear(String year);

    //浏览量更改
    void addBlogViews(Long id);
}
