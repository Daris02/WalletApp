package com.wallet.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Currency;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyRepository implements Crud<Currency> {

    @Override
    public Currency getById(String id) {
        return (Currency) AutoCrud.findById(id, "currency");
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> listCurrencies = new ArrayList<>();
        for (Object object : AutoCrud.findAll("currency")) {
            listCurrencies.add((Currency)object);
        }
        return listCurrencies;
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave) {
        List<Currency> saveAll = new ArrayList<>();
        for (Currency currency : toSave) {
            save(currency);
            saveAll.add(getById(currency.getId()));
        }
        return saveAll;
    }

    @Override
    public Currency save(Currency toSave) {
        AutoCrud.save(toSave);
        return getById(toSave.getId());
    }
    
}
