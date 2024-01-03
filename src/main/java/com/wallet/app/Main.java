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
            // System.out.println(curr.saveCurrency(new Currency("Dollar", "USD")));
            // System.out.println(curr.getAllCurrencies());
            // System.out.println(curr.getAllCurrencyValues());
            // System.out.println(curr.getCurrencyValueById("1"));
            // System.out.println(curr.saveCurrencyValue(new CurrencyValue("2", "1", 4601.13)));


        AccountService acc = new AccountService();
        Account C1 = new Account("test account", "Cash");
        Account C2 = new Account("current account", "Bank");
            String id1 = "f37c2443-7225-436d-ba3f-1abc80a2fa9d";
            String id2 = "701cb97d-e850-4091-af57-bdb7659c2da6";

            // System.out.println(acc.saveAccount(C1, "MGA"));
            // System.out.println(acc.saveAccount(C2, "EUR"));

            // System.out.println(acc.getAccountById(id2));
            // System.out.println(acc.getAllAccounts());

            // System.out.println(acc.getBalancesHistory(id1));
            // System.out.println(acc.getBalancesHistoryWithDate(id1, LocalDateTime.of(2023, 12, 18, 8, 0, 0), LocalDateTime.of(2023, 12, 22, 8, 0, 0)));



        TransactionService tran = new TransactionService();
        Transaction T1 = new Transaction("Salary", 10_000.0, "CREDIT", id1, 32);
        Transaction T2 = new Transaction("Salary", 20.0, "CREDIT", id2, 32);
        Transaction T3 = new Transaction("Gift", 20_000.0, "DEBIT", id1, 3);
        Transaction T4 = new Transaction("New shoes", 50_000.0, "DEBIT", id2, 4);
        Transaction T5 = new Transaction("Gift", 2000.0, "DEBIT", id1, 5);

            // System.out.println(tran.saveTransaction(T1));
            // System.out.println(tran.saveTransaction(T2));
            // System.out.println(tran.saveTransaction(T3));
            // System.out.println(tran.saveTransaction(T4));
            // System.out.println(tran.saveTransaction(T5));

            // System.out.println(tran.getTransactionById("aa9404fd-aa17-4754-89a6-4827495ca000"));

        TransfertService traf = new TransfertService();
            // System.out.println(traf.saveTransfert(id2, id1, 5.0, "median"));
            // System.out.println(traf.getAllTransferts());



        // Spend Amount ---
        LocalDateTime start =  LocalDateTime.of(2024, 1, 3, 8, 0, 0);
        LocalDateTime end = LocalDateTime.of(2024, 1, 4, 8, 0, 0);
            
            // --- SQL
            // System.out.println(acc.getAllTotalSpendAmounts(id1, start, end));
            
            // --- Java Only
            // System.out.println(acc.findAllTotalSpendAmounts(id1, start, end));

    }
}