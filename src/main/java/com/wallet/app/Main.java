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
        Account C2 = new Account("saving account", "Bank");
            // System.out.println(acc.saveAccount(C1, "MGA"));
            // System.out.println(acc.saveAccount(C2, "EUR"));

            // System.out.println(acc.getAccountById("9092d140-e16f-45f9-9476-51166f2b7ff9"));
            // System.out.println(acc.getAllAccounts());

            // System.out.println(acc.getBalancesHistory("3a04306b-bf00-40be-8149-19415e04d5a8"));
            // System.out.println(acc.getBalancesHistoryWithDate("3a04306b-bf00-40be-8149-19415e04d5a8", LocalDateTime.of(2023, 12, 8, 17, 0, 0), LocalDateTime.of(2023, 12, 8, 18, 0, 0)));


        TransactionService tran = new TransactionService();
        Transaction T1 = new Transaction("Salary", 10_000.0, "CREDIT", "3a04306b-bf00-40be-8149-19415e04d5a8", 1);
        Transaction T2 = new Transaction("Salary", 20.0, "CREDIT", "9092d140-e16f-45f9-9476-51166f2b7ff9", 2);
        Transaction T3 = new Transaction("Gift", 20_000.0, "DEBIT", "3a04306b-bf00-40be-8149-19415e04d5a8", 3);
        Transaction T4 = new Transaction("New shoes", 50_000.0, "DEBIT", "9092d140-e16f-45f9-9476-51166f2b7ff9", 4);
        Transaction T5 = new Transaction("Gift", 2000.0, "DEBIT", "3a04306b-bf00-40be-8149-19415e04d5a8", 5);

            // System.out.println(tran.saveTransaction(T1));
            // System.out.println(tran.saveTransaction(T2));
            // System.out.println(tran.saveTransaction(T3));
            // System.out.println(tran.saveTransaction(T4));
            System.out.println(tran.saveTransaction(T5));

            // System.out.println(tran.getTransactionById("f00ff521-eae3-4a99-839c-3437fb7d1522"));

        TransfertService traf = new TransfertService();
            // traf.saveTransfert("9092d140-e16f-45f9-9476-51166f2b7ff9", "3a04306b-bf00-40be-8149-19415e04d5a8", 5.0, "median");
            // System.out.println(traf.getAllTransferts());

        // Spend Amount ---
        LocalDateTime start =  LocalDateTime.of(2023, 12, 12, 12, 30, 0);
        LocalDateTime end = LocalDateTime.of(2023, 12, 15, 12, 30, 0);
            System.out.println(acc.getAllTotalSpendAmounts("3a04306b-bf00-40be-8149-19415e04d5a8", start, end));

    }
}