package com.rabbiter.vlm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * User Entity
 * DB entity for CRUD ops
 */
@TableName("user")
public class UserEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Primary key
	 */
	@TableId
	private Long id;
	/**
	 * Username
	 */
	@TableField("username")
	private String username;

	/**
	 * Password
	 */
	@TableField("password")
	private String password;

	/**
	 * Real name
	 */
	@TableField("real_name")
	private String realName;

	/**
	 * Gender
	 */
	@TableField("gender")
	private String gender;

	/**
	 * Birthday
	 */
	@TableField("birthday")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
	private Date birthday;

	/**
	 * ID number
	 */
	@TableField("identification_number")
	private String identificationNumber;

	/**
	 * Mobile phone
	 */
	@TableField("mobile_phone")
	private String mobilePhone;

	/**
	 * Photo
	 */
	@TableField("photo")
	private String photo;

	@TableField("add_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addTime;


	@TableField("is_reviewed")
	private String isReviewed;

	@TableField("review_response")
	private String reviewResponse;

	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", realName='" + realName + '\'' +
				", gender='" + gender + '\'' +
				", birthday=" + birthday +
				", identificationNumber='" + identificationNumber + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", photo='" + photo + '\'' +
				", addTime=" + addTime +
				", isReviewed='" + isReviewed + '\'' +
				", reviewResponse='" + reviewResponse + '\'' +
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getIsReviewed() {
		return isReviewed;
	}

	public void setIsReviewed(String isReviewed) {
		this.isReviewed = isReviewed;
	}

	public String getReviewResponse() {
		return reviewResponse;
	}

	public void setReviewResponse(String reviewResponse) {
		this.reviewResponse = reviewResponse;
	}

	public UserEntity(Long id, String username, String password, String realName, String gender, Date birthday, String identificationNumber, String mobilePhone, String photo, Date addTime, String isReviewed, String reviewResponse) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.identificationNumber = identificationNumber;
		this.mobilePhone = mobilePhone;
		this.photo = photo;
		this.addTime = addTime;
		this.isReviewed = isReviewed;
		this.reviewResponse = reviewResponse;
	}

	public UserEntity() {
	}
}
