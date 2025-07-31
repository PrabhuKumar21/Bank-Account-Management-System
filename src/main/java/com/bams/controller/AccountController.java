package com.bams.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/account")
public class AccountController {
	
	
	
//	POST /bank/account/create/{userId} → Create account for a user.
	
	@PostMapping("/create/{userId}")
	public ResponseEntity<?> createAccount(@PathVariable("id") int id ){
		
		return null;
		
		
	}
	
	
	
	
//	GET /bank/account/{id} → Get account by ID.
//	GET /bank/account/all → Get all accounts.
//	PATCH /bank/account/{id}/status → Update account status (ACTIVE, INACTIVE).
//	DELETE /bank/account/{id} → Close account (if balance is zero).
//	GET /bank/account/{userId}/list → Get all accounts of a user.
//	POST /bank/account/{id}/deposit → Deposit money.
//	POST /bank/account/{id}/withdraw → Withdraw money.
//	POST /bank/account/transfer → Transfer money between accounts.
//	GET /bank/account/{id}/balance → Check account balance.
//	GET /bank/account/{id}/transactions → Get all transactions for an account.
	
}
