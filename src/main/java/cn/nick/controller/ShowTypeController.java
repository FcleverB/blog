package cn.nick.controller;

import cn.nick.dto.IndexBlog;
import cn.nick.dto.SearchBlog;
import cn.nick.pojo.Type;
import cn.nick.service.BlogService;
import cn.nick.service.TypeService;
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
public class ShowTypeController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping("/toType/{id}")
    public String toType(HttpSession httpSession, Model model, @PathVariable Long id){
        List<Type> types = typeService.sortType(typeService.queryTypeAndBlog());
        model.addAttribute("types",types);
        //默认为id在最前面的type
        if(id==-1){
            id = types.get(0).getId();
        }
        model.addAttribute("activeTypeId",id);
        SearchBlog searchBlog = new SearchBlog();
        searchBlog.setTypeId(id);
        httpSession.setAttribute("sb",searchBlog);
        PageHelper.startPage(1,5);
        List<IndexBlog> indexBlogs = blogService.queryIndexBlogByTypeOrTag(searchBlog);
        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogs);
        model.addAttribute("pageInfo",pageInfo);
        return "types";
    }
    //通过session拿到查询的数据并分页
    @GetMapping("/type/page")
    public String page(Model model,HttpSession httpSession, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<Type> types = typeService.sortType(typeService.queryTypeAndBlog());
        model.addAttribute("types",types);
        SearchBlog searchBlog = (SearchBlog) httpSession.getAttribute("sb");
        PageHelper.startPage(pageNum,5);
        List<IndexBlog> indexBlogs = blogService.queryIndexBlogByTypeOrTag(searchBlog);

        PageInfo<IndexBlog> pageInfo = new PageInfo<>(indexBlogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",searchBlog.getTypeId());
        return "types";
    }

}
