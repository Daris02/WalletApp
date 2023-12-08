package com.wallet.app;

import com.wallet.app.model.Account;
import com.wallet.app.model.Currency;
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


        AccountService acc = new AccountService();
        Account account1 = new Account("test account", "Cash");
        Account account2 = new Account("saving account", "Bank");

        // System.out.println(acc.saveAccount(account1));
        // System.out.println(acc.saveAccount(account2));

        // System.out.println(acc.getAllAccounts());

        // System.out.println(acc.getBalancesHistory("a28301bf-593e-435e-9e9a-bdf7d00cb64a"));
        // System.out.println(acc.getBalancesHistoryWithDate("a28301bf-593e-435e-9e9a-bdf7d00cb64a", LocalDateTime.of(2023, 12, 8, 17, 0, 0), LocalDateTime.of(2023, 12, 8, 18, 0, 0)));


        TransactionService tran = new TransactionService();
        Transaction transaction1 = new Transaction("Salary", 100_000.0, "CREDIT", "a28301bf-593e-435e-9e9a-bdf7d00cb64a");
        Transaction transaction2 = new Transaction("Gift", 2_000_000.0, "CREDIT", "a216cda3-8387-4a51-9369-414e43c5c1bc");

        // System.out.println(tran.saveTransaction(transaction1));
        // System.out.println(tran.saveTransaction(transaction2));

        TransfertService traf = new TransfertService();
        traf.saveTransfert("a28301bf-593e-435e-9e9a-bdf7d00cb64a", "a216cda3-8387-4a51-9369-414e43c5c1bc", 50_000.0);
        System.out.println(traf.getAllTransferts());
    }
}