package com.api.okker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.okker.dao.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{
	//to check if the Email already exist in the database or not
	Boolean existsByEmail(String email);
	
	//to find the account from the database using email
	Optional<Account> findByEmail(String email);
}
