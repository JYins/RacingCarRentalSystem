package com.rabbiter.vlm.entity.vo;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * Car Dispense VO
 * Helper class for API response (remove unnecessary fields)
 */
public class CarDispenseVO implements Serializable {
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
	 * Body color
	 */

	private String bodyColor;

	/**
	 * Seating capacity
	 */

	private String seatingCapacity;

	/**
	 * Hourly price
	 */

	private Integer hourlyPrice;

	/**
	 * Dispense region
	 */

	private String dispenseRegion;

	/**
	 * Dispense location
	 */

	private String dispenseLocation;

	/**
	 * Dispense time
	 */

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date dispenseTime;

	/**
	 * Car status
	 */

	private String carCondition;

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

	public String getBodyColor() {
		return bodyColor;
	}

	public void setBodyColor(String bodyColor) {
		this.bodyColor = bodyColor;
	}

	public String getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(String seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public Integer getHourlyPrice() {
		return hourlyPrice;
	}

	public void setHourlyPrice(Integer hourlyPrice) {
		this.hourlyPrice = hourlyPrice;
	}

	public String getDispenseRegion() {
		return dispenseRegion;
	}

	public void setDispenseRegion(String dispenseRegion) {
		this.dispenseRegion = dispenseRegion;
	}

	public String getDispenseLocation() {
		return dispenseLocation;
	}

	public void setDispenseLocation(String dispenseLocation) {
		this.dispenseLocation = dispenseLocation;
	}

	public Date getDispenseTime() {
		return dispenseTime;
	}

	public void setDispenseTime(Date dispenseTime) {
		this.dispenseTime = dispenseTime;
	}

	public String getCarCondition() {
		return carCondition;
	}

	public void setCarCondition(String carCondition) {
		this.carCondition = carCondition;
	}
}
