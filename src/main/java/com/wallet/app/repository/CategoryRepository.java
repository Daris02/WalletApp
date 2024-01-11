package com.wallet.app.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wallet.app.config.ConnectionDB;
import com.wallet.app.model.Category;

public class CategoryRepository implements Crud<Category> {

    @Override
    public Category getById(String id) {
        return (Category) ImplementationMethod.findById(id, "category");
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;

        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT * FROM \"category\" WHERE id = " + id + ";";
        //     resultSet = statement.executeQuery(sql);
        //     Category responseSQL = null;

        //     while (resultSet.next()) {
        //         responseSQL = new Category(
        //                 resultSet.getInt("id"),
        //                 resultSet.getString("name")
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
    public List<Category> findAll() {
        List<Category> listCategories = new ArrayList<>();
        for (Object object : ImplementationMethod.findAll("category")) {
            listCategories.add((Category)object);
        }
        return listCategories;
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet resultSet = null;

        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "SELECT  * FROM \"category\" ORDER BY name;";
        //     resultSet = statement.executeQuery(sql);
        //     List<Category> responseSQL = new ArrayList<>();

        //     while (resultSet.next()) {
        //         responseSQL.add(new Category(
        //                 resultSet.getInt("id"),
        //                 resultSet.getString("name")
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
    public List<Category> saveAll(List<Category> toSave) {
        List<Category> saveAll = new ArrayList<>();
        for (Category category : toSave) {
            save(category);
            saveAll.add(getByName(category.getName()));
        }
        return saveAll;
    }

    @Override
    public Category save(Category toSave) {
        ImplementationMethod.save(toSave);
        return getById(toSave.getId().toString());
        // Connection connection = null;
        // Statement statement = null;

        // try {
        //     connection = ConnectionDB.createConnection();
        //     statement = connection.createStatement();
        //     String sql = "INSERT INTO \"category\" (name) VALUES ('" + toSave.getName() + "');";
            
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
    
    public Category getByName(String name) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = ConnectionDB.createConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM \"category\" WHERE name = " + name + ";";
            resultSet = statement.executeQuery(sql);
            Category responseSQL = null;

            while (resultSet.next()) {
                responseSQL = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
            }
            return responseSQL;

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
}
