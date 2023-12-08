package com.wallet.app.service;

import java.util.List;

import com.wallet.app.model.Account;
import com.wallet.app.model.Balance;
import com.wallet.app.repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepo = new AccountRepository();
    
    public Account getAccountById(String id) {
        return accountRepo.getById(id);
    }

    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    public Account saveAccount(Account Account) {
        return accountRepo.save(Account);
    }

    public List<Account> saveAllAccounts(List<Account> accounts) {
        return accountRepo.saveAll(accounts);
    }

    public List<Balance> getBalancesHistory(String id) {
        return accountRepo.getBalanceHistory(id);
    }
}
