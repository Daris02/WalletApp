package com.wallet.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.wallet.app.model.Transfert;

public class TransfertRepository implements Crud<Transfert> {

    @Override
    public Transfert getById(String id) {
        return (Transfert) ImplementationMethod.findById(id, "transfert");
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT * FROM \"transfert\" WHERE id = '" + id + "';";
        //     resultSet = statement.executeQuery(sql);
        //     Transfert responseSQL = null;

        //     while (resultSet.next()) {
        //         responseSQL = new Transfert(
        //                 resultSet.getString("id"),
        //                 resultSet.getString("accountId1"),
        //                 resultSet.getString("accountId2"),
        //                 resultSet.getDouble("amount"),
        //                 resultSet.getTimestamp("datetime")
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
    public List<Transfert> findAll() {
        List<Transfert> listTransferts = new ArrayList<>();
        for (Object object : ImplementationMethod.findAll("transfert")) {
            listTransferts.add((Transfert)object);
        }
        return listTransferts;
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT * FROM \"transfert\" ORDER BY datetime;";
        //     resultSet = statement.executeQuery(sql);
        //     List<Transfert> responseSQL = new ArrayList<>();

        //     while (resultSet.next()) {
        //         responseSQL.add(new Transfert(
        //                 resultSet.getString("id"),
        //                 resultSet.getString("accountId1"),
        //                 resultSet.getString("accountId2"),
        //                 resultSet.getDouble("amount"),
        //                 resultSet.getTimestamp("datetime")
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
    public List<Transfert> saveAll(List<Transfert> toSave) {
        List<Transfert> saveAll = new ArrayList<>();
        for (Transfert transfert : toSave) {
            save(transfert);
            saveAll.add(getById(transfert.getId()));
        }
        return saveAll;
    }

    @Override
    public Transfert save(Transfert toSave) {
        ImplementationMethod.save(toSave);
        return getById(toSave.getId());
        // Connection connection = null;
        // Statement statement = null;
        
        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "INSERT INTO \"transfert\" (amount, accountid1, accountid2) VALUES " +
        //     "(" + toSave.getAmount() + ", '" + toSave.getDebtorId() + "' , '" + toSave.getCreditorId() + "' ) ;";
            
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
