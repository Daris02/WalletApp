package com.wallet.app.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class SpendAmount {
    public String categoryName;
    public Double totalAmount;
}
