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
    
    public Account(String name, Double balance, Timestamp creationDate, String type) {
        this.name = name;
        this.balance = balance;
        this.creationDate = creationDate;
        this.type = type;
    }
    
    public Account(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
