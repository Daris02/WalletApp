package com.wallet.app.service;

import java.util.List;

import com.wallet.app.model.Currency;
import com.wallet.app.model.Transaction;
import com.wallet.app.model.Transfert;
import com.wallet.app.repository.TransactionRepository;
import com.wallet.app.repository.TransfertRepository;

public class TransfertService {
    TransactionRepository transactionRepository = new TransactionRepository();
    TransfertRepository transfertRepository = new TransfertRepository();
    AccountService accountService = new AccountService();

    public List<Transfert> getAllTransferts() {
        return transfertRepository.findAll();
    }
    
    public Transfert saveTransfert(String debtorId, String creditorId, Double amount) {
        Currency currencyDebitor = accountService.getAccountById(debtorId).getCurrency();
        Currency currencyCreditor = accountService.getAccountById(creditorId).getCurrency();

        if (debtorId == creditorId) {
            System.out.println("Transfer failed: an account can't do transfer to himself.");
            return null;
        } else if (accountService.getAccountById(debtorId).getCurrency().getCode() != accountService.getAccountById(creditorId).getCurrency().getCode()) {
                        System.out.println("Transfer failed: an account can't do transfer to another account with different currency.");
            return null;
        }

        transactionRepository.save(new Transaction("Transfert", amount, "DEBIT", debtorId));
        transactionRepository.save(new Transaction("Transfert", amount, "CREDIT", creditorId));
        
        Transfert transfert = new Transfert(debtorId, creditorId, amount);
        transfertRepository.save(transfert);
        
        return transfert;
    }
    
}
 