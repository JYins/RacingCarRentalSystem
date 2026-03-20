package com.rabbiter.vlm.entity.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;


/**
 * User VO
 * Helper class for API response (remove unnecessary fields)
 */
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * Password
	 */

	private String password;

	/**
	 * Real name
	 */

	private String realName;

	/**
	 * Gender
	 */

	private String gender;

	/**
	 * Birthday
	 */

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date birthday;

	/**
	 * ID number
	 */

	private String identificationNumber;

	/**
	 * Mobile phone
	 */

	private String mobilePhone;

	/**
	 * Photo
	 */
	private String photo;

	private String isReviewed;

	private String review_response;

	@Override
	public String toString() {
		return "UserVO{" +
				"password='" + password + '\'' +
				", realName='" + realName + '\'' +
				", gender='" + gender + '\'' +
				", birthday=" + birthday +
				", identificationNumber='" + identificationNumber + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", photo='" + photo + '\'' +
				", isReviewed='" + isReviewed + '\'' +
				", review_response='" + review_response + '\'' +
				'}';
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIsReviewed() {
		return isReviewed;
	}

	public void setIsReviewed(String isReviewed) {
		this.isReviewed = isReviewed;
	}

	public String getReview_response() {
		return review_response;
	}

	public void setReview_response(String review_response) {
		this.review_response = review_response;
	}

	public UserVO(String password, String realName, String gender, Date birthday, String identificationNumber, String mobilePhone, String photo, String isReviewed, String review_response) {
		this.password = password;
		this.realName = realName;
		this.gender = gender;
		this.birthday = birthday;
		this.identificationNumber = identificationNumber;
		this.mobilePhone = mobilePhone;
		this.photo = photo;
		this.isReviewed = isReviewed;
		this.review_response = review_response;
	}

	public UserVO() {
	}
}
