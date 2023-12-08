package com.wallet.app.model;

import java.sql.Timestamp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    private String id;
    private String name;
    private Double balance;
    private Timestamp creationDate;
    private String type;
    private String currencyId;
        
    public Account(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
