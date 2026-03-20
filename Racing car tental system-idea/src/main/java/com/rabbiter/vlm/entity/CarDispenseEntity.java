package com.rabbiter.vlm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Car Dispense Entity
 * DB entity for CRUD ops
 */
@TableName("car_dispense")
public class CarDispenseEntity<T> implements Serializable {
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
	 * Body color
	 */
	@TableField("body_color")
	private String bodyColor;

	/**
	 * Seating capacity
	 */
	@TableField("seating_capacity")
	private String seatingCapacity;

	/**
	 * Hourly price
	 */
	@TableField("hourly_price")
	private Integer hourlyPrice;

	/**
	 * Dispense region
	 */
	@TableField("dispense_region")
	private String dispenseRegion;

	/**
	 * Dispense location
	 */
	@TableField("dispense_location")
	private String dispenseLocation;

	/**
	 * Dispense time
	 */
	@TableField("dispense_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date dispenseTime;

	/**
	 * Car condition/status
	 */
	@TableField("car_condition")
	private String carCondition;

	@TableField("add_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addTime;

	public CarDispenseEntity(Long id, String carName, String carType, String brand, String licensePlateNumber, String bodyColor, String seatingCapacity, Integer hourlyPrice, String dispenseRegion, String dispenseLocation, Date dispenseTime, String carCondition, Date addTime) {
		this.id = id;
		this.carName = carName;
		this.carType = carType;
		this.brand = brand;
		this.licensePlateNumber = licensePlateNumber;
		this.bodyColor = bodyColor;
		this.seatingCapacity = seatingCapacity;
		this.hourlyPrice = hourlyPrice;
		this.dispenseRegion = dispenseRegion;
		this.dispenseLocation = dispenseLocation;
		this.dispenseTime = dispenseTime;
		this.carCondition = carCondition;
		this.addTime = addTime;
	}

	public CarDispenseEntity() {
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
