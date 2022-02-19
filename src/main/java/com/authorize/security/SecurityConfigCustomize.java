package com.authorize.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义实现类设置账号密码
 */
//暂时忽略配置使用配置文件中的配置 注释
//@Configuration
public class SecurityConfigCustomize extends WebSecurityConfigurerAdapter {

    // 注入UserDetailsService
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 将UserDetailsService接口注入auth的userDetailsService()方法中
         * passwordEncoder(password()) 对密码进行加密
         */
        auth.userDetailsService(userDetailsService).passwordEncoder(password());

    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

}