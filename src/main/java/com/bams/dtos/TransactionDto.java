package com.bams.dtos;


import com.bams.entity.Account;
import com.bams.entity.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

//    private Long senderAccount;
//
//    private Long receiverAccount;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private TransactionType transaction_type;
}
