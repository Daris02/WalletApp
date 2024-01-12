package com.wallet.app.model;

import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CurrencyValue {
    private Integer id;
    private Integer currencySource;
    private Integer currencyDestination;
    private Double amount;
    private LocalDateTime dateEffect;

    public CurrencyValue(Integer currencySource, Integer currencyDestination, Double amount) {
        this.currencySource = currencySource;
        this.currencyDestination = currencyDestination;
        this.amount = amount;
    }
}