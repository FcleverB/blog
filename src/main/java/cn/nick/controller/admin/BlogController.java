package cn.nick.controller.admin;
import cn.nick.dto.BlogQuery;
import cn.nick.dto.SearchBlog;
import cn.nick.pojo.Blog;
import cn.nick.pojo.Tag;
import cn.nick.pojo.Type;
import cn.nick.pojo.User;
import cn.nick.service.BlogService;
import cn.nick.service.TagService;
import cn.nick.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(HttpSession httpSession,Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<BlogQuery> blogQueryList = blogService.queryBlogByTypeOrTagOrRecommend(new SearchBlog());
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogQueryList);
        List<Type> types = typeService.listType();
        List<Tag> tags = tagService.listTag();
        httpSession.setAttribute("tags",tags);
        httpSession.setAttribute("types",types);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
         return "admin/blogs";
    }

    //按条件查找
    @PostMapping("/blogs/search")
    public String search(HttpSession httpSession,SearchBlog searchBlog, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        //将获得的String类型recommend转换成int
        if (!"".equals(searchBlog.getRecommend()) && null != searchBlog.getRecommend()) {
            searchBlog.setRecommend2(1);
        }
        //将查询内容放入session方便分页
        httpSession.setAttribute("search",searchBlog);
        List<BlogQuery> blogQueryList = blogService.queryBlogByTypeOrTagOrRecommend(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogQueryList);
        model.addAttribute("types",httpSession.getAttribute("types"));
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs-search";
    }

    //通过session找到前台查询的输入内容并分页
    @GetMapping("/blogs/page")
    public String page(HttpSession httpSession,Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        model.addAttribute("types",httpSession.getAttribute("types"));
        SearchBlog searchBlog = (SearchBlog) httpSession.getAttribute("search");
        PageHelper.startPage(pageNum,5);
        List<BlogQuery> blogQueryList = blogService.queryBlogByTypeOrTagOrRecommend(searchBlog);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogQueryList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs-search";
    }

    //到新增页面
    @GetMapping("/blogs/add")
    public String add(Model model,HttpSession httpSession){
        model.addAttribute("blog",new Blog());
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        httpSession.setAttribute("types",types);
        List<Tag> tags = tagService.listTag();
        model.addAttribute("tags",tags);
        httpSession.setAttribute("tags",tags);
        return "admin/blogs-input";
    }


    //新增或更新博客
    @PostMapping("/blogs/add")
    public String postAdd(Blog blog, HttpSession httpSession, RedirectAttributes redirectAttributes){
        //从session中获取user的信息
        User user = (User) httpSession.getAttribute("user");
        blog.setUser(user);
        blog.setUserId(user.getId());
        blog.setType(typeService.queryTypeById(blog.getTypeId()));
        blog.setTags(tagService.changeTagsToList(blog.getTagIds()));
        //id不为空则修改，为空则新增
        if(blog.getId()!=null){
            //从session中获得blog将创建时间传入更新,以及浏览次数
            Blog blog1 = (Blog) httpSession.getAttribute("blog");
            blog.setViews(blog1.getViews());
            blog.setCreateTime(blog1.getCreateTime());
            if(blogService.updBlog(blog)!=0){
                redirectAttributes.addFlashAttribute("msg", "博客更新成功！");
                return "redirect:/admin/blogs";
            }else{
                redirectAttributes.addFlashAttribute("errormsg", "博客更新失败！");
                return "redirect:/admin/blogs";
            }
        }else {
            blogService.addBlog(blog);
            redirectAttributes.addFlashAttribute("msg", "博客上传成功！");
            return "redirect:/admin/blogs";
        }
    }

    //到修改页面
    @GetMapping("/blogs/{id}/upd")
    public String upd(@PathVariable Long id, Model model, HttpSession httpSession){
        Blog blog = blogService.queryBlogById(id);
        httpSession.setAttribute("blog",blog);
        System.out.println(blog.getTagIds());
        model.addAttribute("blog",blog);
        model.addAttribute("types",httpSession.getAttribute("types"));
        model.addAttribute("tags",httpSession.getAttribute("tags"));
        return "admin/blogs-input";
    }

    //删除博客
    @GetMapping("/blogs/{id}/del")
    public String del(@PathVariable Long id,RedirectAttributes redirectAttributes){
        blogService.delBlog(id);
        redirectAttributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/blogs";
    }
}
