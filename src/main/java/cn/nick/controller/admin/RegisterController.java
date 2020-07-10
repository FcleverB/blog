package cn.nick.controller.admin;

import cn.nick.pojo.User;
import cn.nick.service.UserService;
import cn.nick.util.CryptoUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestParam String regname, @RequestParam String regpass){
        // 0.对密码进行MD5加密
        CryptoUtil cryptoUtil = new CryptoUtil();
        regpass = cryptoUtil.md5(regpass,"");
        //2. 如果没有重复用户名,则将用户信息封装加密后保存到数据库内
        User user = new User(regname, regpass);
        userService.registerUser(user);
        //3. 注册成功后,跳转到登录页面
        return "redirect:/admin";

    }

    @PostMapping("/registername")
    @ResponseBody
    public String registerName(@RequestParam String regname, @RequestParam String regpass){
        //1. 需要做查重处理,检查数据库中是否存在同名用户名
        String row = userService.findUserByName(regname);
        return row;
    }
}
