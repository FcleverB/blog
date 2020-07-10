package cn.nick.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /*
        anon:无需认证即可访问
        authc:认证后才可访问
        user:必须拥有记住我功能才能使用
        perms:拥有对某个资源的权限才能访问
        role：拥有某个角色权限才能访问
     */
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //关联SecurityManager
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //授权：

        filterChainDefinitionMap.put("/admin","anon");
        filterChainDefinitionMap.put("/admin/login","anon");
        //放行注册
        filterChainDefinitionMap.put("/admin/register","anon");
        filterChainDefinitionMap.put("/admin/registername","anon");
        filterChainDefinitionMap.put("/admin/**","authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //设置登录请求
        filterFactoryBean.setLoginUrl("/admin");
        return filterFactoryBean;
    }


    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建Realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
