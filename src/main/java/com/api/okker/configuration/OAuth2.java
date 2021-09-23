package com.api.okker.configuration;

import java.util.ArrayList;
import java.util.List;

//Configuration class to use in app.yml for configuration 
public class OAuth2 {
	//to store the redirect URL from yml file
	private List<String> authorizedRedirectUris = new ArrayList<>();

    public List<String> getAuthorizedRedirectUris() {
        return authorizedRedirectUris;
    }

    public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
        this.authorizedRedirectUris = authorizedRedirectUris;
        return this;
    }
}
