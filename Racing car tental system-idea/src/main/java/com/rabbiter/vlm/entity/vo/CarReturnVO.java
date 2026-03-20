package com.rabbiter.vlm.entity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * Car Return VO
 * Helper class for API response (remove unnecessary fields)
 */
public class CarReturnVO implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * Car type
	 */

	private String carType;

	/**
	 * Brand
	 */

	private String brand;

	/**
	 * License plate number
	 */

	private String licensePlateNumber;

	/**
	 * Return time
	 */

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date returnTime;

	/**
	 * Return content
	 */

	private String returnContent;

	/**
	 * Username
	 */

	private String username;

	/**
	 * Real name
	 */

	private String realName;

	/**
	 * Mobile phone
	 */

	private String mobilePhone;

	/**
	 * Review status
	 */

	private String isReviewed;

	/**
	 * Review response
	 */

	private String reviewResponse;

	@Override
	public String toString() {
		return "CarReturnVO{" +
				"carType='" + carType + '\'' +
				", brand='" + brand + '\'' +
				", licensePlateNumber='" + licensePlateNumber + '\'' +
				", returnTime=" + returnTime +
				", returnContent='" + returnContent + '\'' +
				", username='" + username + '\'' +
				", realName='" + realName + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", isReviewed='" + isReviewed + '\'' +
				", reviewResponse='" + reviewResponse + '\'' +
				'}';
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

	public CarReturnVO(String carType, String brand, String licensePlateNumber, Date returnTime, String returnContent, String username, String realName, String mobilePhone, String isReviewed, String reviewResponse) {
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
	}

	public CarReturnVO() {
	}
}
