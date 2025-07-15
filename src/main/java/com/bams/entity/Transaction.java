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
@Table
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transaction_id;

    @ManyToOne
    @JoinColumn(name="account_from_id", referencedColumnName = "account_id")
    private Account account_from;

    @ManyToOne
    @JoinColumn(name="account_to_id", referencedColumnName = "account_id")
    private Account account_to;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transaction_type;


    private LocalDateTime transaction_time;


}
