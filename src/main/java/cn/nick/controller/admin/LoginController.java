package cn.nick.controller.admin;
import cn.nick.pojo.User;
import cn.nick.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cn.nick.util.CryptoUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    //跳转至登录页面
    @GetMapping
    public String loginPage(){
        return "/admin/login";
    }

    //登录处理
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        RedirectAttributes attributes){
        System.out.println(username);
        CryptoUtil cryptoUtil = new CryptoUtil();
        password = cryptoUtil.md5(password,"");
        //获取当前用户数据
        Subject subject = SecurityUtils.getSubject();
        //封装用户信息
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        try{
            subject.login(token);
            return "admin/index";
        }catch (UnknownAccountException e1){
            attributes.addFlashAttribute("msg","用户名不存在！");
            return "redirect:/admin";
        }catch (IncorrectCredentialsException e2){
            attributes.addFlashAttribute("msg","密码错误！");
            return "redirect:/admin";
        }

    }

    //注销用户
    @GetMapping("/logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/admin";
    }

    //跳转至修改个人信息
    @GetMapping("/personal")
    public String personalInformation(){
        return "/admin/personal-information";
    }

    //修改个人信息
    @PostMapping("changepi")
    public String changPI(User user){
        System.out.println(user.getAvatar());
        System.out.println(user.getNickname());
        User userLogin  = (User) SecurityUtils.getSubject().getPrincipal();
        userLogin.setAvatar(user.getAvatar());
        userLogin.setNickname(user.getNickname());
        System.out.println(userLogin.getNickname());
        userService.updUser(user);
        return "admin/index";
    }
}
