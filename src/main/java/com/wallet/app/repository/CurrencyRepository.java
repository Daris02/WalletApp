package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Currency;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyRepository implements Crud<Currency> {

    @Override
    public Currency getById(String id) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM \"currency\" WHERE id = " + id + ";";
            resultSet = statement.executeQuery(sql);
            Currency responseSQL = null;
            
            while (resultSet.next()) {
                responseSQL = new Currency(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
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
    public List<Currency> findAll() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();
            String sql = "SELECT  * FROM \"currency\" ORDER BY name;";
            resultSet = statement.executeQuery(sql);
            List<Currency> responseSQL = new ArrayList<>();

            while (resultSet.next()) {
                responseSQL.add(new Currency(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
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
    public List<Currency> saveAll(List<Currency> toSave) {
        List<Currency> saveAll = new ArrayList<>();
        for (Currency currency : toSave) {
            save(currency);
            saveAll.add(getById(currency.getId()));
        }
        return saveAll;
    }

    @Override
    public Currency save(Currency toSave) {
        Connection connection = null;
        Statement statement = null;
        
        
        try {
            toSave.setId(UUID.randomUUID().toString());
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();
            String sql = "INSERT INTO \"currency\" (id, name, code) VALUES " +
            "( '" + toSave.getId() + "', '" + toSave.getName() + "', '" + toSave.getCode() + "'  );";
            
            statement.executeUpdate(sql);
            return toSave;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
}
