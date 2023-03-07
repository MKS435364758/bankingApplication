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

    @Column(name="transaction_Type")//,columnDefinition = "transaction")
    private String transactionType;

    @Column(name="description")
    private String description;
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id",referencedColumnName = "cus_acc_num")
    private Customer customer;

}
