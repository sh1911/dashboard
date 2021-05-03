package com.letsaspire.dashboard.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "user_credentials")
public class UserCredentials {
	
	@Id
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID id = UUID.randomUUID();

	@Column(name = "phoneNumber")
	private Long phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "role",nullable = false)
	private String role;
	

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserCredentials [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password="
				+ password + ", role=" + role + "]";
	}

		


}
