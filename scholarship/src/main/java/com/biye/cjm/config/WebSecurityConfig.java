//package com.biye.cjm.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @Author CJM
// * @Date 2021-03-21  14:53
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                // 如果有允许匿名的url，填在下面
////                .antMatchers().permitAll()
//                .anyRequest().authenticated()
//                .and()
//                // 设置登陆页
//                .formLogin().loginPage("http://127.0.0.1:3000/")
//                // 设置登陆成功页
//                .defaultSuccessUrl("/").permitAll()
//                // 自定义登陆用户名和密码参数，默认为username和password
////                .usernameParameter("username")
////                .passwordParameter("password")
//                .and()
//                .cors()
//                .and()
//                .logout().permitAll();
//
//        // 关闭CSRF跨域
////        http.csrf().disable();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 设置拦截忽略文件夹，可以对静态资源放行
//        web.ignoring().antMatchers("/css/**", "/js/**");
//    }
//}
//
//
