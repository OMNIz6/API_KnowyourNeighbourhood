package com.api.okker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.okker.dao.Account;
import com.api.okker.exception.ResourceNotFoundException;
import com.api.okker.repository.AccountRepo;
import com.api.okker.service.UserPrincipal;
@RestController
@RequestMapping(value="/kyn")
public class UserController {
	@Autowired
    private AccountRepo accRepo;
	
	@GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
	public Account getUser(@CurrentUser UserPrincipal userPrincipal) {
		return accRepo.findById(userPrincipal.getUserId())
				 .orElseThrow(() -> new ResourceNotFoundException("Users", "userId", userPrincipal.getUserId()));
    }
}
