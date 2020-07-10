package cn.nick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2     //开启Swagger2
public class SwaggerConfig {

    //配置swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){ //传入参数判断配置环境进行不同环境开启Swagger Environment

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");
        //environment.acceptsProfiles(profiles)来获取是否是该环境
        Boolean b = environment.acceptsProfiles(profiles);
        //创建一个Contact为下面使用
        Contact contact = new Contact("Nick Ming", "http://182.92.202.48:8080/springboot-blog/", "944757776@qq.com");

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("Swagger Api for Blog",
                        "练习写的Blog项目的Api文档",
                        "1.0",
                        "http://182.92.202.48:8080/springboot-blog/",
                        contact,
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList()))
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                /*
                    .basePackage    //指定要扫描的包
                    .any            //扫描全部
                    .none           //都不扫描
                    .withClassAnnotation    //扫描类上的注解   例:.withClassAnnotation(RestController.class)
                    .withMethodAnnotation   //扫描方法上的注解  例：.withMethodAnnotation(GetMapping.class)
                 */
                .apis(RequestHandlerSelectors.basePackage("cn.nick.controller"))
                //过滤路径  例；.ant("/admin/**") 只扫描路径为/admin/**的接口
                .paths(PathSelectors.any())
                .build()
                //是否开启Swagger  默认为true  这里通过配置环境来判断是否开启
                .enable(b)
                .groupName("Nick Ming")     //将swagger分组（想配置多个组就new多个Docket）
                ;
    }
}
