package com.skylab.soft_v.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
//    private final String[] excludePath = {"/static","/","/index","/toLogin","/user/login","/webjars/**","/layuiadmin/**","/reg","/forget","/swagger-ui.html","/swagger-resources/**"};
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index/success").setViewName("main");
        registry.addViewController("/toLogin").setViewName("user/login");
    }
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        /* 是否允许请求带有验证信息 */
        corsConfiguration.setAllowCredentials(true);
        /* 允许访问的客户端域名 */
        corsConfiguration.addAllowedOrigin("*");
        /* 允许服务端访问的客户端请求头 */
        corsConfiguration.addAllowedHeader("*");
        /* 允许访问的方法名,GET POST等 */
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerIntceptor()).addPathPatterns("/**")
//                .excludePathPatterns(excludePath);
//
//    }
}