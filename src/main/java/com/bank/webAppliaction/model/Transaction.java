package com.bank.webAppliaction.model;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="transaction_id")
    private Long id;

    @NotNull
    @Column(name="transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name="account_balance")
    private BigDecimal remainBalance;

    @CreationTimestamp
    @Column(name = "transaction_made_on")
    private Timestamp made_on;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId",referencedColumnName = "cus_acc_num")
    private Customer customer;

}
