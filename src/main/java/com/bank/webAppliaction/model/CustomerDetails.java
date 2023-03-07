package com.bank.webAppliaction.model;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CustomerDetails extends Details {

    public final static String TYPE = "Debited";
    @NonNull
    private long id;

    private String name;
    private BigDecimal balance;



}
