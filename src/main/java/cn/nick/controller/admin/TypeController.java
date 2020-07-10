package cn.nick.controller.admin;

import cn.nick.pojo.Type;
import cn.nick.service.TypeService;
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
public class TypeController{

    @Autowired
    private TypeService typeService;

    //展示分页列表
    @GetMapping("/types")
    public String list(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Type> all = typeService.listType();
        PageInfo<Type> pageInfo = new PageInfo<>(all);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    //进入新增分类页面
    @GetMapping("/types/add")
    public String add(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //新增或修改分类
    @PostMapping("/types/add")
    public String postAdd(Type type, RedirectAttributes attributes){
        System.out.println(type.getName());
        System.out.println(type.getId());
        //id不为空则修改，为空则新增
        if(type.getId()!=null){
            if(typeService.updType(type)!=0){
                attributes.addFlashAttribute("msg","修改成功！");
                return "redirect:/admin/types";
            }else{
                attributes.addFlashAttribute("errormsg","修改失败！");
                return "redirect:/admin/types";
            }
        }else{
            if(typeService.queryTypeByName(type.getName())!=null){
                //已存在相同的分类
                attributes.addFlashAttribute("msg","不能重复添加已存在的类！");
                return "redirect:/admin/types/add";
            }else{
                typeService.addType(type);
                attributes.addFlashAttribute("msg","添加成功！");
                return "redirect:/admin/types";
            }
        }

    }

    //进入修改页面
    @GetMapping("/types/{id}/upd")
    public String upd(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.queryTypeById(id));
        return "admin/types-input";
    }

    //删除分类
    @GetMapping("/types/{id}/del")
    public String del(@PathVariable Long id, RedirectAttributes attributes){
        typeService.delType(id);
        attributes.addFlashAttribute("msg","删除成功！");
        return "redirect:/admin/types";
    }
}
