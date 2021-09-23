package com.api.okker.payload;

//for the responding after the login authentication 
//after successful authentication this class response a token
public class LoginResponse {
	
	private String accessToken;
	private String tokenType = "Bearer";
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public LoginResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
