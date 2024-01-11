package com.wallet.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.CurrencyValue;

public class CurrencyValueRepository implements Crud<CurrencyValue> {

    @Override
    public CurrencyValue getById(String id) {
        return (CurrencyValue) ImplementationMethod.findById(id, "currency_value");
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;

        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT * FROM \"currency_value\" WHERE id = " + id + ";";
        //     resultSet = statement.executeQuery(sql);
        //     CurrencyValue responseSQL = null;

        //     while (resultSet.next()) {
        //         responseSQL = new CurrencyValue(
        //                 resultSet.getInt("id"),
        //                 resultSet.getString("currency_source"),
        //                 resultSet.getString("currency_destination"),
        //                 resultSet.getDouble("amount"),
        //                 resultSet.getTimestamp("date_effect").toLocalDateTime()
        //             );
        //     }
        //     return responseSQL;

        // } catch (SQLException e) {
        //     throw new RuntimeException(e);

        // } finally {
        //     try {
        //         if (resultSet != null) resultSet.close();
        //         if (statement != null) statement.close();
        //         if (connection != null) connection.close();
        //     } catch (SQLException e) {
        //         throw new RuntimeException(e);
        //     }
        // }
    }

    @Override
    public List<CurrencyValue> findAll() {
        List<CurrencyValue> listCurrenciesValues = new ArrayList<>();
        for (Object object : ImplementationMethod.findAll("currency_value")) {
            listCurrenciesValues.add((CurrencyValue)object);
        }
        return listCurrenciesValues;
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT * FROM \"currency_value\" ORDER BY date_effect;";
        //     resultSet = statement.executeQuery(sql);
        //     List<CurrencyValue> responseSQL = new ArrayList<>();

        //     while (resultSet.next()) {
        //         responseSQL.add(new CurrencyValue(
        //                 resultSet.getInt("id"),
        //                 resultSet.getString("currency_source"),
        //                 resultSet.getString("currency_destination"),
        //                 resultSet.getDouble("amount"),
        //                 resultSet.getTimestamp("date_effect").toLocalDateTime()
        //             )
        //         );
        //     }
        //     return responseSQL;

        // } catch (SQLException e) {
        //     throw new RuntimeException(e);

        // } finally {
        //     try {
        //         if (resultSet != null) resultSet.close();
        //         if (statement != null) statement.close();
        //         if (connection != null) connection.close();
        //     } catch (SQLException e) {
        //         throw new RuntimeException(e);
        //     }
        // }
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
        ImplementationMethod.save(toSave);
        return getById(toSave.getId().toString());
        // Connection connection = null;
        // Statement statement = null;
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "INSERT INTO \"currency_value\" (currency_source, currency_destination, amount) VALUES " +
        //         "( " + toSave.getCurrencySource() + ", " + toSave.getCurrencyDestination() + ", " + toSave.getAmount() + " );";
            
        //     statement.executeUpdate(sql);
        //     return toSave;

        // } catch (SQLException e) {
        //     throw new RuntimeException(e);

        // } finally {
        //     try {
        //         if (statement != null) statement.close();
        //         if (connection != null) connection.close();
        //     } catch (SQLException e) {
        //         throw new RuntimeException(e);
        //     }
        // }
    }

}