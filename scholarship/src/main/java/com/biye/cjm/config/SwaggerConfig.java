package com.biye.cjm.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author chenjiaming
 * @Date 2021/2/24
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket createRestfulApiDocs() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("奖学金管理系统")
                .description("基于Springboot、React的奖学金管理系统！！！")
//                .termsOfServiceUrl("https://blog.csdn.net")
//                .contact(new Contact("chenjiaming", "https://www.baidu.com", "wscjm@outlook.com"))
                .version("1.0.0")
                .build();
    }
}

