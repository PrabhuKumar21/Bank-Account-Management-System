package com.bams.controller;

import com.bams.dtos.AccountDto;
import com.bams.dtos.TransactionDto;
import com.bams.entity.Transaction;
import com.bams.exception.InsufficientBalanceException;
import com.bams.service.TransactionService;
import com.fasterxml.jackson.core.util.RecyclerPool;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bank/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit/{accountId}")
    public ResponseEntity<String> moneyDeposit(@PathVariable Long accountId, @RequestBody TransactionDto transactionDto) throws AccountNotFoundException {
        transactionService.depositMoney(accountId, transactionDto);


        return ResponseEntity.ok("Amount deposited successfully");
    }

    @PostMapping("/withdraw/{accountId}")
    public ResponseEntity<String> moneyWithdraw(@PathVariable Long accountId, @RequestBody TransactionDto transactionDto) throws InsufficientBalanceException, AccountNotFoundException {
        transactionService.withdrawMoney(accountId,transactionDto);
        return ResponseEntity.ok("Money withdraw completed successfully");
    }

    @PostMapping("/transfer/{senderId}/{receiverId}")
    public ResponseEntity<String> moneyTransfer(@PathVariable Long senderId, @PathVariable Long receiverId, @RequestBody TransactionDto transactionDto) throws InsufficientBalanceException, AccountNotFoundException {

        transactionService.transferMoney(senderId, receiverId,transactionDto);
        return ResponseEntity.ok("Money transferred from one account to another successfully");
    }


    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable Long accountId) throws AccountNotFoundException {
        List<Transaction> transactionList = transactionService.allTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactionList);
    }


}
