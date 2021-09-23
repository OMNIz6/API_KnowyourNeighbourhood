package com.api.okker.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.okker.dao.Account;
import com.api.okker.dao.AuthProvider;
import com.api.okker.exception.BadRequestException;
import com.api.okker.payload.Login;
import com.api.okker.payload.LoginResponse;
import com.api.okker.payload.Register;
import com.api.okker.payload.RegisterResponse;
import com.api.okker.repository.AccountRepo;

@RestController
@RequestMapping("/kyn")
public class AuthController {
	@Autowired
	private AccountRepo accRepo;
	
	@Autowired
	private com.api.okker.jwt.TokenProvider tokenProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value="/register")
	public ResponseEntity<?> registerUser(@RequestBody Register register){
		
		if(accRepo.existsByEmail(register.getEmail())) {
			throw new BadRequestException("Email already registered");
		}
		
		Account acc = new Account();
		acc.setUserName(register.getUserName());
		acc.setEmail(register.getEmail());
		acc.setPassword(register.getPassword());
		acc.setProvider(AuthProvider.local);
		
		acc.setPassword(passwordEncoder.encode(acc.getPassword()));
		
		acc = accRepo.save(acc);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/user/me")
				.buildAndExpand(acc.getAccId()).toUri();
		return ResponseEntity.created(location)
				.body(new RegisterResponse(true, "User has successfully registered!!!"));
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {

		//Checking Authentication 
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getEmail(),
                		login.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //If authorized user, create token
        String token = tokenProvider.createToken(authentication);
        
        //Return to LoginResponse Payload
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
