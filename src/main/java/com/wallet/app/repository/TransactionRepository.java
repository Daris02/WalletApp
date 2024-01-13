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
public class TransactionRepository extends AutoCrud<Transaction, String> {
    
    @Override
    protected String getTableName() {
        return "transaction";
    }
    
    @Override
    protected Transaction mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new Transaction(
                resultSet.getString("id"),
                resultSet.getString("label"),
                resultSet.getDouble("amount"),
                resultSet.getString("type"),
                resultSet.getTimestamp("datetime"),
                resultSet.getString("accountid"),
                resultSet.getInt("categoryid")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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
                responseSQL.add(mapResultSetToEntity(resultSet));
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
    public List<Transaction> saveAll(List<Transaction> toSave) {
        List<Transaction> saveAll = new ArrayList<>();
        for (Transaction transaction : toSave) {
            save(transaction);
            saveAll.add(getById(transaction.getId()));
        }
        return saveAll;
    }
}
