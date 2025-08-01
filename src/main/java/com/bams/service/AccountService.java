package com.bams.service;

import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bams.dtos.AccountDto;
import com.bams.dtos.AccountOpen;
import com.bams.entity.Account;
import com.bams.entity.Status;
import com.bams.entity.User;
import com.bams.exception.ResourceNotFoundException;
import com.bams.repository.AccountRepository;
import com.bams.repository.UserRepository;

@Service
public class AccountService {
	
	UserRepository userRepository;
	
	AccountDto accountDto;
	
	AccountRepository accountRepository;
	
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	public AccountService(UserRepository userRepository,AccountDto accountDto,AccountRepository accountRepository) {
		this.userRepository=userRepository;
		this.accountDto= accountDto;
		this.accountRepository=accountRepository;
	}

	  private String generateUniqueAccountNumber() {
		  String accNum;
		    do {
		        accNum = "ACCT" + (1000000000 + new Random().nextInt(900000000));
		    } while (accountRepository.existsByAccountNumber(accNum));
		    return accNum;
	    }

	
	public AccountDto createAccount(long userId, AccountOpen accountOpen) {
		
		Optional<User> user = userRepository.findById(userId);
		
		
		
		if(user.isPresent()){
			
			boolean existsByUserAndAccountType = accountRepository.existsByUserAndAccountType(user.get(), accountOpen.getAccountType());
			
			if(!existsByUserAndAccountType) {
			Account account = new Account();
			account.setAccountNumber(generateUniqueAccountNumber());
			account.setAccountType(accountOpen.getAccountType());
			account.setBalance(accountOpen.getBalance());
			account.setUser(user.get());
			account.setStatus(Status.ACTIVE);
			
			accountRepository.save(account);
			
			accountDto.setUserId(user.get().getUser_id());
			accountDto.setAccountNumber(account.getAccountNumber());
			accountDto.setAccountType(account.getAccountType());
			accountDto.setBalance(account.getBalance());
			accountDto.setStatus(account.getStatus());
			return accountDto;
			}
			else {
				throw new ResourceNotFoundException(" Account already exists ");
			}
		
			
		}
		else {
			throw new ResourceNotFoundException("User Not Found to Open Account ");
		}
	
		
		
		
	}

	

}
