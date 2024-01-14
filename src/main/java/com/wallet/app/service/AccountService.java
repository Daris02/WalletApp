package com.wallet.app.service;

import java.util.List;

import java.time.LocalDateTime;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.wallet.app.model.Account;
import com.wallet.app.model.Balance;
import com.wallet.app.model.Category;
import com.wallet.app.model.Transaction;
import com.wallet.app.repository.AccountRepository;
import com.wallet.app.repository.CategoryRepository;

public class AccountService {
    private AccountRepository accountRepo = new AccountRepository();
    private CategoryRepository categoryRepo = new CategoryRepository();
    private BalanceService balanceService = new BalanceService();
    private TransactionService transactionService = new TransactionService();

    public Account getAccountById(String id) {
        Account account = accountRepo.getById(id);
        List<Transaction> transactionsList = transactionService.getAllTransactionsByAccoundId(id);
        account.setBalance(balanceService.getBalanceNow(id).getValue());
        if (transactionsList != null) {
            account.setTransactionList(transactionsList);
        }
        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        for (Account account : accounts) {
            List<Transaction> transactionsList = transactionService.getAllTransactionsByAccoundId(account.getId());
            account.setBalance(balanceService.getBalanceNow(account.getId()).getValue());
            if (transactionsList != null) {
                account.setTransactionList(transactionsList);
            }
        }
        return accounts;
    }

    public Account saveAccount(Account account) {
        if (account.getId() == null) {
            account.setId(UUID.randomUUID().toString());
        }
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        account.setCreationDate(timestamp);
        account.setBalance(0.0);
        Account accountSave = accountRepo.save(account);
        balanceService.save(new Balance(UUID.randomUUID().toString(), 0.0, timestamp, account.getId()));
        return accountSave;
    }

    public List<Account> saveAllAccounts(List<Account> accounts) {
        return accountRepo.saveAll(accounts);
    }

    public void removeById(String id) {
        for (Balance balance : balanceService.getAll()) {
            if (balance.getAccountId().equals(id)) {
                balanceService.removeById(balance.getId());
            }
        }
        accountRepo.removeById(id);
    }

    public Map<String, Double> getAllTotalSpendAmounts(String accountId, LocalDateTime start, LocalDateTime end) {
        return accountRepo.findAllTotalSpendAmount(accountId, start.toString().replace("T", " "), end.toString().replace("T", " "));
    }

    public Map<String, Double> findAllTotalSpendAmounts(String accountId, LocalDateTime start, LocalDateTime end) {
        Map<String, Double> allSpendAmounts = new HashMap<>();
        List<Transaction> allTransactions = transactionService.getAllTransactionsByAccoundId(accountId);
        List<Category> allCategories = categoryRepo.findAll();

        for (Transaction transaction : allTransactions) {
            if (transaction.getDateTime().after(Timestamp.valueOf(start)) && transaction.getDateTime().before(Timestamp.valueOf(end))) {
                for (Category category : allCategories) {
                    Double amount = 0.0;
                    if (transaction.getCategoryId().equals(category.getId()) && !allSpendAmounts.containsKey(category.getName())) {
                        allSpendAmounts.put(category.getName(),  transaction.getAmount() + amount);
                    } else if (transaction.getCategoryId().equals(category.getId()) && allSpendAmounts.containsKey(category.getName())) {
                        amount = allSpendAmounts.get(category.getName()) + transaction.getAmount();
                        allSpendAmounts.put(category.getName(), transaction.getAmount() + amount);
                    }
                }
            }
        }
        
        return allSpendAmounts;
    }
}
