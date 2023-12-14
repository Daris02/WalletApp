package com.wallet.app.service;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Account;
import com.wallet.app.model.Transaction;
import com.wallet.app.repository.TransactionRepository;

public class TransactionService {
    private TransactionRepository transactionRepo = new TransactionRepository();

    public Transaction getTransactionById(String id) {
        return transactionRepo.getById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Account saveTransaction(Transaction transaction) {
        AccountService accountService = new AccountService();
        Account account = accountService.getAccountById(transaction.getAccountId());
        Double balance = account.getBalance();
        if (!account.getType().equals("Bank") && balance < transaction.getAmount() && transaction.getType().equals("DEBIT")) {
            System.out.println("Transaction failed: balance not enough.");
            return null;
        }        
        transactionRepo.save(transaction);
        return accountService.getAccountById(transaction.getAccountId());
    }

    public List<Account> saveAllTransactions(List<Transaction> transactions) {
        List<Account> accounts = new ArrayList<>();
        for (Transaction transaction : transactions) {
            accounts.add(saveTransaction(transaction));
        }
        return accounts;
    }

    public List<Transaction> getALlTransactionsByAccoundId(String id) {
        return transactionRepo.findAllByAccountId(id);
    }
}
