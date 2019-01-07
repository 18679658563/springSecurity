package com.rz.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description: WebMvcConfigurerAdapter在spring5.0之后被废除
 * User: silence
 * Date: 2018-12-25
 * Time: 上午8:53
 */

@Configuration
public class WebMvcConfig implements   WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

}