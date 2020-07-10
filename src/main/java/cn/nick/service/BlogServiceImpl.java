package cn.nick.service;

import cn.nick.dto.*;
import cn.nick.mapper.BlogMapper;
import cn.nick.pojo.Blog;
import cn.nick.pojo.Tag;
import cn.nick.util.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog queryBlogById(Long id) {
        return blogMapper.queryBlogById(id);
    }

    @Override
    public List<Blog> listblog() {
        return blogMapper.listblog();
    }

    @Override
    public List<BlogQuery> queryBlogByTypeOrTagOrRecommend(SearchBlog searchBlog) {
        return blogMapper.queryBlogByTypeOrTagOrRecommend(searchBlog);
    }

    @Transactional
    @Override
    public int addBlog(Blog blog) {
        Long id = new Date().getTime();
        blog.setId(id);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //数据保存到blog_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = new BlogAndTag();
        for (Tag tag : tags){
            System.out.println(blog.getId());
            blogAndTag.setBlogId(blog.getId());
            blogAndTag.setTagId(tag.getId());
            blogMapper.addBlogAndTags(blogAndTag);
        }
        return blogMapper.addBlog(blog);
    }

    @Transactional
    @Override
    public int updBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //数据保存到blog_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = new BlogAndTag();
        //先删除掉blog_tags中关于该博客的数据
        blogMapper.delBlogAndTag(blog.getId());
        for (int i=0;i<tags.size();i++){
            blogAndTag.setBlogId(blog.getId());
            blogAndTag.setTagId(tags.get(i).getId());
            blogMapper.addBlogAndTags(blogAndTag);
        }
        return blogMapper.updBlog(blog);
    }

    @Transactional
    @Override
    public void delBlog(Long id) {
        blogMapper.delBlogAndTag(id);
        blogMapper.delBlog(id);
    }

    @Override
    public List<IndexBlog> queryIndexBlog() {
        return blogMapper.queryIndexBlog();
    }

    @Override
    public List<RecommendBlog> queryRecommendBlog() {
        return blogMapper.queryRecommendBlog();

    }

    @Override
    public List<RecommendBlog> sortTag(List<RecommendBlog> rbs) {
        Collections.sort(rbs, new Comparator<RecommendBlog>() {
            @Override
            public int compare(RecommendBlog o1, RecommendBlog o2) {
                Date dt0 = o1.getCreateTime();
                Date dt1 = o2.getCreateTime();
                int i = dt0.compareTo(dt1);
                if(i==-1){
                    return 1;
                }else if(i==0){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        List<RecommendBlog> rb = new ArrayList<>();
        //获取前5条
        for(int i=0;i<5;i++){
            rb.add(rbs.get(i));
        }
        return rb;
    }

    @Override
    public List<IndexBlog> queryAllBlog(String query) {
        return blogMapper.queryAllBlog(query);
    }

    @Override
    public Blog queryDetailBlogById(Long id) {
        return blogMapper.queryDetailBlogById(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogMapper.queryDetailBlogById(id);
        String content = blog.getContent();
        blog.setContent(MarkdownUtil.markdownToHtmlExtensions(content));
        return blog;
    }

    @Override
    public List<IndexBlog> queryIndexBlogByTypeOrTag(SearchBlog searchBlog) {
        return blogMapper.queryIndexBlogByTypeOrTag(searchBlog);
    }

    @Override
    public List<DateTime> queryAllDate() {
        return blogMapper.queryAllDate();
    }

    @Override
    public List<Blog> queryAllBlogByYear(String year) {
        return blogMapper.queryAllBlogByYear(year);
    }

    @Override
    public void addBlogViews(Long id) {
        blogMapper.addBlogViews(id);
    }


}
