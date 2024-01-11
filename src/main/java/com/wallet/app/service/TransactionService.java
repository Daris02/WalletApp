package com.wallet.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.app.model.Account;
import com.wallet.app.model.Category;
import com.wallet.app.model.Transaction;
import com.wallet.app.repository.CategoryRepository;
import com.wallet.app.repository.TransactionRepository;

public class TransactionService {
    private TransactionRepository transactionRepo = new TransactionRepository();

    private CategoryRepository categoryRepo = new CategoryRepository();

    public Transaction getTransactionById(String id) {
        return transactionRepo.getById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Account saveTransaction(Transaction transaction) {
        if (transaction.getId() == null) {
            transaction.setId(UUID.randomUUID().toString());
        }
        AccountService accountService = new AccountService();
        Account account = accountService.getAccountById(transaction.getAccountId());
        Double balance = account.getBalance();

        if (!account.getType().equals("Bank") && balance < transaction.getAmount() && transaction.getType().equals("DEBIT")) {
            System.out.println("Transaction failed: balance not enough.");
            return null;
        }
        
        List<Category> listCategory = categoryRepo.findAll();
        transaction.setCategoryId(listCategory.get(transaction.getCategoryId()).getId());

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

    public List<Transaction> getAllTransactionsByAccoundId(String id) {
        return transactionRepo.findAllByAccountId(id);
    }
}
