package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Account;
import com.wallet.app.model.Category;
import com.wallet.app.model.Currency;
import com.wallet.app.model.CurrencyValue;
import com.wallet.app.model.Transaction;
import com.wallet.app.model.Transfert;

public class ImplementationMethod {

    public static Object findById(String id, String table) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();
            
            String sql = "SELECT * FROM \""+ table +"\" WHERE id = '" + id + "';";
            
            resultSet = statement.executeQuery(sql);

            Object objectResult = null;

            while (resultSet.next()) {
                objectResult = createResultSet(resultSet, table, objectResult);
            }

            return objectResult;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Object> findAll(String table) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();

            String sql = "SELECT  * FROM \""+ table +"\";";
            
            resultSet = statement.executeQuery(sql);
            
            List<Object> listObjectResult = new ArrayList<>();
            Object objectResult = null;

            while (resultSet.next()) {
                listObjectResult.add(createResultSet(resultSet, table, objectResult));
            }
            return listObjectResult;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Object save(Object toSave) {
        Connection connection = null;
        Statement statement = null;
        String sql = "";
        
        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();

            sql = createSql(toSave);

            statement.executeUpdate(sql);
            return toSave;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static void deleteById(String id) {

    }

    private static Object createResultSet(ResultSet resultSet, String table, Object obj) {
        CurrencyRepository currencyRepository = new CurrencyRepository();

        try {
            if (table.equals("account")) {
                obj = new Account(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    0.0,
                    resultSet.getTimestamp("creationdate"),
                    resultSet.getString("account_type"),
                    currencyRepository.getById(resultSet.getString("currencyid"))
                );
                return obj;
            }
            
            if (table.equals("currency")) {
                return new Currency(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("code")
                );
            }
            
            if (table.equals("currency_value")) {
                return new CurrencyValue(
                    resultSet.getInt("id"),
                    resultSet.getString("currency_source"),
                    resultSet.getString("currency_destination"),
                    resultSet.getDouble("amount"),
                    resultSet.getTimestamp("date_effect").toLocalDateTime()
                );
            }

            if (table.equals("category")) {
                return new Category(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
                );
            }

            if (table.equals("transaction")) {
                return new Transaction(
                    resultSet.getString("id"),
                    resultSet.getString("label"),
                    resultSet.getDouble("amount"),
                    resultSet.getString("transactiontype"),
                    resultSet.getTimestamp("datetime"),
                    resultSet.getString("accountid"),
                    resultSet.getInt("categoryid")
                );
            }

            if (table.equals("transfert")) {
                return new Transfert(
                    resultSet.getString("id"),
                    resultSet.getString("accountId1"),
                    resultSet.getString("accountId2"),
                    resultSet.getDouble("amount"),
                    resultSet.getTimestamp("datetime")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String createSql(Object obj) {
        AccountRepository accountRepository = new AccountRepository();

        if (obj.getClass().equals(Account.class)) {
            Account toSave = (Account) obj;
            return "DO $$" +
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
        }

        if (obj.getClass().equals(Currency.class)) {
            Currency toSave = (Currency) obj;
            return "INSERT INTO \"currency\" (id, name, code) VALUES " +
            "( '" + toSave.getId() + "', '" + toSave.getName() + "', '" + toSave.getCode() + "'  );";
        }
        
        if (obj.getClass().equals(CurrencyValue.class)) {
            CurrencyValue toSave = (CurrencyValue) obj;
            return "INSERT INTO \"currency_value\" (currency_source, currency_destination, amount) VALUES " +
                "( " + toSave.getCurrencySource() + ", " + toSave.getCurrencyDestination() + ", " + toSave.getAmount() + " );";
        }
        
        if (obj.getClass().equals(Category.class)) {
            Category toSave = (Category) obj;
            return "INSERT INTO \"category\" (name) VALUES ('" + toSave.getName() + "');";
        }
        
        if (obj.getClass().equals(Transaction.class)) {
            Transaction toSave = (Transaction) obj;

            if ("DEBIT".equals(toSave.getType())) {
                return "DO $$" +
                        "BEGIN" +
                        "   BEGIN" +
                        "       INSERT INTO \"balance_history\" (value, accountId) VALUES " +
                        "           ( (" + accountRepository.getBalanceNow(toSave.getAccountId()).getValue() + " - " + toSave.getAmount() + "), " +
                        "              '" + toSave.getAccountId() + "' );" +
                        "       INSERT INTO \"transaction\" (label, amount, transactiontype, accountId,  categoryId) VALUES " +
                        "           ('" + toSave.getLabel() + "', " + toSave.getAmount() + ", '" + toSave.getType() + "', '" + toSave.getAccountId() + "', " + toSave.getCategoryId() + ");" +
                        "       EXCEPTION" +
                        "           WHEN OTHERS THEN" +
                        "               ROLLBACK;" +
                        "               RAISE;" +
                        "   END;" +
                        "   COMMIT;" +
                        "END $$;";
            }
            
            if ("CREDIT".equals(toSave.getType())) {
                return "DO $$" +
                        "BEGIN" +
                        "   BEGIN" +
                        "       INSERT INTO \"balance_history\" (value, accountId) VALUES " +
                        "           ( (" + accountRepository.getBalanceNow(toSave.getAccountId()).getValue() + " + " + toSave.getAmount() + "), " +
                        "            '" + toSave.getAccountId() + "' );" +
                        "       INSERT INTO \"transaction\" (label, amount, transactiontype, accountId, categoryId) VALUES " +
                        "           ('" + toSave.getLabel() + "', " + toSave.getAmount() + ", '" + toSave.getType() + "', '" + toSave.getAccountId() + "', " + toSave.getCategoryId() + ");" +
                        "       EXCEPTION" +
                        "           WHEN OTHERS THEN" +
                        "               ROLLBACK;" +
                        "               RAISE;" +
                        "   END;" +
                        "   COMMIT;" +
                        "END $$;";
            }
        }
        
        if (obj.getClass().equals(Transfert.class)) {
            Transfert toSave = (Transfert) obj;
            return "INSERT INTO \"transfert\" (id, amount, accountid1, accountid2) VALUES " +
                "( '" + toSave.getId() + "', " + toSave.getAmount() + ", '" + toSave.getDebtorId() + "' , '" + toSave.getCreditorId() + "' ) ;";
        }

        return null;
    }
}
