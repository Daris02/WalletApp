package com.wallet.app.model;

import java.sql.Timestamp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Transfert {
    private String id;
    private Double amount;
    private Timestamp dateTime;
    private String debtorId;
    private String creditorId;

    public Transfert(String debtorId, String creditorId, Double amount) {
        this.debtorId = debtorId;
        this.creditorId = creditorId;
        this.amount = amount;
    }
}
