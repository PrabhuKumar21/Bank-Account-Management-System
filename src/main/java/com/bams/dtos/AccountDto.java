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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private AccountType accountType;

}
