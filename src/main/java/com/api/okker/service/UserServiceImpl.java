package com.api.okker.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.okker.dao.Account;
import com.api.okker.exception.ResourceNotFoundException;
import com.api.okker.repository.AccountRepo;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	AccountRepo accRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account acc = accRepo.findByEmail(email)
		.orElseThrow( () -> new UsernameNotFoundException("This email cannot be found" + email));
		
		return UserPrincipal.createUser(acc);
	}
	
	public UserDetails loadUserById(long userId){
		Account acc = accRepo.findById(userId)
		.orElseThrow( () -> new ResourceNotFoundException("Users", "userId", userId));
		
		return UserPrincipal.createUser(acc);
	}
}
