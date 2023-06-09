package com.example.bookStore.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    @Bean
    public Docket exampleApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("example")
                .apiInfo(exampleApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bookStore.example.controller"))
                .build();
    }

    private ApiInfo exampleApiInfo() {
        return new ApiInfoBuilder()
                .title("example模块 API")
                .build();
    }


}
