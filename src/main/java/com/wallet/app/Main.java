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
        Account account1 = new Account("test account", "Cash");
        Account account2 = new Account("saving account", "Bank");

        // System.out.println(acc.saveAccount(account1));
        // System.out.println(acc.saveAccount(account2));

        System.out.println(acc.getAllAccounts());

        // System.out.println(acc.getBalancesHistory("281c7a26-ba83-494f-9330-542c2d786b1f"));


        TransactionService tran = new TransactionService();
        Transaction transaction1 = new Transaction("Salary", 100_000.0, "CREDIT", "281c7a26-ba83-494f-9330-542c2d786b1f");
        Transaction transaction2 = new Transaction("Gift", 2_000_000.0, "CREDIT", "78047217-9fb5-449c-9622-57fc960bdeeb");

        // System.out.println(tran.saveTransaction(transaction1));
        // System.out.println(tran.saveTransaction(transaction2));

        // System.out.println(String.valueOf(transaction.getAmount()));
    }
}