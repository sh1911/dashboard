package com.letsaspire.dashboard.domain;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtCreation;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
@Entity
@Table(name = "user_login_details")
public class UserLoginDetails {
	
	@Id
	@GeneratedValue
	private Long id;
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID userId;
	
	private String status;
	
	private String deviceName;
	
	private String deviceOs;
	
	private String deviceClient;
	
	@CreatedDate
	@Column(name ="loggedin_date_time",updatable = true)
	private String  logInDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceOs() {
		return deviceOs;
	}

	public void setDeviceOs(String deviceOs) {
		this.deviceOs = deviceOs;
	}

	public String getDeviceClient() {
		return deviceClient;
	}

	public void setDeviceClient(String deviceClient) {
		this.deviceClient = deviceClient;
	}

	public String getLogInDateTime() {
		return logInDateTime;
	}

	public void setLogInDateTime(String logInDateTime) {
		this.logInDateTime = logInDateTime;
	}
	
	

}
