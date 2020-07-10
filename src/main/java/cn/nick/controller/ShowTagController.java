package cn.nick.controller;

import cn.nick.dto.IndexBlog;
import cn.nick.dto.SearchBlog;
import cn.nick.pojo.Tag;
import cn.nick.service.BlogService;
import cn.nick.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShowTagController {

    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @GetMapping("/toTag/{id}")
    public String toTag(HttpSession httpSession, Model model, @PathVariable Long id){
        List<Tag> tags = tagService.sortTag(tagService.queryTagAndBlog());
        model.addAttribute("tags",tags);
        //默认为id在最前面的type
        if(id==-1){
            id = tags.get(0).getId();
        }
        SearchBlog searchBlog = new SearchBlog();
        searchBlog.setTagId(id);
        httpSession.setAttribute("sb",searchBlog);
        model.addAttribute("activeTagId",id);
        PageHelper.startPage(1,5);
        List<IndexBlog> indexBlogs = blogService.queryIndexBlogByTypeOrTag(searchBlog);

        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogs);
        model.addAttribute("pageInfo",pageInfo);
        return "tags";
    }

    //通过session拿到查询的数据并分页
    @GetMapping("/tag/page")
    public String page(Model model,HttpSession httpSession, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Tag> tags = tagService.sortTag(tagService.queryTagAndBlog());
        model.addAttribute("tags",tags);
        SearchBlog searchBlog = (SearchBlog) httpSession.getAttribute("sb");
        PageHelper.startPage(pageNum,5);
        List<IndexBlog> indexBlogs = blogService.queryIndexBlogByTypeOrTag(searchBlog);

        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId",searchBlog.getTypeId());
        return "tags";
    }
}
