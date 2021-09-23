package com.api.okker.payload;

import com.sun.istack.NotNull;

//For registration to the application
public class Register {
	@NotNull
	private String userName;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
}
