package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Account;
import com.wallet.app.model.Balance;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountRepository implements Crud<Account> {
    private final Connection connection = ConnectionDB.createConnection();
    private CurrencyRepository currencyRepo = new CurrencyRepository();

    @Override
    public Account getById(String id) {
        String sql = "SELECT * FROM \"account\" WHERE id = '" + id + "';";
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
                        currencyRepo.getById(resultSet.getString("currencyid"))
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
                        currencyRepo.getById(resultSet.getString("currencyid"))
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
                "                INSERT INTO \"account\" (id, name, account_type, currencyid) VALUES ( '" + toSave.getId() + "', '" + toSave.getName() + "', '" + toSave.getType() + "', " + toSave.getCurrency().getId() + ");" +
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
    
    public List<Balance> getBalanceHistory(String id, Timestamp startDatetime, Timestamp endDatetime) {
        String sql = "";
        if (startDatetime == null && endDatetime == null) {
            sql = "SELECT * FROM \"balance_history\" WHERE accountid = ?;";
        } else {
            sql = "SELECT * FROM \"balance_history\" WHERE accountid = ? " + 
                    " AND updatedatetime BETWEEN ? AND ? ;";
        }

        List<Balance> responseSQL = new ArrayList<>();

        try {
            PreparedStatement preSt = connection.prepareStatement(sql);
            preSt.setObject(1, UUID.fromString(id));
            
            if (startDatetime != null && endDatetime != null) {
                preSt.setTimestamp(2, startDatetime);
                preSt.setTimestamp(3, endDatetime);
            }

            ResultSet resultSet = preSt.executeQuery();
            
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
            e.printStackTrace();
        }
        return null;
    }
}
