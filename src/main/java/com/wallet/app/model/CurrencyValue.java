package com.wallet.app.model;

import java.time.LocalDate;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurrencyValue {
    private Integer id;
    private String currencySource;
    private String currencyDestination;
    private Double amount;
    private LocalDate dateEffect;
}