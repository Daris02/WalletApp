package com.wallet.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.CurrencyValue;

public class CurrencyValueRepository extends AutoCrud<CurrencyValue, Integer> {
    
    @Override
    protected String getTableName() {
        return "currency_value";
    }
    
    @Override
    protected CurrencyValue mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new CurrencyValue(
                resultSet.getInt("id"),
                resultSet.getInt("currency_source"),
                resultSet.getInt("currency_destination"),
                resultSet.getDouble("amount"),
                resultSet.getTimestamp("date_effect").toLocalDateTime()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CurrencyValue> saveAll(List<CurrencyValue> toSave) {
        List<CurrencyValue> saveAll = new ArrayList<>();
        for (CurrencyValue currencyValue : toSave) {
            save(currencyValue);
            saveAll.add(getById(currencyValue.getId()));
        }
        return saveAll;
    }
}