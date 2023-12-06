package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Currency;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyRepository implements Crud<Currency> {
    private final Connection connection = ConnectionDB.createConnection();

    @Override
    public Currency getById(String id) {
        String sql = "SELECT * FROM \"currency\" WHERE id = " + id + ";";
        Currency responseSQL = null;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL = new Currency(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                    );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Currency> findAll() {
        String sql = "SELECT  * FROM \"currency\" ORDER BY name;";
        List<Currency> responseSQL = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

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
            e.printStackTrace();
        }
        return null;
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
        toSave.setId(UUID.randomUUID().toString());
        
        String sql = "INSERT INTO \"currency\" (id, name, code) VALUES " +
            "( '" + toSave.getId() + "', '" + toSave.getName() + "', '" + toSave.getCode() + "'  );";

        try {
            connection.createStatement().executeUpdate(sql);
            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
