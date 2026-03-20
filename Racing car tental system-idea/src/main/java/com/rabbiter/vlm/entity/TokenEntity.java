package com.rabbiter.vlm.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Token Entity
 */
@TableName("token")
public class TokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * User ID
	 */
	private Long userId;

	/**
	 * Username
	 */
	private String username;

	/**
	 * Table name
	 */
	private String tableName;

	/**
	 * Role
	 */
	private String role;

	/**
	 * Token
	 */
	private String token;

	/**
	 * Expiration time
	 */
	private Date expirationTime;

	/**
	 * Add time
	 */
	private Date addTime;

	public TokenEntity(Long userId, String username, String tableName,String role, String token, Date expirationTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.tableName = tableName;
		this.role = role;
		this.token = token;
		this.expirationTime = expirationTime;
	}

	public TokenEntity() {
	}

	public TokenEntity(Long id, Long userId, String username, String tableName, String role, String token, Date expirationTime, Date addTime) {
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.tableName = tableName;
		this.role = role;
		this.token = token;
		this.expirationTime = expirationTime;
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "TokenEntity{" +
				"id=" + id +
				", userId=" + userId +
				", username='" + username + '\'' +
				", tableName='" + tableName + '\'' +
				", role='" + role + '\'' +
				", token='" + token + '\'' +
				", expirationTime=" + expirationTime +
				", addTime=" + addTime +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
