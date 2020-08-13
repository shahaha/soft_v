package com.skylab.soft_v.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.skylab.soft_v.common.ShiroCacheManager;
import com.skylab.soft_v.component.CustomShiroRealm;
import com.skylab.soft_v.component.JWTFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    @Bean
    public ShiroCacheManager cacheManager(){
        return new ShiroCacheManager();
    }

    @Bean
    public CustomShiroRealm customShiroRealm(){
        CustomShiroRealm customRealm=new CustomShiroRealm();
        customRealm.setCacheManager(cacheManager());
        return customRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customShiroRealm());
        return defaultWebSecurityManager;
    }

    //@Bean("shiroFilter")
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //log.info("ShiroConfig-->>>shiroFilterFactoryBean");
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        //放行登录接口和其他不需要权限的接口
        filterRuleMap.put("/toLogin", "anon");
        filterRuleMap.put("/index/**", "anon");
        filterRuleMap.put("/user/login", "anon");

        // 放行不需要权限认证的接口
        //放行Swagger接口
        filterRuleMap.put("/", "anon");
        filterRuleMap.put("/**/*.js", "anon");
        filterRuleMap.put("/**/*.css", "anon");
        filterRuleMap.put("/**/*.html", "anon");
        filterRuleMap.put("/**/*.svg", "anon");
        filterRuleMap.put("/**/*.jpg", "anon");
        filterRuleMap.put("/**/*.png", "anon");
        filterRuleMap.put("/**/*.woff", "anon");
        filterRuleMap.put("/**/*.woff2", "anon");
        filterRuleMap.put("/**/*.gif", "anon");
        filterRuleMap.put("/**/*.map", "anon");
        filterRuleMap.put("/**/*.ttf", "anon");
        filterRuleMap.put("/**/*.ico", "anon");
        filterRuleMap.put("/druid/**", "anon");
        filterRuleMap.put("/swagger/**", "anon");
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/favicon.ico", "anon");
        filterRuleMap.put("/captcha.jpg", "anon");
        filterRuleMap.put("/swagger**/**","anon");
        filterRuleMap.put("/v2/**", "anon");
        filterRuleMap.put("/csrf", "anon");
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt,authc");
        // 设置无权限时跳转的 url;
        factoryBean.setUnauthorizedUrl("/error");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;

    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}