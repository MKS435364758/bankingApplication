package com.bank.webAppliaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="cus_acc_num",unique = true)
    @NotNull
    private long id;

    @NonNull
    @NotBlank(message = "Name can't be Blank or empty")
    @Size(min=4, message = "Minimum of 5 characters")
    @Column(name="cus_Name")
    private String name;

    @NonNull
    @NotNull
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
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}
