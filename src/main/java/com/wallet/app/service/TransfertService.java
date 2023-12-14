package com.wallet.app.service;

import java.util.List;

import java.time.LocalDateTime;
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
    
    public Transfert saveTransfert(String debtorId, String creditorId, Double amount) {
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

            for (CurrencyValue currencyValue : currencyService.getAllCurrencyValues()) {
                if (currencyValue.getDateEffect().equals(LocalDate.now())) {
                    currencyDebitor = currencyService.getCurrencyById(currencyValue.getCurrencySource());
                    currencyCreditor = currencyService.getCurrencyById(currencyValue.getCurrencyDestination());
                    rateChange = currencyValue.getAmount();
                }
            }

            if (currencyDebitor.getCode().equals("EUR") && currencyCreditor.getCode().equals("MGA")) {
                finalAmount = amount * rateChange;
            }
            if (currencyDebitor.getCode().equals("MGA") && currencyCreditor.getCode().equals("EUR")) {
                finalAmount = amount / rateChange;
            }
        }

        transactionRepository.save(new Transaction("Transfert", amount, "DEBIT", debtorId));
        transactionRepository.save(new Transaction("Transfert", finalAmount, "CREDIT", creditorId));
        
        Transfert transfert = new Transfert(debtorId, creditorId, amount);
        transfertRepository.save(transfert);
        
        return transfert;
    }
    
}
 