package cn.nick.controller;


import cn.nick.dto.IndexBlog;
import cn.nick.dto.RecommendBlog;
import cn.nick.pojo.Blog;
import cn.nick.pojo.Tag;
import cn.nick.pojo.Type;
import cn.nick.service.BlogService;
import cn.nick.service.TagService;
import cn.nick.service.TypeService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

//    @RequestMapping("/toLogin")
//    public String toLogin(){
//        return "admin/blogs";
//    }

    @GetMapping("/")
    public String index(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Type> types = typeService.queryTypeAndBlog();
        List<Tag> tags = tagService.queryTagAndBlog();
        List<RecommendBlog> rbs = blogService.queryRecommendBlog();
        //判断是否超过5条，如果超过则排序后输出到页面值展示前5个
        if(rbs.size()>5)
            rbs = blogService.sortTag(rbs);
        if(tags.size()>5)
            tags = tagService.selectFive(tagService.sortTag(tags));
        if(types.size()>5)
            types = typeService.selectFive(typeService.sortType(types));
        PageHelper.startPage(pageNum,5);
        List<IndexBlog> indexBlogs = blogService.queryIndexBlog();
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        model.addAttribute("rbs",rbs);
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model,HttpSession httpSession,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,@RequestParam String query){
        PageHelper.startPage(pageNum,5);
        List<IndexBlog> indexBlogs = blogService.queryAllBlog(query);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogs);
        //将查询内容放入session方便分页
        httpSession.setAttribute("search",query);
        model.addAttribute("pageInfo",pageInfo);
        return "search";
    }

    //通过session找到前台查询的输入内容并分页
    @GetMapping("/search/page")
    public String searchPage(HttpSession httpSession,Model model,
                             @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String query = (String) httpSession.getAttribute("search");
        PageHelper.startPage(pageNum,5);
        List<IndexBlog> indexBlogs = blogService.queryAllBlog(query);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogs);
        model.addAttribute(pageInfo);
        return "search";
    }

    //进入博客详情页
    @GetMapping("/blog/{id}/detail")
    public String blogDetail(@PathVariable Long id, Model model){
        blogService.addBlogViews(id);
        Blog blog = blogService.getAndConvert(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    //进入关于我页面
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
