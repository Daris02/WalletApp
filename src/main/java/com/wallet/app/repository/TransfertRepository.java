package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Transfert;

public class TransfertRepository implements Crud<Transfert> {
    private final Connection connection = ConnectionDB.createConnection();

    @Override
    public Transfert getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public List<Transfert> findAll() {
        String sql = "SELECT  * FROM \"transfert\" ORDER BY datetime;";
        List<Transfert> responseSQL = new ArrayList<>();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            while (resultSet.next()) {
                responseSQL.add(new Transfert(
                        resultSet.getString("id"),
                        resultSet.getString("accountId1"),
                        resultSet.getString("accountId2"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("datetime")
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
    public List<Transfert> saveAll(List<Transfert> toSave) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveAll'");
    }

    @Override
    public Transfert save(Transfert toSave) {
        String sql = "INSERT INTO \"transfert\" (amount, accountid1, accountid2) VALUES " +
        "(" + toSave.getAmount() + ", '" + toSave.getDebtorId() + "' , '" + toSave.getCreditorId() + "' ) ;";

        try {
            connection.createStatement().executeUpdate(sql);
            
            return toSave;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
