package com.wallet.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Currency;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyRepository extends AutoCrud<Currency, Integer> {
    
    @Override
    protected String getTableName() {
        return "currency";
    }
    
    @Override
    protected Currency mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new Currency(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("code")
            );
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
    
}
