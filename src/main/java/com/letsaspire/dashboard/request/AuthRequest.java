package com.letsaspire.dashboard.request;



public class AuthRequest {

	String email;
	Long phonenumber;
	String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AuthRequest [email=" + email + ", phonenumber=" + phonenumber + ", password=" + password + "]";
	}
	
	

	
}
