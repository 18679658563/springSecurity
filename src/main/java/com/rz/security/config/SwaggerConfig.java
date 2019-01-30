package com.rz.security.config;

import com.google.common.collect.Lists;
import com.rz.security.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IntelliJ IDEA.
 * Description: swagger文档配置类
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:43
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        ParameterBuilder builder = new ParameterBuilder();
        builder.parameterType("header").name(TokenFilter.TOKEN_KEY)
                .description("header参数")
                .required(false)
                .modelRef(new ModelRef("String"));//在swagger里显示header
        return new Docket(DocumentationType.SWAGGER_2).groupName("swagger接口文档")
                .apiInfo(new ApiInfoBuilder().title("swagger接口文档")
                        .contact(new Contact("silence","","m18679658563@163.com")).version("1.0").build())
                .globalOperationParameters(Lists.newArrayList(builder.build()))
                .select().paths(PathSelectors.any()).build();
    }

}