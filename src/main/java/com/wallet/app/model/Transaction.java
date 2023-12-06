package com.wallet.app.model;

import java.sql.Timestamp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    private String id;
    private String label;
    private Double amount;
    private String type;
    private Timestamp dateTime;
    private String accountId;
    
    public Transaction(String label, Double amount, String type, Timestamp dateTime, String accountId) {
        this.label = label;
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
        this.accountId = accountId;
    }
}
