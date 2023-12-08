package com.wallet.app.service;

import java.util.List;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import com.wallet.app.model.Account;
import com.wallet.app.model.Balance;
import com.wallet.app.repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepo = new AccountRepository();

    private TransactionService transactionService = new TransactionService();

    public Account getAccountById(String id) {
        Account account = accountRepo.getById(id);
        account.setTransactionList(transactionService.getALlTransactionsByAccoundId(id));
        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        for (Account account : accounts) {
            account.setTransactionList(transactionService.getALlTransactionsByAccoundId(account.getId()));
        }
        return accounts;
    }

    public Account saveAccount(Account Account) {
        return accountRepo.save(Account);
    }

    public List<Account> saveAllAccounts(List<Account> accounts) {
        return accountRepo.saveAll(accounts);
    }

    public List<Balance> getBalancesHistory(String id) {
        return accountRepo.getBalanceHistory(id, null, null);
    }
    
    public List<Balance> getBalancesHistoryWithDate(String id, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        Timestamp start = Timestamp.valueOf(startDatetime);
        Timestamp end = Timestamp.valueOf(endDatetime);
        return accountRepo.getBalanceHistory(id, start, end);
    }
}
