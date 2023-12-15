package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Transaction;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransactionRepository implements Crud<Transaction> {
    private final Connection connection = ConnectionDB.createConnection();
    AccountRepository accountRepo = new AccountRepository();

    public List<Transaction> findAllByAccountId(String id) {
        String sql = "SELECT * FROM \"transaction\" WHERE accountid = '" + id + "';";
        List<Transaction> responseSQL = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL.add(new Transaction(
                        resultSet.getString("id"),
                        resultSet.getString("label"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transactiontype"),
                        resultSet.getTimestamp("datetime"),
                        resultSet.getString("accountid"),
                        resultSet.getInt("categoryid")
                    )
                );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Transaction getById(String id) {
        String sql = "SELECT * FROM \"transaction\" WHERE id = '" + id + "';";
        Transaction responseSQL = null;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL = new Transaction(
                        resultSet.getString("id"),
                        resultSet.getString("label"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transactiontype"),
                        resultSet.getTimestamp("datetime"),
                        resultSet.getString("accountid"),
                        resultSet.getInt("categoryid")
                    );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transaction> findAll() {
        String sql = "SELECT  * FROM \"transaction\" ORDER BY datetime;";
        List<Transaction> responseSQL = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL.add(new Transaction(
                        resultSet.getString("id"),
                        resultSet.getString("label"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transactiontype"),
                        resultSet.getTimestamp("datetime"),
                        resultSet.getString("accountid"),
                        resultSet.getInt("categoryid")
                    )
                );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        List<Transaction> saveAll = new ArrayList<>();
        for (Transaction transaction : toSave) {
            save(transaction);
            saveAll.add(getById(transaction.getId()));
        }
        return saveAll;
    }

    @Override
    public Transaction save(Transaction toSave) {
        String sql = "";

        if ("DEBIT".equals(toSave.getType())) {
            sql = "DO $$" +
                    "BEGIN" +
                    "   BEGIN" +
                    "       INSERT INTO \"balance_history\" (value, accountId) VALUES " +
                    "           ( (" + accountRepo.getBalanceNow(toSave.getAccountId()).getValue() + " - " + toSave.getAmount() + "), " +
                    "              '" + toSave.getAccountId() + "' );" +
                    "       INSERT INTO \"transaction\" (label, amount, transactiontype, accountId,  categoryId) VALUES " +
                    "           ('" + toSave.getLabel() + "', " + toSave.getAmount() + ", '" + toSave.getType() + "', '" + toSave.getAccountId() + "', " + toSave.getCategoryId() + ");" +
                    "       EXCEPTION" +
                    "           WHEN OTHERS THEN" +
                    "               ROLLBACK;" +
                    "               RAISE;" +
                    "   END;" +
                    "   COMMIT;" +
                    "END $$;";
        }
        
        if ("CREDIT".equals(toSave.getType())) {
            sql = "DO $$" +
                    "BEGIN" +
                    "   BEGIN" +
                    "       INSERT INTO \"balance_history\" (value, accountId) VALUES " +
                    "           ( (" + accountRepo.getBalanceNow(toSave.getAccountId()).getValue() + " + " + toSave.getAmount() + "), " +
                    "            '" + toSave.getAccountId() + "' );" +
                    "       INSERT INTO \"transaction\" (label, amount, transactiontype, accountId, categoryId) VALUES " +
                    "           ('" + toSave.getLabel() + "', " + toSave.getAmount() + ", '" + toSave.getType() + "', '" + toSave.getAccountId() + "', " + toSave.getCategoryId() + ");" +
                    "       EXCEPTION" +
                    "           WHEN OTHERS THEN" +
                    "               ROLLBACK;" +
                    "               RAISE;" +
                    "   END;" +
                    "   COMMIT;" +
                    "END $$;";
        }
        
        try {
            connection.createStatement().executeUpdate(sql);
            return toSave;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
