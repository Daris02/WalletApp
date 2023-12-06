package com.wallet.app;

import com.wallet.app.model.Account;
import com.wallet.app.model.Currency;
import com.wallet.app.service.AccountService;
import com.wallet.app.service.CurrencyService;

public class Main {
    public static void main(String[] args) {
        walletTest();
    }

    public static void walletTest() {
        CurrencyService curr = new CurrencyService();
        System.out.println(curr.saveCurrency(new Currency("Dollar", "USD")));
        System.out.println(curr.getAllCurrencies());

        AccountService acc = new AccountService();
        System.out.println(acc.saveAccount(new Account("saving account", "Bank")));
        System.out.println(acc.getAllAccounts());
    }
}