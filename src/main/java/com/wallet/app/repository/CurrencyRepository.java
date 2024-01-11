package com.wallet.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Currency;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CurrencyRepository implements Crud<Currency> {

    @Override
    public Currency getById(String id) {
        return (Currency) ImplementationMethod.findById(id, "currency");
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT * FROM \"currency\" WHERE id = " + id + ";";
        //     resultSet = statement.executeQuery(sql);
        //     Currency responseSQL = null;
            
        //     while (resultSet.next()) {
        //         responseSQL = new Currency(
        //                 resultSet.getString("id"),
        //                 resultSet.getString("name"),
        //                 resultSet.getString("code")
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
    public List<Currency> findAll() {
        List<Currency> listCurrencies = new ArrayList<>();
        for (Object object : ImplementationMethod.findAll("currency")) {
            listCurrencies.add((Currency)object);
        }
        return listCurrencies;
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT  * FROM \"currency\" ORDER BY name;";
        //     resultSet = statement.executeQuery(sql);
        //     List<Currency> responseSQL = new ArrayList<>();

        //     while (resultSet.next()) {
        //         responseSQL.add(new Currency(
        //                 resultSet.getString("id"),
        //                 resultSet.getString("name"),
        //                 resultSet.getString("code")
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
        ImplementationMethod.save(toSave);
        return getById(toSave.getId());
        // Connection connection = null;
        // Statement statement = null;
        
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "INSERT INTO \"currency\" (id, name, code) VALUES " +
        //     "( '" + toSave.getId() + "', '" + toSave.getName() + "', '" + toSave.getCode() + "'  );";
            
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
