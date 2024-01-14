package com.wallet.app.service;

import java.util.List;

import com.wallet.app.model.Currency;
import com.wallet.app.model.CurrencyValue;
import com.wallet.app.repository.CurrencyRepository;
import com.wallet.app.repository.CurrencyValueRepository;

public class CurrencyService {
    private CurrencyRepository currencyRepo = new CurrencyRepository();
    private CurrencyValueRepository currencyValueRepo = new CurrencyValueRepository();

    public Currency getCurrencyById(Integer id) {
        return currencyRepo.getById(id);
    }

    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    public Currency saveCurrency(Currency currency) {
        Integer id = getAllCurrencies().size();
        currency.setId(id == 0 ? id = 1 : id + 1);
        return currencyRepo.save(currency);
    }

    public List<Currency> saveAllCurrencies(List<Currency> currencies) {
        return currencyRepo.saveAll(currencies);
    }

    public void removeById(Integer id) {
        currencyRepo.removeById(id);
    }

    public List<CurrencyValue> getAllCurrencyValues() {
        return currencyValueRepo.findAll();
    }

    public CurrencyValue getCurrencyValueById(Integer id) {
        return currencyValueRepo.getById(id);
    }

    public CurrencyValue saveCurrencyValue(CurrencyValue toSave) {
        return currencyValueRepo.save(toSave);
    }
}
