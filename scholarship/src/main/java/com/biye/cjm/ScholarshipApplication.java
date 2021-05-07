package com.biye.cjm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author chenjiaming
 * @date 2021-02-23
 */
@SpringBootApplication
@MapperScan("com.biye.cjm.mapper") //扫描的mapper
@EnableOpenApi
public class ScholarshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScholarshipApplication.class, args);
    }

}
