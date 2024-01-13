package com.wallet.app;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Account;
import com.wallet.app.model.Currency;
import com.wallet.app.model.CurrencyValue;
import com.wallet.app.model.Transaction;
import com.wallet.app.model.Transfert;
import com.wallet.app.service.AccountService;
import com.wallet.app.service.CurrencyService;
import com.wallet.app.service.TransactionService;
import com.wallet.app.service.TransfertService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        walletTest();
    }

    public static void walletTest() {
        CurrencyService curr = new CurrencyService();
            // System.out.println(curr.saveCurrency(new Currency("Ariary", "MGA")));
            // System.out.println(curr.saveCurrency(new Currency("Euro", "EUR")));
            // System.out.println(curr.saveCurrency(new Currency("Dollar US", "USD")));
            // System.out.println(curr.getAllCurrencies());
            // System.out.println(curr.getCurrencyById(1));
            // System.out.println(curr.getAllCurrencyValues());
            // System.out.println(curr.getCurrencyValueById(0));
            // System.out.println(curr.saveCurrencyValue(new CurrencyValue("2", "1", 4601.13)));


        AccountService acc = new AccountService();
        Account C1 = new Account("AccountMGA_1", "Cash", 1);
        Account C2 = new Account("AccountEUR_2", "Bank", 2);
            String id1 = "a5868f16-2b96-4773-8b17-163d26b1e42a";
            String id2 = "746faf84-b7de-477b-a2b8-4b9fdcd79d27";

            // System.out.println(acc.saveAccount(C1));
            // System.out.println(acc.saveAccount(C2));

            // System.out.println(acc.getAccountById(id1));
            // System.out.println(acc.getAccountById(id2));
            // System.out.println(acc.getAllAccounts());

            // System.out.println(acc.getBalancesHistory(id1));
            // System.out.println(acc.getBalancesHistoryWithDate(id1, LocalDateTime.of(2023, 12, 18, 8, 0, 0), LocalDateTime.of(2023, 12, 22, 8, 0, 0)));



        TransactionService tran = new TransactionService();
        Transaction T1 = new Transaction("Salary", 10_000.0, "CREDIT", id1, 75);
        Transaction T2 = new Transaction("Salary", 20.0, "CREDIT", id2, 75);
        Transaction T3 = new Transaction("Gift", 20_000.0, "DEBIT", id1, 3);
        Transaction T4 = new Transaction("New shoes", 50_000.0, "DEBIT", id2, 4);
        Transaction T5 = new Transaction("Gift", 2000.0, "DEBIT", id1, 5);

            // System.out.println(tran.saveTransaction(T1));
            // System.out.println(tran.saveTransaction(T2));
            // System.out.println(tran.saveTransaction(T3));
            // System.out.println(tran.saveTransaction(T4));
            // System.out.println(tran.saveTransaction(T5));

            // System.out.println(tran.getTransactionById("c5f219d0-72f8-4bbd-ac5a-09b16672ecec"));

        TransfertService traf = new TransfertService();
            // System.out.println(traf.saveTransfert(id2, id1, 5.0, "median"));
            // System.out.println(traf.getAllTransferts());



        // Spend Amount ---
        LocalDateTime start =  LocalDateTime.of(2024, 1, 13, 8, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 14, 8, 0, 0);
            
            // --- SQL
            // System.out.println(acc.getAllTotalSpendAmounts(id1, start, end));
            
            // --- Java Only
            // System.out.println(acc.findAllTotalSpendAmounts(id1, start, end));

    }
}