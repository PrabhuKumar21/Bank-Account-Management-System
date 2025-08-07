package com.bams.controller;

import com.bams.dtos.AccountDto;
import com.bams.dtos.TransactionDto;
import com.bams.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/bank/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<String> moneyDeposit(@PathVariable Long accountId, @RequestBody TransactionDto transactionDto) throws AccountNotFoundException {
        transactionService.DepositMoney(accountId, transactionDto);


        return ResponseEntity.ok("Amount deposited successfully");
    }
}
