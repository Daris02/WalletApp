package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Account;
import com.wallet.app.model.Balance;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountRepository implements Crud<Account> {

    @Override
    public Account getById(String id) {
        return (Account) AutoCrud.findById(id, "account");
    }

    @Override
    public List<Account> findAll() {
        List<Account> listAccounts = new ArrayList<>();
        for (Object object : AutoCrud.findAll("account")) {
            listAccounts.add((Account)object);
        }
        return listAccounts;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave) {
        List<Account> saveAll = new ArrayList<>();
        for (Account account : toSave) {
            save(account);
            saveAll.add(getById(account.getId()));
        }
        return saveAll;
    }

    @Override
    public Account save(Account toSave) {
        AutoCrud.save(toSave);
        return getById(toSave.getId());
    }
    
    public List<Balance> getBalanceHistory(String id, Timestamp startDatetime, Timestamp endDatetime) {
        Connection connection = null;
        PreparedStatement preSt = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionDB.createConnection();

            String sql = "";
            if (startDatetime == null && endDatetime == null) {
                sql = "SELECT * FROM \"balance_history\" WHERE accountid = ?;";
            } else {
                sql = "SELECT * FROM \"balance_history\" WHERE accountid = ? " + 
                        " AND updatedatetime BETWEEN ? AND ? ;";
            }

            preSt = connection.prepareStatement(sql);
            preSt.setObject(1, UUID.fromString(id));
            
            List<Balance> responseSQL = new ArrayList<>();
            
            if (startDatetime != null && endDatetime != null) {
                preSt.setTimestamp(2, startDatetime);
                preSt.setTimestamp(3, endDatetime);
            }

            resultSet = preSt.executeQuery();
            
            while (resultSet.next()) {
                responseSQL.add(new Balance(
                        resultSet.getString("id"),
                        resultSet.getDouble("value"),
                        resultSet.getTimestamp("updatedatetime"),
                        resultSet.getString("accountid")
                ));
            }

            return responseSQL;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preSt != null) preSt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Balance getBalanceNow(String id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();

            String sql = "SELECT bh.* FROM \"account\" a INNER JOIN \"balance_history\" bh ON bh.accountid = a.id " +
                        "WHERE a.id = '" + id + "' " +
                        "ORDER BY updatedatetime DESC " +
                        "LIMIT 1;";

            resultSet = statement.executeQuery(sql);
            Balance responseSQL = null;

            while (resultSet.next()) {
                responseSQL = new Balance(
                        resultSet.getString("id"),
                        resultSet.getDouble("value"),
                        resultSet.getTimestamp("updatedatetime"),
                        resultSet.getString("accountid")
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

    public Map<String, Double> findAllTotalSpendAmount(String accountId, String startDatetime, String endDatetime) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();

            String sql = "  SELECT c.id AS category_id, " +
                        "      c.name AS category_name, " +
                        "      (SUM(CASE WHEN bh.value IS NOT NULL THEN bh.value ELSE 0 END)) AS total_amount " +
                        "  FROM \"category\" c " +
                        "      LEFT JOIN \"transaction\" tr ON tr.categoryid = c.id " +
                        "      LEFT JOIN \"account\" acc ON acc.id = tr.accountid " +
                        "       LEFT JOIN \"balance_history\" bh ON bh.accountid = acc.id " +
                        "   WHERE bh.accountId = '" + accountId + "' " +
                        "   AND updateDateTime BETWEEN '" + startDatetime + "' AND '" + endDatetime + "' " +
                        "   GROUP BY c.id, c.name; ";

            resultSet = statement.executeQuery(sql);
            Map<String, Double> responseSQL = new HashMap<>();
            
            while (resultSet.next()) {
                responseSQL.put(resultSet.getString("category_name"), resultSet.getDouble("total_amount"));
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
}
