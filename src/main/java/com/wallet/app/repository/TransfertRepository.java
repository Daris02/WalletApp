package com.wallet.app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Transfert;

public class TransfertRepository extends AutoCrud<Transfert, String> {
    
    @Override
    protected String getTableName() {
        return "transfert";
    }
    
    @Override
    protected Transfert mapResultSetToEntity(ResultSet resultSet) {
        try {
            return new Transfert(
                resultSet.getString("id"),
                resultSet.getDouble("amount"),
                resultSet.getTimestamp("datetime"),
                resultSet.getString("debtorId"),
                resultSet.getString("creditorId")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Transfert> saveAll(List<Transfert> toSave) {
        List<Transfert> saveAll = new ArrayList<>();
        for (Transfert transfert : toSave) {
            save(transfert);
            saveAll.add(getById(transfert.getId()));
        }
        return saveAll;
    }
}
