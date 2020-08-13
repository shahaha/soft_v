package com.skylab.soft_v.config;

import com.skylab.soft_v.common.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 集成swagger2  解决前后端分离 弊端：不能及时协商+今早解决的问题
 *      使用swagger总结:
 *          通过swagger 给一些比基奥难理解的接口或属性，增加注释信息
 *          接口文档实时更新
 *          可以在线测试
 *      安全问题:
 *          正式上线的时候  记得关闭swagger
 */
@Configuration//加载到springboot配置里面
@EnableSwagger2//开启swagger2
public class SwaggerConfig {
    /**
     * 配置swagger2
     * 注册一个bean属性
     * swagger2其实就是重新写一个构造器，因为他没有get set方法\
     * enable() 是否启用swagger false swagger不能再浏览器中访问
     * groupName()配置api文档的分组  那就注册多个Docket实例 相当于多个分组
     * @return Docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("天工")//组名称
                //.enable(true)
                .select()
                /**
                 * RequestHandlerSelectors配置扫描接口的方式
                 *      basePackage 配置要扫描的包
                 *      any 扫描全部
                 *      none 不扫描
                 *      withClassAnnotation 扫描类上的注解
                 *      withMethodAnnotation 扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.basePackage("com.skylab.soft_v.controller"))
                /**
                 * paths() 扫描过滤方式
                 *      any过滤全部
                 *      none不过滤
                 *      regex正则过滤
                 *      ant过滤指定路径
                 */
                //                .paths(PathSelectors.ant("/login/**"))
                .build()
                .globalOperationParameters(setHeaderToken());
    }
    /**
     * 配置swagger2信息 =apiInfo
     * @return ApiInfo
     */
    private ApiInfo apiInfo(){
        /*作者信息*/
        //        Contact contact = new Contact("XXX", "http://baidu.com", "email");
        Contact contact = new Contact("天工测控", "http://www.skylab.com.cn/", "sales1@skylab.com.cn");
        return new ApiInfoBuilder().title("软件管理系统API接口")
                                    .description("controller中接口的测试与管理")
                                    .termsOfServiceUrl("urn:toVs")
                                    .contact(contact)
                                    .version("1.0")
                                    .build();
    }

    /**
     * JWT token
     * @return
     */
    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder refreshTokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(Const.ACCESS_TOKEN).description("swagger测试用(模拟authorization传入)非必填 header").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        refreshTokenPar.name(Const.REFRESH_TOKEN).description("swagger测试用(模拟刷新token传入)非必填 header").modelRef(new ModelRef("string")).parameterType("header").required(false);
        pars.add(tokenPar.build());
        pars.add(refreshTokenPar.build());
        return pars;
    }
}