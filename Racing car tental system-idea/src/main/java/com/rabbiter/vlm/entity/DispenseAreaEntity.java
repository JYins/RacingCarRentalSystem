package com.rabbiter.vlm.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Dispense Area Entity
 * DB entity for CRUD ops
 */
@TableName("dispense_area")
public class DispenseAreaEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Primary key
	 */
	@TableId
	private Long id;
	/**
	 * Area name
	 */

	@TableField("area")
	private String area;

	@TableField("add_time")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addTime;

	@Override
	public String toString() {
		return "DispenseAreaEntity{" +
				"id=" + id +
				", area='" + area + '\'' +
				", addTime=" + addTime +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public DispenseAreaEntity(Long id, String area, Date addTime) {
		this.id = id;
		this.area = area;
		this.addTime = addTime;
	}

	public DispenseAreaEntity() {
	}
}
