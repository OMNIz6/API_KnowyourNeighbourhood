package com.api.okker.configuration;

//Configuration class to use in app.yml for configuration 
public class Auth {
	
	//this will store the tokenSecret from yml file
	private String tokenSecret;
	
	//this will store the tokenExpirationMsec from yml file
    private long tokenExpirationMsec;

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public long getTokenExpirationMsec() {
        return tokenExpirationMsec;
    }

    public void setTokenExpirationMsec(long tokenExpirationMsec) {
        this.tokenExpirationMsec = tokenExpirationMsec;
    }
}
