package com.wallet.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.time.LocalDate;

import com.wallet.app.model.Currency;
import com.wallet.app.model.CurrencyValue;
import com.wallet.app.model.Transaction;
import com.wallet.app.model.Transfert;
import com.wallet.app.repository.TransactionRepository;
import com.wallet.app.repository.TransfertRepository;

public class TransfertService {
    TransactionRepository transactionRepository = new TransactionRepository();
    TransfertRepository transfertRepository = new TransfertRepository();
    AccountService accountService = new AccountService();
    CurrencyService currencyService = new CurrencyService();

    public List<Transfert> getAllTransferts() {
        return transfertRepository.findAll();
    }
    
    public Transfert saveTransfert(String debtorId, String creditorId, Double amount, String currencyValueChoice) {
        String currencyDebitorId = accountService.getAccountById(debtorId).getCurrency().getId();
        String currencyCreditorId = accountService.getAccountById(creditorId).getCurrency().getId();
        Double finalAmount = 0.0;
        
        if (debtorId == creditorId) {
            System.out.println("Transfer failed: an account can't do transfer to himself.");
            return null;
        } else if (currencyDebitorId != currencyCreditorId) {
            Currency currencyDebitor = null;
            Currency currencyCreditor = null;
            Double rateChange = 0.0;

            List<Double> listAmountValues = new ArrayList<>();

            for (CurrencyValue currencyValue : currencyService.getAllCurrencyValues()) {
                if (currencyValue.getDateEffect().toLocalDate().equals(LocalDate.now())) {
                    listAmountValues.add(currencyValue.getAmount());
                    currencyDebitor = currencyService.getCurrencyById(currencyValue.getCurrencySource());
                    currencyCreditor = currencyService.getCurrencyById(currencyValue.getCurrencyDestination());
                }
            }
            
            Collections.sort(listAmountValues);
            int middle = listAmountValues.size() / 2;
            Double max = listAmountValues.get(listAmountValues.size() - 1);
            Double min = listAmountValues.get(0);
            Double median = null;
            
            if (listAmountValues.size() == 1) {
                rateChange = listAmountValues.get(0);
            }
            if (listAmountValues.size() % 2 != 0) {
                median = listAmountValues.get(middle);
            }
            if (listAmountValues.size() % 2 == 0) {
                median = (listAmountValues.get(middle) + listAmountValues.get(middle - 1)) / 2;
            }

            if (currencyValueChoice.equals("max")) {
                rateChange = max;
            }
            if (currencyValueChoice.equals("min")) {
                rateChange = min;
            }
            if (currencyValueChoice.equals("median")) {
                rateChange = median;
            }

            assert currencyDebitor != null;
            if (currencyDebitor.getCode().equals("EUR") && currencyCreditor.getCode().equals("MGA")) {
                finalAmount = amount * rateChange;
            }
            if (currencyDebitor.getCode().equals("MGA") && currencyCreditor.getCode().equals("EUR")) {
                finalAmount = amount / rateChange;
            }
        }

        transactionRepository.save(new Transaction("Transfert", amount, "DEBIT", debtorId, 1));
        transactionRepository.save(new Transaction("Transfert", finalAmount, "CREDIT", creditorId, 1));
        
        Transfert transfert = new Transfert(debtorId, creditorId, amount);
        transfertRepository.save(transfert);
        
        return transfert;
    }
    
}
 