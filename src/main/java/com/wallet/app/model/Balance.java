package com.wallet.app.model;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Balance {
    private String id;
    private Double value;
    private Timestamp lastUpdate;
    private String accountId;
}
