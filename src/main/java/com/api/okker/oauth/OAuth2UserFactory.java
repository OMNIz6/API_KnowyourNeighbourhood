package com.api.okker.oauth;

import java.util.Map;

import com.api.okker.dao.AuthProvider;
import com.api.okker.exception.OAuthAuthenticationException;

public class OAuth2UserFactory {
	public static OAuth2Users getInstance(String registerId, Map<String, Object> attributes) throws OAuthAuthenticationException {
		if(registerId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
			return new FacebookOAuth2User(attributes);
		}
		else {
			throw new OAuthAuthenticationException("Login with" + registerId + "is not supported yet!!!");
		}
	}
}
