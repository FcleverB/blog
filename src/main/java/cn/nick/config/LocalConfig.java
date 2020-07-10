package cn.nick.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocalConfig implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获得请求中的语言参数
        String language = httpServletRequest.getParameter("l");
        Locale locale = Locale.getDefault();    //没有就使用默认
        //有就进行分割处理
        if(!StringUtils.isEmpty(language)){
            String[] split = language.split("_");
            //国家、地区
            locale = new Locale(split[0], split[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
