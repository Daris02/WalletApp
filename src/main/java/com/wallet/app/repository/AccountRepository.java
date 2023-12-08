package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Account;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountRepository implements Crud<Account> {
    private final Connection connection = ConnectionDB.createConnection();

    @Override
    public Account getById(String id) {
        String sql = "SELECT * FROM \"account\" WHERE id = " + id + ";";
        Account responseSQL = null;

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL = new Account(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("balance"),
                        resultSet.getTimestamp("creationdate"),
                        resultSet.getString("account_type"),
                        resultSet.getInt("currencyid")
                    );
            }
            return responseSQL;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> findAll() {
        String sql = "SELECT  * FROM \"account\" ORDER BY creationdate;";
        List<Account> responseSQL = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL.add(new Account(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("balance"),
                        resultSet.getTimestamp("creationdate"),
                        resultSet.getString("account_type"),
                        resultSet.getInt("currencyid")
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
        toSave.setId(UUID.randomUUID().toString());
        
        String sql = "DO $$" +
                "        BEGIN" +
                "            BEGIN" +
                "                INSERT INTO \"account\" (id, name, account_type, currencyid) VALUES ( '" + toSave.getId() + "', '" + toSave.getName() + "', '" + toSave.getType() + "', 1);" +
                "                INSERT INTO \"balance_history\" (accountId) VALUES ('" + toSave.getId() + "' );" +
                "            EXCEPTION" +
                "                WHEN OTHERS THEN" +
                "                    ROLLBACK;" +
                "                    RAISE;" +
                "            END;" +
                "            COMMIT;" +
                "        END $$;";

        try {
            connection.createStatement().executeUpdate(sql);
            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
