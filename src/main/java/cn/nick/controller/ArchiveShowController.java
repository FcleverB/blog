package cn.nick.controller;

import cn.nick.dto.DateTime;
import cn.nick.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArchiveShowController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        List<DateTime> dateTimes = blogService.queryAllDate();
        int total = 0;
        //循环将所有博客归档
        for(int i=0;i<dateTimes.size();i++){
           dateTimes.get(i).setBlogs(blogService.queryAllBlogByYear(dateTimes.get(i).getYear()));
           //计算博客总数
           total = dateTimes.get(i).getBlogs().size()+total;
        }
        model.addAttribute("dateTimes",dateTimes);
        model.addAttribute("total",total);
        return "archives";
    }
}
