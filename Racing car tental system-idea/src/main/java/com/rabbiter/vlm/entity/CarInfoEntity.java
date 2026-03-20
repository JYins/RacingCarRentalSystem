package com.rabbiter.vlm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Car Info Entity
 * DB entity for CRUD ops
 */
@TableName("car_info")
public class CarInfoEntity<T> implements Serializable {
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
	 * License plate number
	 */
	@TableField("license_plate_number")
	private String licensePlateNumber;

	/**
	 * Additional info
	 */
	@TableField("content")
	private String content;


	@TableField("add_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addTime;

	public CarInfoEntity(Long id, String carName, String carType, String brand, String bodyColor, String seatingCapacity, String licensePlateNumber, String content, Date addTime) {
		this.id = id;
		this.carName = carName;
		this.carType = carType;
		this.brand = brand;
		this.bodyColor = bodyColor;
		this.seatingCapacity = seatingCapacity;
		this.licensePlateNumber = licensePlateNumber;
		this.content = content;
		this.addTime = addTime;
	}

	public CarInfoEntity() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
