package com.bank.webAppliaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Seq_id")
    @SequenceGenerator(name = "Seq_id",
            initialValue = 10021345,allocationSize = 7)
    @Column(name="cus_acc_num")
    @NotNull
    private long id;

    @NonNull
    @Column(name="cus_Name")
    private String name;

    @NonNull
    @Column(name="cus_Balance")
    private BigDecimal balance;

    @Generated
    @Column(name="acc_creation",nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

    @Generated
    @Column(name="last_Modified",nullable = false)
    @UpdateTimestamp
    private Timestamp modifiedOn;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Transaction> transactions;

}
