package com.wallet.app;

import com.wallet.app.model.Account;
import com.wallet.app.model.Currency;
import com.wallet.app.model.CurrencyValue;
import com.wallet.app.model.Transaction;
import com.wallet.app.model.Transfert;
import com.wallet.app.service.AccountService;
import com.wallet.app.service.CurrencyService;
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
            // System.out.println(curr.saveCurrencyValue(new CurrencyValue("2", "1", 4550.0)));


        AccountService acc = new AccountService();
        Account C1 = new Account("test account", "Cash");
        Account C2 = new Account("current account", "Bank");
            // System.out.println(acc.saveAccount(C1, "MGA"));
            // System.out.println(acc.saveAccount(C2, "EUR"));

            // System.out.println(acc.getAccountById("701cb97d-e850-4091-af57-bdb7659c2da6"));
            // System.out.println(acc.getAllAccounts());

            // System.out.println(acc.getBalancesHistory("f37c2443-7225-436d-ba3f-1abc80a2fa9d"));
            // System.out.println(acc.getBalancesHistoryWithDate("f37c2443-7225-436d-ba3f-1abc80a2fa9d", LocalDateTime.of(2023, 12, 8, 18, 0, 0), LocalDateTime.of(2023, 12, 15, 18, 0, 0)));


        TransactionService tran = new TransactionService();
        Transaction T1 = new Transaction("Salary", 10_000.0, "CREDIT", "f37c2443-7225-436d-ba3f-1abc80a2fa9d", 32);
        Transaction T2 = new Transaction("Salary", 20.0, "CREDIT", "701cb97d-e850-4091-af57-bdb7659c2da6", 32);
        Transaction T3 = new Transaction("Gift", 20_000.0, "DEBIT", "f37c2443-7225-436d-ba3f-1abc80a2fa9d", 3);
        Transaction T4 = new Transaction("New shoes", 50_000.0, "DEBIT", "701cb97d-e850-4091-af57-bdb7659c2da6", 4);
        Transaction T5 = new Transaction("Gift", 2000.0, "DEBIT", "f37c2443-7225-436d-ba3f-1abc80a2fa9d", 5);

            // System.out.println(tran.saveTransaction(T1));
            // System.out.println(tran.saveTransaction(T2));
            // System.out.println(tran.saveTransaction(T3));
            // System.out.println(tran.saveTransaction(T4));
            // System.out.println(tran.saveTransaction(T5));

            // System.out.println(tran.getTransactionById("f00ff521-eae3-4a99-839c-3437fb7d1522"));

            // System.out.println(tran.getTransactionById("f00ff521-eae3-4a99-839c-3437fb7d1522"));

        TransfertService traf = new TransfertService();
            traf.saveTransfert("701cb97d-e850-4091-af57-bdb7659c2da6", "f37c2443-7225-436d-ba3f-1abc80a2fa9d", 5.0, "median");
            // System.out.println(traf.getAllTransferts());

        // Spend Amount ---
        LocalDateTime start =  LocalDateTime.of(2023, 12, 12, 18, 0, 0);
        LocalDateTime end = LocalDateTime.of(2023, 12, 15, 18, 0, 0);
            
            // --- SQL
            System.out.println(acc.getAllTotalSpendAmounts("f37c2443-7225-436d-ba3f-1abc80a2fa9d", start, end));
            
            // --- Java Only
            System.out.println(acc.findAllTotalSpendAmounts("f37c2443-7225-436d-ba3f-1abc80a2fa9d", start, end));

    }
}