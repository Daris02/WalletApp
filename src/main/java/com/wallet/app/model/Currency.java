package com.wallet.app.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Currency {
    private String id;
    private String name;
    private String code;
    
    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
