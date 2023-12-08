package com.wallet.app.service;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Account;
import com.wallet.app.model.Transaction;
import com.wallet.app.repository.AccountRepository;
import com.wallet.app.repository.TransactionRepository;

public class TransactionService {
    private TransactionRepository transactionRepo = new TransactionRepository();
    private AccountRepository accountRepo = new AccountRepository();

    public Transaction getTransactionById(String id) {
        return transactionRepo.getById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Account saveTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
        return accountRepo.getById(transaction.getAccountId());
    }

    public List<Account> saveAllTransactions(List<Transaction> transactions) {
        List<Account> accounts = new ArrayList<>();
        for (Transaction transaction : transactions) {
            accounts.add(saveTransaction(transaction));
        }
        return accounts;
    }
}
