package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.CurrencyValue;

public class CurrencyValueRepository implements Crud<CurrencyValue> {
    private final Connection connection = ConnectionDB.createConnection();

    @Override
    public CurrencyValue getById(String id) {
        String sql = "SELECT * FROM \"currency_value\" WHERE id = " + id + ";";
        CurrencyValue responseSQL = null;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL = new CurrencyValue(
                        resultSet.getInt("id"),
                        resultSet.getString("currency_source"),
                        resultSet.getString("currency_destination"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("date_effect").toLocalDateTime()
                    );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CurrencyValue> findAll() {
        String sql = "SELECT * FROM \"currency_value\" ORDER BY date_effect;";
        List<CurrencyValue> responseSQL = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL.add(new CurrencyValue(
                        resultSet.getInt("id"),
                        resultSet.getString("currency_source"),
                        resultSet.getString("currency_destination"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("date_effect").toLocalDateTime()
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
    public List<CurrencyValue> saveAll(List<CurrencyValue> toSave) {
        List<CurrencyValue> saveAll = new ArrayList<>();
        for (CurrencyValue currencyValue : toSave) {
            save(currencyValue);
            saveAll.add(getById(currencyValue.getId().toString()));
        }
        return saveAll;
    }

    @Override
    public CurrencyValue save(CurrencyValue toSave) {        
        String sql = "INSERT INTO \"currency_value\" (currency_source, currency_destination, amount) VALUES " +
            "( " + toSave.getCurrencySource() + ", " + toSave.getCurrencyDestination() + ", " + toSave.getAmount() + " );";

        try {
            connection.createStatement().executeUpdate(sql);
            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}