package com.authorize.basic.entity;

import java.io.Serializable;

/**
 * Role实体类
 * 封装角色信息
 */
public class Role implements Serializable {

    private Integer id;
    private String name;

    public Role() {
        super();
    }
    public Role(String name) {
        super();
        this.name = name;
    }

    // getter & setter
    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + "]";
    }
}
