package com.authorize.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 1）、编写一个类继承WebSecurityConfigurerAdapter抽象类
 * 2）、重写configure(AuthenticationManagerBuilder auth) 方法设置用户名和密码
 * 实现AuthenticationManager 认证管理器
 * 三种方式：application 配置文件中配置；编写Java程序配置注入内存；自定义Java配置文件读取数据库数据。
 * 注入配置文件
 */
//暂时忽略配置使用配置文件中的配置 注释
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置密码加密方式
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 对密码进行加密
        String password = passwordEncoder.encode("admin");
        // inMemoryAuthentication-在内存中设置用户名和密码
        // roles("admin") - 角色权限
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("admin");

    }

    // There is no PasswordEncoder mapped for the id "null"
    // 密码加密需要自己手动创建 PasswordEncoder 对象
    // PasswordEncoder是接口  BCryptPasswordEncoder 实现了PasswordEncoder接口
    @Bean
    PasswordEncoder password() {
        return  new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }


}
