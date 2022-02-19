package com.authorize.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//暂时忽略配置使用配置文件中的配置 注释
//@Service("userDetailsService")
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    /**
     * 编写实现类实现UserDetailsService，重写loadUserByUsername()方法
     * @param username - 前台传入的参数
     * @return
     * @throws UsernameNotFoundException
     * 返回一个实现UserDetails接口的User对象
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 假定创建有一个这样的权限组
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        // 填入用户名 密码 以及权限组（权限组不能为null）
        return new User("admin", new BCryptPasswordEncoder().encode("admin"), auths);
    }
}