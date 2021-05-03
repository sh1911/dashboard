package com.letsaspire.dashboard.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id = UUID.randomUUID();

	@Column(name = "phoneNumber")
	private Long phoneNumber;

	@Column(name = "pannumber")
	private String panNumber;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "line_amount")
	private String line_amount;

	@Column(name = "isActive")
	private int isActive;

	@Column(name = "creationDate")
	private String creationDate;

	@Column(name = "modificationDate")
	private String modificationDate;

	@Column(name = "latitude")
	private double latitude;

	@Column(name = "longitude")
	private double longitude;

	@Column(name = "IsOTPReceived")
	private int IsOTPReceived;

	@Column(name = "userName")
	private String userName;

	@Column(name = "user_login_pin")
	private String userLoginPin;

	@Column(name = "user_skip_login")
	private int userSkipLogin;

	public String getUserLoginPin() {
		return userLoginPin;
	}

	public void setUserLoginPin(String userLoginPin) {
		this.userLoginPin = userLoginPin;
	}

	public int getUserSkipLogin() {
		return userSkipLogin;
	}

	public void setUserSkipLogin(int userSkipLogin) {
		this.userSkipLogin = userSkipLogin;
	}

	@Column(columnDefinition = "integer default 3")
	private int kycAllowedCount = 3;

	private int smsflowallowed;

	private int isTestUser;

	private Integer isWhatsAppConsent;

	public User() {
		super();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getLine_amount() {
		return line_amount;
	}

	public void setLine_amount(String line_amount) {
		this.line_amount = line_amount;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getIsOTPReceived() {
		return IsOTPReceived;
	}

	public void setIsOTPReceived(int isOTPReceived) {
		IsOTPReceived = isOTPReceived;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getKycAllowedCount() {
		return kycAllowedCount;
	}

	public void setKycAllowedCount(int kycAllowedCount) {
		this.kycAllowedCount = kycAllowedCount;
	}

	public int getSmsflowallowed() {
		return smsflowallowed;
	}

	public void setSmsflowallowed(int smsflowallowed) {
		this.smsflowallowed = smsflowallowed;
	}

	public int getIsTestUser() {
		return isTestUser;
	}

	public void setIsTestUser(int isTestUser) {
		this.isTestUser = isTestUser;
	}

	public Integer getIsWhatsAppConsent() {
		return isWhatsAppConsent;
	}

	public void setIsWhatsAppConsent(Integer isWhatsAppConsent) {
		this.isWhatsAppConsent = isWhatsAppConsent;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", phoneNumber=" + phoneNumber + ", panNumber=" + panNumber + ", dob=" + dob
				+ ", line_amount=" + line_amount + ", isActive=" + isActive + ", creationDate=" + creationDate
				+ ", modificationDate=" + modificationDate + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", IsOTPReceived=" + IsOTPReceived + ", userName=" + userName + ", userLoginPin=" + userLoginPin
				+ ", userSkipLogin=" + userSkipLogin + ", kycAllowedCount=" + kycAllowedCount + ", smsflowallowed="
				+ smsflowallowed + ", isTestUser=" + isTestUser + ", isWhatsAppConsent=" + isWhatsAppConsent + "]";
	}

}
