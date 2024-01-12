package com.wallet.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.CurrencyValue;

public class CurrencyValueRepository implements Crud<CurrencyValue> {

    @Override
    public CurrencyValue getById(String id) {
        return (CurrencyValue) AutoCrud.findById(id, "currency_value");
    }

    @Override
    public List<CurrencyValue> findAll() {
        List<CurrencyValue> listCurrenciesValues = new ArrayList<>();
        for (Object object : AutoCrud.findAll("currency_value")) {
            listCurrenciesValues.add((CurrencyValue)object);
        }
        return listCurrenciesValues;
    }

    @Override
    public List<CurrencyValue> saveAll(List<CurrencyValue> toSave) {
        List<CurrencyValue> saveAll = new ArrayList<>();
        for (CurrencyValue currencyValue : toSave) {
            save(currencyValue);
            saveAll.add(getById(currencyValue.getId().toString()));
        }
        return saveAll;
    }

    @Override
    public CurrencyValue save(CurrencyValue toSave) {
        AutoCrud.save(toSave);
        return getById(toSave.getId().toString());
    }

}