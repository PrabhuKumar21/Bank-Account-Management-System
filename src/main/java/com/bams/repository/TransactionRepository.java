package com.bams.repository;

import com.bams.entity.Account;
import com.bams.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findBySenderAccountOrReceiverAccount(Account sender,Account receiver);
}
