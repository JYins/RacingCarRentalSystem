package com.rabbiter.vlm.entity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * Use Order VO
 * Helper class for API response (remove unnecessary fields)
 */
public class UseOrderVO implements Serializable {
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
	 * Hourly price
	 */

	private Integer hourlyPrice;

	/**
	 * Use duration
	 */

	private Integer useDuration;

	/**
	 * Total price
	 */

	private String totalPrice;

	/**
	 * Use time
	 */

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date useTime;

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

	/**
	 * Payment status
	 */

	private String isPaid;
	/**
	 * Return status
	 */
	private String isReturn;

	@Override
	public String toString() {
		return "UseOrderVO{" +
				"carType='" + carType + '\'' +
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

	public UseOrderVO(String carType, String brand, String licensePlateNumber, Integer hourlyPrice, Integer useDuration, String totalPrice, Date useTime, String username, String realName, String mobilePhone, String isReviewed, String reviewResponse, String isPaid, String isReturn) {
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
	}

	public UseOrderVO() {
	}
}
