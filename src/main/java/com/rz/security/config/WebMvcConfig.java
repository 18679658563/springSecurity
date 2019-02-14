package com.rz.security.config;

import com.rz.security.page.PageTableArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: WebMvcConfigurerAdapter在spring5.0之后被废除
 * User: silence
 * Date: 2018-12-25
 * Time: 上午8:53
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域支持
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

    /**
     * datatable分页解析
     * @return
     */
    @Bean
    public PageTableArgumentResolver tableHandlerMethodArgumentResolver(){
        return new PageTableArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(tableHandlerMethodArgumentResolver());
    }

}