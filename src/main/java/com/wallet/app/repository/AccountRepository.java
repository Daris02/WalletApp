package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Account;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountRepository extends AutoCrud<Account, String> {
    CurrencyRepository currencyRepository = new CurrencyRepository();
    
    @Override
    protected String getTableName() {
        return "account";
    }
    
    @Override
    protected Account mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new Account(
                resultSet.getString("id"),
                resultSet.getString("name"),
                0.0,
                resultSet.getTimestamp("creationdate"),
                resultSet.getString("type"),
                resultSet.getInt("currencyid")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
