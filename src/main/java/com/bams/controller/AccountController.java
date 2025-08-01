package com.bams.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bams.dtos.AccountDto;
import com.bams.dtos.AccountOpen;
import com.bams.exception.ErrorResponse;
import com.bams.exception.ResourceNotFoundException;
import com.bams.service.AccountService;

@RestController
@RequestMapping("/bank/account")
public class AccountController {
	
	
 AccountService accountService;
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService=accountService;
	}
	
	
	
//	POST /bank/account/create/{userId} → Create account for a user.
	
	@PostMapping("/create/{userId}")
	public ResponseEntity<AccountDto> createAccount(@PathVariable long userId ,@RequestBody AccountOpen accountopen ){
		AccountDto account = accountService.createAccount(userId,accountopen);
		
		
		return new ResponseEntity<>(account,HttpStatus.CREATED) ;
		
		
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
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException ex) {

		ErrorResponse response = new ErrorResponse(
				LocalDateTime.now(),
				HttpStatus.NOT_FOUND.value(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				ex.getMessage()

		);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

	}
	
}
