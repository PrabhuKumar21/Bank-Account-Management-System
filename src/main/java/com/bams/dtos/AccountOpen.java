package com.bams.dtos;

import java.math.BigDecimal;

import com.bams.entity.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Data
public class AccountOpen {

	 @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private AccountType accountType;
	 
	 private BigDecimal balance;
	

}
