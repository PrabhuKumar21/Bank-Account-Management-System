package com.bams.service;

import com.bams.dtos.TransactionDto;
import com.bams.entity.Account;
import com.bams.entity.Transaction;
import com.bams.entity.TransactionType;
import com.bams.repository.AccountRepository;
import com.bams.repository.TransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {

   private final TransactionRepository transactionRepository;

   private final ModelMapper modelMapper= new ModelMapper();

   private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    public  TransactionDto DepositMoney(Long accountId, TransactionDto transactionDto) throws AccountNotFoundException {
        Optional<Account> account= accountRepository.findById(Long.valueOf(accountId));
        if(account.isEmpty()){
            throw new AccountNotFoundException("Account is not available");

        }
        Account accountDetails=account.get();
        BigDecimal depositAmount=transactionDto.getAmount();
        accountDetails.setBalance(accountDetails.getBalance().add(depositAmount));

        accountRepository.save(accountDetails);

        Transaction transaction= new Transaction();
        transaction.setReceiverAccount(accountDetails);
        transaction.setAmount(depositAmount);
        transaction.setTransaction_type(TransactionType.DEPOSIT);
        transactionRepository.save(transaction);

        return modelMapper.map(transaction,TransactionDto.class);






    }




}
