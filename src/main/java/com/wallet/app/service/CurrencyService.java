package com.wallet.app.service;

import java.util.List;

import com.wallet.app.model.Currency;
import com.wallet.app.repository.CurrencyRepository;

public class CurrencyService {
    private CurrencyRepository currencyRepo = new CurrencyRepository();

    public Currency getCurrencyById(String id) {
        return currencyRepo.getById(id);
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    public Currency saveCurrency(Currency currency) {
        return currencyRepo.save(currency);
    }

    public List<Currency> saveAllCurrencies(List<Currency> currencies) {
        return currencyRepo.saveAll(currencies);
    }
}
