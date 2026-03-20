package com.rabbiter.vlm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Use Order Entity
 * DB entity for CRUD ops
 */
@TableName("use_order")
public class UseOrderEntity<T> implements Serializable {
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
	 * Hourly price
	 */
	@TableField("hourly_price")
	private Integer hourlyPrice;

	/**
	 * Use duration
	 */
	@TableField("use_duration")
	private Integer useDuration;

	/**
	 * Total price
	 */
	@TableField("total_price")
	private String totalPrice;

	/**
	 * Use time
	 */
	@TableField("use_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date useTime;

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

	/**
	 * Payment status
	 */
	@TableField("is_paid")
	private String isPaid;
	/**
	 * Return status
	 */
	@TableField("is_return")
	private String isReturn;

	@TableField("add_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addTime;

	public UseOrderEntity(Long id, String carName, String carType, String brand, String licensePlateNumber, Integer hourlyPrice, Integer useDuration, String totalPrice, Date useTime, String username, String realName, String mobilePhone, String isReviewed, String reviewResponse, String isPaid, String isReturn, Date addTime) {
		this.id = id;
		this.carName = carName;
		this.carType = carType;
		this.brand = brand;
		this.licensePlateNumber = licensePlateNumber;
		this.hourlyPrice = hourlyPrice;
		this.useDuration = useDuration;
		this.totalPrice = totalPrice;
		this.useTime = useTime;
		this.username = username;
		this.realName = realName;
		this.mobilePhone = mobilePhone;
		this.isReviewed = isReviewed;
		this.reviewResponse = reviewResponse;
		this.isPaid = isPaid;
		this.isReturn = isReturn;
		this.addTime = addTime;
	}

	public UseOrderEntity() {
	}

	@Override
	public String toString() {
		return "UseOrderEntity{" +
				"id=" + id +
				", carName='" + carName + '\'' +
				", carType='" + carType + '\'' +
				", brand='" + brand + '\'' +
				", licensePlateNumber='" + licensePlateNumber + '\'' +
				", hourlyPrice=" + hourlyPrice +
				", useDuration=" + useDuration +
				", totalPrice='" + totalPrice + '\'' +
				", useTime=" + useTime +
				", username='" + username + '\'' +
				", realName='" + realName + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", isReviewed='" + isReviewed + '\'' +
				", reviewResponse='" + reviewResponse + '\'' +
				", isPaid='" + isPaid + '\'' +
				", isReturn='" + isReturn + '\'' +
				", addTime=" + addTime +
				'}';
	}

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

	public Integer getHourlyPrice() {
		return hourlyPrice;
	}

	public void setHourlyPrice(Integer hourlyPrice) {
		this.hourlyPrice = hourlyPrice;
	}

	public Integer getUseDuration() {
		return useDuration;
	}

	public void setUseDuration(Integer useDuration) {
		this.useDuration = useDuration;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
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

	public String getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}

	public String getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
