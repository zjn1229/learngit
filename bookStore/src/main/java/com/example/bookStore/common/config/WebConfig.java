package com.example.bookStore.common.config;

import com.example.bookStore.common.utils.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new MyInterceptor();
    }
    /**
     * 添加Web项目的拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有访问路径，都通过MyInterceptor类型的拦截器进行拦截
        registry.addInterceptor(getMyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register", "/book/list", "/book/getById",
                        "/book/listByShopId", "/shop/list", "/shop/getById", "/category/list",
                        "/evaluation/pageByBookId");
        //放行游客资源
    }
}
