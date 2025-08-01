package com.bams.dtos;

import com.bams.entity.AccountType;
import com.bams.entity.Status;
import com.bams.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AccountDto {

	Long userId;

	private String accountNumber;

	@Enumerated(EnumType.STRING)
	private Status status;

	private BigDecimal balance;

	@Enumerated(EnumType.STRING)

	private AccountType accountType;

}
