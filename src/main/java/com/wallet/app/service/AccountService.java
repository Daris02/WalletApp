package com.wallet.app.service;

import java.util.List;

import java.time.LocalDateTime;
import java.sql.Timestamp;

import com.wallet.app.model.Account;
import com.wallet.app.model.Balance;
import com.wallet.app.model.Currency;
import com.wallet.app.model.SpendAmount;
import com.wallet.app.model.Transaction;
import com.wallet.app.repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepo = new AccountRepository();
    private CurrencyService currencyService = new CurrencyService();

    private TransactionService transactionService = new TransactionService();

    public Account getAccountById(String id) {
        Account account = accountRepo.getById(id);
        List<Transaction> transactionsList = transactionService.getALlTransactionsByAccoundId(id);
        account.setBalance(accountRepo.getBalanceNow(id).getValue());
        if (transactionsList != null) {
            account.setTransactionList(transactionsList);
        }

        return account;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        for (Account account : accounts) {
            List<Transaction> transactionsList = transactionService.getALlTransactionsByAccoundId(account.getId());
            account.setBalance(accountRepo.getBalanceNow(account.getId()).getValue());
            if (transactionsList != null) {
                account.setTransactionList(transactionsList);
            }
        }
        return accounts;
    }

    public Account saveAccount(Account Account, String currencyCode) {
        for (Currency curr : currencyService.getAllCurrencies()) {
            if(curr.getCode().equals(currencyCode)) {
                Account.setCurrency(curr);
            }
        }
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

    public List<SpendAmount> getAllTotalSpendAmounts(String accountId, LocalDateTime start, LocalDateTime end) {
        return accountRepo.findAllTotalSpendAmount(accountId, start.toString().replace("T", " "), end.toString().replace("T", " "));
    }
}
