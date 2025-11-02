package com.bams.repository;

import com.bams.entity.Account;
import com.bams.entity.AccountType;
import com.bams.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

	public boolean existsByAccountNumber(String accNo);
	
	public boolean existsByUserAndAccountType(User user ,AccountType accountType);

	List<Account> findByUser(User userDetails);
}
