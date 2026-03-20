package com.rabbiter.vlm.entity.vo;

import java.io.Serializable;


/**
 * Car Info VO
 * Helper class for API response (remove unnecessary fields)
 */
public class CarInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * Additional info
	 */
	private String content;
	/**
	 * Car type
	 */

	private String carType;

	/**
	 * Brand
	 */

	private String brand;

	/**
	 * Body color
	 */

	private String bodyColor;

	/**
	 * Seating capacity
	 */

	private String seatingCapacity;

	/**
	 * License plate number
	 */

	private String licensePlateNumber;

	@Override
	public String toString() {
		return "CarInfoVO{" +
				"content='" + content + '\'' +
				", carType='" + carType + '\'' +
				", brand='" + brand + '\'' +
				", bodyColor='" + bodyColor + '\'' +
				", seatingCapacity='" + seatingCapacity + '\'' +
				", licensePlateNumber='" + licensePlateNumber + '\'' +
				'}';
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public CarInfoVO(String content, String carType, String brand, String bodyColor, String seatingCapacity, String licensePlateNumber) {
		this.content = content;
		this.carType = carType;
		this.brand = brand;
		this.bodyColor = bodyColor;
		this.seatingCapacity = seatingCapacity;
		this.licensePlateNumber = licensePlateNumber;
	}

	public CarInfoVO() {
	}
}
