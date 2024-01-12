package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Transaction;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransactionRepository implements Crud<Transaction> {
    AccountRepository accountRepo = new AccountRepository();

    public List<Transaction> findAllByAccountId(String id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM \"transaction\" WHERE accountid = '" + id + "';";
            resultSet = statement.executeQuery(sql);
            List<Transaction> responseSQL = new ArrayList<>();

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
            throw new RuntimeException(e);

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Transaction getById(String id) {
        return (Transaction) AutoCrud.findById(id, "transaction");
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> listTransactions = new ArrayList<>();
        for (Object object : AutoCrud.findAll("Transaction")) {
            listTransactions.add((Transaction)object);
        }
        return listTransactions;
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
        AutoCrud.save(toSave);
        return getById(toSave.getId());
    }

}
