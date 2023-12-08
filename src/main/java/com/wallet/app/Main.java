package com.wallet.app;

import com.wallet.app.model.Account;
import com.wallet.app.model.Currency;
import com.wallet.app.model.Transaction;
import com.wallet.app.service.AccountService;
import com.wallet.app.service.CurrencyService;
import com.wallet.app.service.TransactionService;

public class Main {
    public static void main(String[] args) {
        walletTest();
    }

    public static void walletTest() {
        CurrencyService curr = new CurrencyService();
        // System.out.println(curr.saveCurrency(new Currency("Dollar", "USD")));
        // System.out.println(curr.getAllCurrencies());

        AccountService acc = new AccountService();
        // System.out.println(acc.saveAccount(new Account("saving account", "Bank")));
        // System.out.println(acc.getAllAccounts());

        TransactionService tran = new TransactionService();
        Transaction transaction1 = new Transaction("Gift", 20_000.0, "DEBIT", "059b0f00-fcf8-4b2c-bde7-4ec2d695f7f1");
        Transaction transaction2 = new Transaction("Salary", 100_000.25, "CREDIT", "f790706d-24b1-4c5e-8825-7c6a6e71c102");
        System.out.println(tran.saveTransaction(transaction1));
        // System.out.println(tran.saveTransaction(transaction2));
        // System.out.println(String.valueOf(transaction.getAmount()));
    }
}