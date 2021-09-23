package com.api.okker.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


//For local login authentication
public class Login {
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

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
