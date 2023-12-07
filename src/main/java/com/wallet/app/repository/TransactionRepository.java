package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Transaction;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransactionRepository implements Crud<Transaction> {
    private final Connection connection = ConnectionDB.createConnection();

    @Override
    public Transaction getById(String id) {
        String sql = "SELECT * FROM \"transaction\" WHERE id = " + id + ";";
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
                        resultSet.getString("accountid")
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
                        resultSet.getString("accountid")
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
        toSave.setId(UUID.randomUUID().toString());
        
        String sql = "DO $$" +
                "        BEGIN" +
                "            BEGIN" +
                "                UPDATE \"account\" SET balance = balance + " + toSave.getAmount() + " WHERE id = " + toSave.getAccountId() + ";" +
                "                INSERT INTO \"balance_history\" (value, accountId) VALUES " +
                "                    ( (SELECT balance FROM \"account\" WHERE id = " + toSave.getAccountId() + "), " + toSave.getAccountId() + " );" +
                "                INSERT INTO \"transaction\" (label, amount, transactiontype, accountId) VALUES " +
                "                    (" + toSave.getLabel() + ", " + toSave.getAmount() + ", " + toSave.getType() + ", " + toSave.getAccountId() + ");" +
                "            EXCEPTION" +
                "                WHEN OTHERS THEN" +
                "                    ROLLBACK;" +
                "                    RAISE;" +
                "            END;" +
                "            COMMIT;" +
                "        END $$;";

        try {
            connection.createStatement().executeUpdate(sql);
            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
