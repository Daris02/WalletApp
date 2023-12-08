package com.wallet.app.model;

import java.sql.Timestamp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Transfert {
    private String id;
    private String accountId1;
    private String accountId2;
    private Double amount;
    private Timestamp dateTime;
}
