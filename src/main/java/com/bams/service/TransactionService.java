package com.bams.service;

import com.bams.dtos.TransactionDto;
import com.bams.entity.Account;
import com.bams.entity.Transaction;
import com.bams.entity.TransactionType;
import com.bams.exception.InsufficientBalanceException;
import com.bams.repository.AccountRepository;
import com.bams.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.text.html.Option;
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


    public  TransactionDto depositMoney(Long accountId, TransactionDto transactionDto) throws AccountNotFoundException {
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

    public TransactionDto withdrawMoney(Long accountId, TransactionDto transactionDto) throws AccountNotFoundException, InsufficientBalanceException {
        Optional<Account> accountOptional= accountRepository.findById(accountId);

        if(accountOptional.isEmpty())
        {
            throw new AccountNotFoundException("No account found with this account id ");
        }
        BigDecimal withdrawAmount=transactionDto.getAmount();

        Account accountDetails=accountOptional.get();
        BigDecimal Current_balance= accountDetails.getBalance();

        if(Current_balance.compareTo(withdrawAmount)<0){
           throw new InsufficientBalanceException("Insufficient Balance");

        }

        BigDecimal updatedBalance= Current_balance.subtract(withdrawAmount);
        accountDetails.setBalance(updatedBalance);
        accountRepository.save(accountDetails);

        Transaction transaction = new Transaction();
        transaction.setSenderAccount(accountDetails);
        transaction.setTransaction_type(TransactionType.WITHDRAW);
        transaction.setAmount(withdrawAmount);

        transactionRepository.save(transaction);

        return modelMapper.map(transaction,TransactionDto.class);
    }

    @Transactional
    public TransactionDto transferMoney(Long senderId, Long receiverId, TransactionDto transactionDto) throws AccountNotFoundException, InsufficientBalanceException {

        Account senderAccount = accountRepository.findById(senderId)
                .orElseThrow(()-> new AccountNotFoundException("Sender account not found with this id"));

        Account receiverAccount = accountRepository.findById(receiverId)
                .orElseThrow(()-> new AccountNotFoundException("receiver account not found with this id"));


        BigDecimal amount= transactionDto.getAmount();

        BigDecimal senderAccountBalance = senderAccount.getBalance();

        if(senderAccountBalance.compareTo(amount)<0){
            throw new InsufficientBalanceException("Insufficient Balance in the sender account");
        }
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));

        //Optional but fine
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);

        Transaction transaction= new Transaction();
        transaction.setTransaction_type(TransactionType.TRANSFER);
        transaction.setAmount(amount);
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        transactionRepository.save(transaction);
        return modelMapper.map(transaction,TransactionDto.class);

    }






}
