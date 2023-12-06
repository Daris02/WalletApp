package com.wallet.app.service;

import java.util.List;

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

    public Transaction saveTransaction(Transaction Transaction) {
        return transactionRepo.save(Transaction);
    }

    public List<Transaction> saveAllTransactions(List<Transaction> transactions) {
        return transactionRepo.saveAll(transactions);
    }
}
