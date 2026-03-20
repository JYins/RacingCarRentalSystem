package com.rabbiter.vlm.entity.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * Car Storage VO
 * Helper class for API response (remove unnecessary fields)
 */
public class CarStorageVO implements Serializable {
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
	 * Recovery location
	 */

	private String recoveryLocation;

	/**
	 * Recovery time
	 */

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date recoveryTime;

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

	public String getRecoveryLocation() {
		return recoveryLocation;
	}

	public void setRecoveryLocation(String recoveryLocation) {
		this.recoveryLocation = recoveryLocation;
	}

	public Date getRecoveryTime() {
		return recoveryTime;
	}

	public void setRecoveryTime(Date recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
}
