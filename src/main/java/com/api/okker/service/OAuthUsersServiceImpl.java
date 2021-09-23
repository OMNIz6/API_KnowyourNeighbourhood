package com.api.okker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.api.okker.dao.Account;
import com.api.okker.dao.AuthProvider;
import com.api.okker.exception.OAuthAuthenticationException;
import com.api.okker.oauth.OAuth2UserFactory;
import com.api.okker.oauth.OAuth2Users;
import com.api.okker.repository.AccountRepo;


@Service
public class OAuthUsersServiceImpl extends DefaultOAuth2UserService {
	@Autowired
	AccountRepo accRepo;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		try {
			return processOAuth2User(userRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		}catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
		
	}
	
	private OAuth2User processOAuth2User (OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws OAuthAuthenticationException {
		OAuth2Users oAuth2Users = OAuth2UserFactory.getInstance(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if(StringUtils.isEmpty(oAuth2Users.getEmail())) {
			throw new OAuthAuthenticationException("Email not Found.");
		}
		
		Optional<Account> accOptional = accRepo.findByEmail(oAuth2Users.getEmail());
		Account acc;
		System.out.println("in the login");
		if (accOptional.isPresent()) {
			acc = accOptional.get();
			if(!acc.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				 throw new OAuthAuthenticationException("Looks like you're signed up with " +
	                        acc.getProvider() + " account. Please use your " + acc.getProvider() +
	                        " account to login.");
			}
			acc = updateExistingUser(acc, oAuth2Users);
		}else {
			acc = registerNewUser(oAuth2UserRequest, oAuth2Users);
		}
		
		return UserPrincipal.createUser(acc, oAuth2User.getAttributes());
	}
	
	private Account registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2Users oAuth2Users) {
		Account acc = new Account();
		acc.setUserName(oAuth2Users.getName());
		acc.setEmail(oAuth2Users.getEmail());
		acc.setImageUrl(oAuth2Users.getImageUrl());
		acc.setProviderId(oAuth2Users.getId());
		acc.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		return accRepo.save(acc);
	}
	
	private Account updateExistingUser(Account existingAcc, OAuth2Users oauth2Users) {
		existingAcc.setUserName(oauth2Users.getName());
		existingAcc.setImageUrl(oauth2Users.getImageUrl());
        return accRepo.save(existingAcc);
	}
}
