package com.rabbiter.vlm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Car Storage Entity
 * DB entity for CRUD ops
 */
@TableName("car_storage")
public class CarStorageEntity<T> implements Serializable {
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
	 * Recovery location
	 */
	@TableField("recovery_location")
	private String recoveryLocation;

	/**
	 * Recovery time
	 */
	@TableField("recovery_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date recoveryTime;

	@TableField("add_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addTime;

	public CarStorageEntity() {
	}

	@Override
	public String toString() {
		return "CarStorageEntity{" +
				"id=" + id +
				", carName='" + carName + '\'' +
				", carType='" + carType + '\'' +
				", brand='" + brand + '\'' +
				", licensePlateNumber='" + licensePlateNumber + '\'' +
				", recoveryLocation='" + recoveryLocation + '\'' +
				", recoveryTime=" + recoveryTime +
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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public CarStorageEntity(Long id, String carName, String carType, String brand, String licensePlateNumber, String recoveryLocation, Date recoveryTime, Date addTime) {
		this.id = id;
		this.carName = carName;
		this.carType = carType;
		this.brand = brand;
		this.licensePlateNumber = licensePlateNumber;
		this.recoveryLocation = recoveryLocation;
		this.recoveryTime = recoveryTime;
		this.addTime = addTime;
	}
}
