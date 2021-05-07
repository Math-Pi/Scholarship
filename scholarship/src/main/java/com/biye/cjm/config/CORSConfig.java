package com.biye.cjm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author CJM
 * @Date 2021-03-21  15:30
 * @Description 配置跨域请求
 */
@Configuration
public class CORSConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000","http://127.0.0.1:3000")
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .allowCredentials(true) // 表示是否可以将对请求的响应暴露给页面
                        .allowedHeaders("*")
                        .maxAge(3600);
            }
        };
    }
}