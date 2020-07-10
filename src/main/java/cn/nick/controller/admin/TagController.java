package cn.nick.controller.admin;

import cn.nick.pojo.Tag;
import cn.nick.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    //展示分页列表
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Tag> all = tagService.listTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(all);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    //进入新增分类页面
    @GetMapping("/tags/add")
    public String add(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    //新增或修改分类
    @PostMapping("/tags/add")
    public String postAdd(Tag tag, RedirectAttributes attributes){
        System.out.println(tag.getName());
        System.out.println(tag.getId());
        //id不为空则修改，为空则新增
        if(tag.getId()!=null){
            if(tagService.updTag(tag)!=0){
                attributes.addFlashAttribute("msg","修改成功！");
                return "redirect:/admin/tags";
            }else{
                attributes.addFlashAttribute("errormsg","修改失败！");
                return "redirect:/admin/tags";
            }
        }else{
            if(tagService.queryTagByName(tag.getName())!=null){
                //已存在相同的分类
                attributes.addFlashAttribute("msg","不能重复添加已存在的类！");
                return "redirect:/admin/tags/add";
            }else{
                tagService.addTag(tag);
                attributes.addFlashAttribute("msg","添加成功！");
                return "redirect:/admin/tags";
            }
        }

    }

    //进入修改页面
    @GetMapping("/tags/{id}/upd")
    public String upd(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.queryTagById(id));
        return "admin/tags-input";
    }

    //删除分类
    @GetMapping("/tags/{id}/del")
    public String del(@PathVariable Long id, RedirectAttributes attributes){
        tagService.delTag(id);
        attributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/tags";
    }
}
