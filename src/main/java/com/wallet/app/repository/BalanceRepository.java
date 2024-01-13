package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Balance;

public class BalanceRepository extends AutoCrud<Balance, String>{

    @Override
    protected String getTableName() {
        return "balance_history";
    }

    @Override
    protected Balance mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new Balance(
                resultSet.getString("id"),
                resultSet.getDouble("value"),
                resultSet.getTimestamp("updatedatetime"),
                resultSet.getString("accountid")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

}
