package com.bams.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Transactions")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transaction_id;

    @ManyToOne
    @JoinColumn(name="account_from_id", referencedColumnName = "account_id",nullable = true)
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name="account_to_id", referencedColumnName = "account_id",nullable = true)
    private Account receiverAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name="transactionType")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;


    private LocalDateTime transaction_time;

    @PrePersist
    public void prePersist(){
        this.transaction_time=LocalDateTime.now();
    }



}
