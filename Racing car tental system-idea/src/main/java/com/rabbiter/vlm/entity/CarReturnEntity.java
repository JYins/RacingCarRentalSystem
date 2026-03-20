package com.rabbiter.vlm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Car Return Entity
 * DB entity for CRUD ops
 */
@TableName("car_return")
public class CarReturnEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Primary key
	 */
	@TableId
	private Long id;
	/**
	 * Car name
	 */
	@TableField("car_name")
	private String carName;

	/**
	 * Car type
	 */
	@TableField("car_type")
	private String carType;

	/**
	 * Brand
	 */
	@TableField("brand")
	private String brand;

	/**
	 * License plate number
	 */
	@TableField("license_plate_number")
	private String licensePlateNumber;

	/**
	 * Return time
	 */
	@TableField("return_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date returnTime;

	/**
	 * Return content
	 */
	@TableField("return_content")
	private String returnContent;

	/**
	 * Username
	 */
	@TableField("username")
	private String username;

	/**
	 * Real name
	 */
	@TableField("real_name")
	private String realName;

	/**
	 * Mobile phone
	 */
	@TableField("mobile_phone")
	private String mobilePhone;

	/**
	 * Review status
	 */
	@TableField("is_reviewed")
	private String isReviewed;

	/**
	 * Review response
	 */
	@TableField("review_response")
	private String reviewResponse;

	@TableField("add_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getReturnContent() {
		return returnContent;
	}

	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public CarReturnEntity(Long id, String carName, String carType, String brand, String licensePlateNumber, Date returnTime, String returnContent, String username, String realName, String mobilePhone, String isReviewed, String reviewResponse, Date addTime) {
		this.id = id;
		this.carName = carName;
		this.carType = carType;
		this.brand = brand;
		this.licensePlateNumber = licensePlateNumber;
		this.returnTime = returnTime;
		this.returnContent = returnContent;
		this.username = username;
		this.realName = realName;
		this.mobilePhone = mobilePhone;
		this.isReviewed = isReviewed;
		this.reviewResponse = reviewResponse;
		this.addTime = addTime;
	}

	public CarReturnEntity() {
	}



}
