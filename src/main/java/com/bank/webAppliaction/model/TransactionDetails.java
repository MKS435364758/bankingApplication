package com.bank.webAppliaction.model;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TransactionDetails extends Details {

    @NonNull
    private Long id;

    @NonNull
    private BigDecimal amount;

    @NonNull
    private String type;

    private String description;
}
