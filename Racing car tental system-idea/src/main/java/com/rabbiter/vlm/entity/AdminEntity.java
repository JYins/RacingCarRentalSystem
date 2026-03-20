package com.rabbiter.vlm.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Admin Entity
 */
@TableName("admin")
public class AdminEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

    /**
     * User role
     */
    private String role;
    @TableField("add_time")
    private Date addTime;

    public AdminEntity() {
    }

    public AdminEntity(Long id, String username, String password, String role, Date addTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "AdminEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", addTime=" + addTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
